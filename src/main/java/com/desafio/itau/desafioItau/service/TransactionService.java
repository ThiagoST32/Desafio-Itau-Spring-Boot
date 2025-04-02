package com.desafio.itau.desafioItau.service;

import com.desafio.itau.desafioItau.domain.Transaction;
import com.desafio.itau.desafioItau.infra.Exceptions.InvalidBodyTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.NegativeValueTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.TransactionInTheFutureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service class for managing transactions and generating statistics.
 * This class provides methods to add transactions, clear the transaction list,
 * and calculate statistical summaries for transactions within specific time intervals.
 */
@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);


    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction){
        logger.info("Adicionando transação! (service)");
        this.verifyTransaction(transaction);
        transactions.add(transaction);
    }

    public void clearTransaction(){
        logger.info("Limpando transações! (service)");
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics60Seconds(){
        logger.info("Buscando transações dos ultimos 60 segundos! (service)");
        OffsetDateTime now = OffsetDateTime.now();
        return this.transactions.stream()
                .filter(t -> t.getDataHora().isBefore(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }

    public DoubleSummaryStatistics getStatistics120Seconds(){
        logger.info("Buscando transações dos ultimos 120 segundos! (service)");
        OffsetDateTime now = OffsetDateTime.now();
        return this.transactions.stream()
                .filter(t -> t.getDataHora().isBefore(now.minusSeconds(120)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }

    public void verifyTransaction(Transaction transaction){
        logger.info("Verificando se a transação é nula! (TransactionService)");
        if (transaction == null) throw new InvalidBodyTransactionException();

        logger.info("Verificando se o valor da transação é menor que 0! (TransactionService)");
        if (transaction.getValor() < 0) throw new NegativeValueTransactionException();

        logger.info("Verificando se a transação ocorre no futuro! (TransactionService)");
        if (transaction.getDataHora().isAfter(OffsetDateTime.now())) throw new TransactionInTheFutureException();
    }
}

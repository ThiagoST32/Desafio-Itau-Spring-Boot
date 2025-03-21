package com.desafio.itau.desafioItau.service;

import com.desafio.itau.desafioItau.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);


    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction){
        logger.info("Adicionando transação! (service)");
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
}

package com.desafio.itau.desafioItau.service;

import com.desafio.itau.desafioItau.domain.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void clearTransaction(){
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics60Seconds(){
        OffsetDateTime now = OffsetDateTime.now();

        return this.transactions.stream()
                .filter(t -> t.getDataHora().isBefore(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }

    public DoubleSummaryStatistics getStatistics120Seconds(){
        OffsetDateTime now = OffsetDateTime.now();

        return this.transactions.stream()
                .filter(t -> t.getDataHora().isBefore(now.minusSeconds(120)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }
}

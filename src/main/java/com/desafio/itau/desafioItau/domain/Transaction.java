package com.desafio.itau.desafioItau.domain;

import java.time.OffsetDateTime;

/**
 * Represents a financial transaction with a value and a timestamp.
 * This class is used to store details of a transaction,
 * including the monetary amount and the exact time it occurred.
 * Immutable field:
 * - `dataHora`: The date and time when the transaction occurred.
 * Mutable field:
 * - `valor`: The monetary value associated with the transaction.
 * Constructor:
 * - Initializes the transaction with the specified monetary value and timestamp.
 * Methods:
 * - `getValor()`: Retrieves the monetary value of the transaction.
 * - `getDataHora()`: Retrieves the timestamp of when the transaction occurred.
 */
public class Transaction {

    private double valor = 0;
    private final OffsetDateTime dataHora;

    public Transaction(final double valor, final OffsetDateTime dataHora){
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

}


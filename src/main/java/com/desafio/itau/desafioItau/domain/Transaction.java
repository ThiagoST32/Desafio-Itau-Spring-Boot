package com.desafio.itau.desafioItau.domain;

import java.time.OffsetDateTime;

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


package com.desafio.itau.desafioItau.dto;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class TransactionDTO {

    private Double valor;

    private OffsetDateTime dataHora;
}

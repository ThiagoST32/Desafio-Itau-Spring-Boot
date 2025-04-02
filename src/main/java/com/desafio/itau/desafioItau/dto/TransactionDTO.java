package com.desafio.itau.desafioItau.dto;

import lombok.Getter;

import java.time.OffsetDateTime;

/**
 * Data Transfer Object (DTO) representing a transaction.
 * This class is used to encapsulate information about a transaction
 * and facilitate data exchange between different application layers.
 * Attributes:
 * - valor (Double): The monetary value of the transaction.
 * - dataHora (OffsetDateTime): The timestamp of when the transaction occurred.
 */
@Getter
public class TransactionDTO {

    private Double valor;

    private OffsetDateTime dataHora;
}

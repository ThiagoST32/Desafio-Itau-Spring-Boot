package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

/**
 * Rest controller for managing statistical operations on transaction data.
 * Provides endpoints for retrieving statistical summaries of transactions
 * within specific time intervals (e.g., the last 60 or 120 seconds).
 * This class interacts with the {@link TransactionService} to perform
 * business logic operations and retrieve statistical summaries of transactions.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private TransactionService service;

    @GetMapping("/60")
    @Operation(description = "Endpoint responsável pelas estatisticas dentro de 60 segundos!")
    public ResponseEntity<DoubleSummaryStatistics> getStatisticsTransactions60Seconds(){
        logger.info("Buscando estatisticas de transações nos últimos 60 segundos! (StatisticsController)");
        DoubleSummaryStatistics statistics60Seconds = this.service.getStatistics60Seconds();
        return new ResponseEntity<>(statistics60Seconds, HttpStatus.OK);
    }

    @GetMapping("/120")
    @Operation(description = "Endpoint responsável pelas estatisticas dentro de 120 segundos!")
    public ResponseEntity<DoubleSummaryStatistics> getStatisticsTransaction120Seconds(){
        logger.info("Buscando estatisticas de transações nos últimos 120 segundos! (StatisticsController)");
        DoubleSummaryStatistics statistics120Seconds = this.service.getStatistics120Seconds();
        return new ResponseEntity<>(statistics120Seconds, HttpStatus.OK);
    }
}

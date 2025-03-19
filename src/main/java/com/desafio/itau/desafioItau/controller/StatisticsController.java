package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private TransactionService service;

    @GetMapping("/60")
    public ResponseEntity<DoubleSummaryStatistics> getStatisticsTransactions(){
        DoubleSummaryStatistics statistics60Seconds = this.service.getStatistics60Seconds();
        return new ResponseEntity<>(statistics60Seconds, HttpStatus.OK);
    }

    @GetMapping("/120")
    public ResponseEntity<DoubleSummaryStatistics> getStatisticsTransaction120Seconds(){
        DoubleSummaryStatistics statistics120Seconds = this.service.getStatistics120Seconds();
        return new ResponseEntity<>(statistics120Seconds, HttpStatus.OK);
    }
}

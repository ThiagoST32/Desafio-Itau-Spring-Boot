package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.domain.Transaction;
import com.desafio.itau.desafioItau.dto.TransactionDTO;
import com.desafio.itau.desafioItau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void>createTransaction(@Valid @RequestBody TransactionDTO transactionDTO){
        if (transactionDTO.getDataHora().isAfter(OffsetDateTime.now())){
            return ResponseEntity.unprocessableEntity().build();
        }
        this.transactionService.addTransaction(new Transaction(transactionDTO.getValor(), transactionDTO.getDataHora()));
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<DoubleSummaryStatistics> getStatisticsTransactions(){
        DoubleSummaryStatistics summaryStatistics = this.transactionService.getEstatics();
        return new ResponseEntity<>(summaryStatistics, HttpStatus.OK);
    }

}

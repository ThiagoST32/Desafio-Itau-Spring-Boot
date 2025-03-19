package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.domain.Transaction;
import com.desafio.itau.desafioItau.dto.TransactionDTO;
import com.desafio.itau.desafioItau.infra.Exceptions.InvalidBodyTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.NegativeValueTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.TransactionInTheFutureException;
import com.desafio.itau.desafioItau.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void>createTransaction(@RequestBody TransactionDTO transactionDTO){
        if (transactionDTO.getDataHora() == null || transactionDTO.getValor() == null) throw new InvalidBodyTransactionException();
        if (transactionDTO.getValor() < 0) throw new NegativeValueTransactionException();
        if (transactionDTO.getDataHora().isAfter(OffsetDateTime.now())) throw new TransactionInTheFutureException();
        this.transactionService.addTransaction(new Transaction(transactionDTO.getValor(), transactionDTO.getDataHora()));
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }

    @DeleteMapping("/deleteTransactions")
    public ResponseEntity<Void>getTransactions(){
        this.transactionService.clearTransaction();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

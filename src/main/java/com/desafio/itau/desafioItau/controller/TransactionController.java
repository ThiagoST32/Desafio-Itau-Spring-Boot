package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.dto.TransactionDTO;
import com.desafio.itau.desafioItau.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void>createTransaction(@RequestBody TransactionDTO transactionDTO){
        this.transactionService.addTransaction();
    }
}

package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.domain.Transaction;
import com.desafio.itau.desafioItau.dto.TransactionDTO;
import com.desafio.itau.desafioItau.infra.Exceptions.InvalidBodyTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.NegativeValueTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.TransactionInTheFutureException;
import com.desafio.itau.desafioItau.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void>createTransaction(@RequestBody TransactionDTO transactionDTO){

        logger.info("Verificando se o body é invalido! (TransactionController)");
        if (transactionDTO.getDataHora() == null || transactionDTO.getValor() == null) throw new InvalidBodyTransactionException();

        logger.info("Verificando se o valor da transação é menor que 0! (TransactionController)");
        if (transactionDTO.getValor() < 0) throw new NegativeValueTransactionException();

        logger.info("Verificando se a transação ocorre no futuro! (TransactionController)");
        if (transactionDTO.getDataHora().isAfter(OffsetDateTime.now())) throw new TransactionInTheFutureException();

        logger.warn("Criação da transação! (TransactionController)");
        this.transactionService.addTransaction(new Transaction(transactionDTO.getValor(), transactionDTO.getDataHora()));
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }

    @DeleteMapping("/deleteTransactions")
    public ResponseEntity<Void>getTransactions(){
        logger.info("Deletando todas as transações! (TransactionController)");
        this.transactionService.clearTransaction();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.desafio.itau.desafioItau.controller;

import com.desafio.itau.desafioItau.domain.Transaction;
import com.desafio.itau.desafioItau.dto.TransactionDTO;
import com.desafio.itau.desafioItau.infra.Exceptions.InvalidBodyTransactionException;
import com.desafio.itau.desafioItau.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

/**
 * Rest controller for managing transaction-related operations.
 * Handles the creation of transactions and the deletion of all transactions.
 * This class utilizes the {@link  TransactionService} to perform business logic and operations
 * related to transactions processing.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    @Operation(description = "Endpoint rsponsável pela criação de transação!")
    public ResponseEntity<Void>createTransaction(@RequestBody TransactionDTO transactionDTO){
        logger.info("Verificando se o body é invalido! (TransactionController)");
        if (transactionDTO.getDataHora() == null || transactionDTO.getValor() == null) throw new InvalidBodyTransactionException();
        logger.warn("Criação da transação! (TransactionController)");
        this.transactionService.addTransaction(new Transaction(transactionDTO.getValor(), transactionDTO.getDataHora()));
        return new ResponseEntity<>(HttpStatus.valueOf(201));
    }

    @DeleteMapping("/deleteTransactions")
    @Operation(description = "Endpoint responsável pelo delete de todas as transações!")
    public ResponseEntity<Void>deleteTransactions(){
        logger.info("Deletando todas as transações! (TransactionController)");
        this.transactionService.clearTransaction();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

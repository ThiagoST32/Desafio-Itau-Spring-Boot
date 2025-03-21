package com.desafio.itau.desafioItau.infra;


import com.desafio.itau.desafioItau.infra.Exceptions.InvalidBodyTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.NegativeValueTransactionException;
import com.desafio.itau.desafioItau.infra.Exceptions.TransactionInTheFutureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericException(Exception ex){
        logger.error("Chamando tratamento de exceções genéricas! (HandlerException)");
        ApiError apiError = ApiError
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(ex.getMessage()))
                .offsetDateTime(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NegativeValueTransactionException.class)
    public ResponseEntity<ApiError> negativeValueTransactionException(NegativeValueTransactionException ex){
        logger.error("Chamando tratamento de exceção sobre valores negativos na trasação! (HandlerException)");
        ApiError apiError = ApiError
                .builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .errors(List.of(ex.getMessage()))
                .offsetDateTime(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TransactionInTheFutureException.class)
    public ResponseEntity<ApiError> transactionInTheFutureException(TransactionInTheFutureException ex){
        logger.error("Chamando tratamento de exceção sobre transação ocorrendo no futuro! (HandlerException)");
        ApiError apiError = ApiError
                .builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .errors(List.of(ex.getMessage()))
                .offsetDateTime(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidBodyTransactionException.class)
    public ResponseEntity<ApiError> bodyEmptyTransactionException(InvalidBodyTransactionException ex){
        logger.error("Chamando tratamento de exceção sobre body invalido enviado! (HandlerException)");
        ApiError apiError = ApiError
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(ex.getMessage()))
                .offsetDateTime(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }
}

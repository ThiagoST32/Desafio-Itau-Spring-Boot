package com.desafio.itau.desafioItau.infra.Exceptions;

public class InvalidBodyTransactionException extends RuntimeException{
    public InvalidBodyTransactionException(){
        super("Invalid Transaction!!");
    }
}

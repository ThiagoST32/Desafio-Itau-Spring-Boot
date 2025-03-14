package com.desafio.itau.desafioItau.infra.Exceptions;

public class TransactionInTheFutureException extends RuntimeException{
    public TransactionInTheFutureException(){
        super("Cannot make one transaction in the future!");
    }
}

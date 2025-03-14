package com.desafio.itau.desafioItau.infra.Exceptions;

public class BodyEmptyTransactionException extends RuntimeException{
    public BodyEmptyTransactionException(){
        super("Transaction is empty!!");
    }
}

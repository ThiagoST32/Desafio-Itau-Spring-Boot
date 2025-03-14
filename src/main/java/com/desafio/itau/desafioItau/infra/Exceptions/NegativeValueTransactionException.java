package com.desafio.itau.desafioItau.infra.Exceptions;

public class NegativeValueTransactionException extends RuntimeException{
    public NegativeValueTransactionException(){
        super("Value cannot be negative!!");
    }
}

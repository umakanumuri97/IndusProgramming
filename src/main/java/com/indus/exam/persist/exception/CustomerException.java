package com.indus.exam.persist.exception;


public class CustomerException extends RuntimeException {

    public CustomerException(String message, Exception e) {
        super(message);
    }
}

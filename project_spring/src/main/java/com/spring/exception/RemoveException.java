package com.spring.exception;

public class RemoveException extends Exception {
    public RemoveException(String msg) {
        super(msg);
    }
    public RemoveException(){
        this("");
    }
}

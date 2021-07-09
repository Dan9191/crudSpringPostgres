package com.example.demo.utils;

public class NoEntityException extends Exception{
    private long id;

    public NoEntityException(String message, long id){
        super(message);
        this.id = id;
    }
}

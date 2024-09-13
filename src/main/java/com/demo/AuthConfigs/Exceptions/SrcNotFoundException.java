package com.demo.AuthConfigs.Exceptions;

public class SrcNotFoundException extends  RuntimeException{
    public SrcNotFoundException(String message){
        super(message);
    }
}

package com.javanauta.agendadortarefas.infrastucture.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String messagem){
        super(messagem);
    }

    public ResourceNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}

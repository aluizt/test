package br.com.appmessage.send.exception.model;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}

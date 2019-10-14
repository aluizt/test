package br.com.appmessage.send.exception.model;

public class SendMessageException extends RuntimeException {
    public SendMessageException(String message){
        super(message);
    }
}

package br.com.appmessage.send.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Issue implements Serializable {

    private String id;
    private String message;

    public Issue(Exception e) {
        this.id = e.toString();
        this.message = e.getMessage();
    }
}

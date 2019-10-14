package br.com.appmessage.send.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiException extends RuntimeException {

    private final List<StandardError> errors;

    public ApiException(StandardError errors) {
        this.errors = Arrays.asList(errors);
    }
}

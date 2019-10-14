package br.com.appmessage.send.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class ResponseError {

    private String namespace;
    private String language;
    @Singular
    private List<StandardError> errors;

}

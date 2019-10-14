package br.com.appmessage.send.exception;

import br.com.appmessage.send.exception.model.NotFoundException;
import br.com.appmessage.send.exception.model.SendMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHadle {

    private static final String PT_BR = "pt-BR";
    private static final String MESAGE_APP = "Entre em contato com nosso suporte por email: desenvservices@dimed.com.br.";
    private static final String MESSAGE_USER = "Verifique os dados de entrada e tente novamente.";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> apiException(ApiException exception, HttpServletRequest request) {
         return ResponseEntity.status(exception.getErrors().get(0).getStatus())
                .body(new ResponseError(request.getRequestURI(), PT_BR, exception.getErrors()));
    }

   

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> notFound(NotFoundException  e, HttpServletRequest request) {
        ApiException apiException = new ApiException(
                StandardError.builder()
                             .status(HttpStatus.NOT_FOUND.value())
                             .name(HttpStatus.NOT_FOUND.name())
                             .issues(new Issue(e))
                             .suggestedApplicationActions(MESAGE_APP)
                             .suggestedUserActions(MESSAGE_USER)
                             .build()
        );
        return ResponseEntity.status(apiException.getErrors().get(0).getStatus())
                .body(new ResponseError(request.getRequestURI(), PT_BR, apiException.getErrors()));
    }

    @ExceptionHandler(SendMessageException.class)
    public ResponseEntity<ResponseError> sendMessage(SendMessageException   e, HttpServletRequest request) {
        ApiException apiException = new ApiException(
                StandardError.builder()
                        .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .name(HttpStatus.SERVICE_UNAVAILABLE.name())
                        .issues(new Issue(e))
                        .suggestedApplicationActions(MESAGE_APP)
                        .suggestedUserActions(MESSAGE_USER)
                        .build()
        );
        return ResponseEntity.status(apiException.getErrors().get(0).getStatus())
                .body(new ResponseError(request.getRequestURI(), PT_BR, apiException.getErrors()));
    }
 
}

package br.com.appmessage.send.exception;

import static org.junit.jupiter.api.Assertions.*;

import br.com.appmessage.send.exception.model.NotFoundException;
import br.com.appmessage.send.exception.model.SendMessageException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ExceptionHadleTest {

    private static final String PT_BR = "pt-BR";
    private static final String MESAGE_APP = "Entre em contato com nosso suporte por email: desenvservices@dimed.com.br.";
    private static final String MESSAGE_USER = "Verifique os dados de entrada e tente novamente.";

    private HttpServletRequest request;
    private ExceptionHadle exceptionHadle;

    @BeforeEach
    public void setUp(){
        request = mock(HttpServletRequest.class);
        exceptionHadle = new ExceptionHadle();
    }

    @Test
    public void should_return_status_code_404(){

        var apiException = new ApiException(StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .name(HttpStatus.NOT_FOUND.name())
                .suggestedApplicationActions(MESAGE_APP)
                .suggestedUserActions(MESSAGE_USER)
                .build());

        ResponseEntity<ResponseError> responseEntity =
                exceptionHadle.apiException(apiException,request);

        assertEquals(404,responseEntity.getStatusCodeValue());
    }

    @Test
    public void test() {
        var exception = new NotFoundException("");

        ResponseEntity<ResponseError> responseEntity =
                exceptionHadle.notFound(exception, request);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void should_return_status_code_503() {
        var exception = new SendMessageException("");

        ResponseEntity<ResponseError> responseEntity =
                exceptionHadle.sendMessage(exception, request);

        assertEquals(503, responseEntity.getStatusCodeValue());
    }

}
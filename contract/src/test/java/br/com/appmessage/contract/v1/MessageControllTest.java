package br.com.appmessage.contract.v1;

import static org.junit.jupiter.api.Assertions.*;

import br.com.appmessage.contract.v1.facade.MessageFacadeContract;
import br.com.appmessage.contract.v1.stubs.MessageResponseStub;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MessageControllTest {

    @Mock
    private MessageFacadeContract messageFacadeContract;

    @InjectMocks
    private MessageControll messageControll;

    @Test
    public void should_return_messageResponse_with_findByMessageId(){
        when(messageFacadeContract.findByMessageId(any())).thenReturn(MessageResponseStub.getMessageResponse());
        var stub = MessageResponseStub.getMessageResponse();
        var response = messageControll.findByMessageId(any());
        Assert.assertTrue(stub.equals(response.getBody()));
    }

    @Test
    public void should_return_status_code_200_with_resubmitMessage(){
        when(messageFacadeContract.resubmitMessage(any())).thenReturn(MessageResponseStub.getMessageResponse());
        var stub = new ResponseEntity(HttpStatus.OK).getStatusCode();
        var response = messageControll.resubmitMessage(any());
        Assert.assertEquals(stub,response.getStatusCode());
    }

    @Test
    public void should_return_status_code_200(){

        when(messageFacadeContract.sendMessage(any())).thenReturn(MessageResponseStub.getMessageResponse());
        var stub = new ResponseEntity(HttpStatus.OK).getStatusCode();
        var response = messageControll.sendMessage(any());
        Assert.assertEquals(stub,response.getStatusCode());
    }

    @Test
    public void should_return_messageResponse_with_findBySubsidiaryId(){
        when(messageFacadeContract.findBySubsidiary(any())).thenReturn(MessageResponseStub.getMessageResponseList());
        var stub = MessageResponseStub.getMessageResponseList();
        var response = messageControll.findBySubsidiary(stub.get(0).getSubsidiaryId());
        Assert.assertEquals(stub.size(),response.getBody().size());
        Assert.assertEquals(stub.get(0).getMongoId(),response.getBody().get(0).getMongoId());
        Assert.assertEquals(stub.get(0).getMessageStatus(),response.getBody().get(0).getMessageStatus());
        Assert.assertEquals(stub.get(0).getCreationDate(),response.getBody().get(0).getCreationDate());
        Assert.assertEquals(stub.get(0).getGroupId(),response.getBody().get(0).getGroupId());
        Assert.assertEquals(stub.get(0).getResume(),response.getBody().get(0).getResume());
        Assert.assertEquals(stub.get(0).getSubsidiaryId(),response.getBody().get(0).getSubsidiaryId());
        Assert.assertEquals(stub.get(0).getText(),response.getBody().get(0).getText());
        Assert.assertEquals(stub.get(0).getUserId(),response.getBody().get(0).getUserId());

    }

    @Test
    public void should_return_list_messageResponse(){
        when(messageFacadeContract.findAllMessage()).thenReturn(MessageResponseStub.getMessageResponseList());
        var stub = MessageResponseStub.getMessageResponseList();
        var response = messageControll.findAllMessage();
        Assert.assertEquals(stub.size(),response.size());
        Assert.assertEquals(stub.get(0).getMongoId(),response.get(0).getMongoId());
        Assert.assertEquals(stub.get(0).getMessageStatus(),response.get(0).getMessageStatus());
        Assert.assertEquals(stub.get(0).getCreationDate(),response.get(0).getCreationDate());
        Assert.assertEquals(stub.get(0).getGroupId(),response.get(0).getGroupId());
        Assert.assertEquals(stub.get(0).getResume(),response.get(0).getResume());
        Assert.assertEquals(stub.get(0).getSubsidiaryId(),response.get(0).getSubsidiaryId());
        Assert.assertEquals(stub.get(0).getText(),response.get(0).getText());
        Assert.assertEquals(stub.get(0).getUserId(),response.get(0).getUserId());
    }

    }
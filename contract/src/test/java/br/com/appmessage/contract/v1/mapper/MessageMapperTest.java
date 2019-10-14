package br.com.appmessage.contract.v1.mapper;

import static org.junit.jupiter.api.Assertions.*;

import br.com.appmessage.contract.v1.stubs.MessageInputModelStub;
import br.com.appmessage.contract.v1.stubs.MessageOutputModelStub;
import br.com.appmessage.contract.v1.stubs.MessageRequestStub;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MessageMapperTest {

    @Test
    public void should_return_messageResponse(){
        var stub = MessageOutputModelStub.getMessageOutputModel();
        var response = MessageMapper.mapToMessageResponse(MessageOutputModelStub.getMessageOutputModel());

        Assert.assertEquals(stub.getMongoId(),response.getMongoId());
    }

    @Test
    public void should_return_list_messageResponse(){
        var stub = MessageOutputModelStub.getMessageOutputModelList();
        var response = MessageMapper.mapToMessageResponseList(MessageOutputModelStub.getMessageOutputModelList());

        Assert.assertEquals(stub.size(),response.size());
    }

    @Test
    public void should_return_messageInputModel(){
        var stub = MessageInputModelStub.getMessageInputModel();
        var response = MessageMapper.mapToMessageInputModel(MessageRequestStub.getMessageRequest());

        Assert.assertEquals(stub.getSubsidiaryId(),response.getSubsidiaryId());
        Assert.assertEquals(stub.getGroupId(),response.getGroupId());
        Assert.assertEquals(stub.getUserId(),response.getUserId());
        Assert.assertEquals(stub.getCreationDate(),response.getCreationDate());
        Assert.assertEquals(stub.getText(),response.getText());
        Assert.assertEquals(stub.getResume(),response.getResume());
    }

    @Test
    public void should_return_messageRequest(){

        var stub = MessageRequestStub.getMessageRequest();

        var response = MessageMapper.mapFromParam(1000l,
                "Conforme nova norma...",
                "Conforme nova norma, para identificar");

        Assert.assertEquals(stub.getSubsidiaryId(),response.getSubsidiaryId());
        Assert.assertEquals(stub.getResume(),response.getResume());
        Assert.assertEquals(stub.getText(),response.getText());
    }



}
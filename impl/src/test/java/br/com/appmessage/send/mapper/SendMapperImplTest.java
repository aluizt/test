package br.com.appmessage.send.mapper;

import br.com.appmessage.send.stub.MessageEntityStub;
import br.com.appmessage.send.stub.MessageInputModelStub;
import br.com.appmessage.send.stub.MessageOutputModelStub;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SendMapperImplTest {

    private SendMapperImpl sendMapper = new SendMapperImpl();

    @Test
    public void should_return_messageOutputModel() {
        var stub = MessageOutputModelStub.getMessageOutputModel();

        var result = sendMapper.mapToMessageInputModel(stub);

        Assert.assertEquals(stub.getMessageStatus(), result.getMessageStatus());
    }

    @Test
    public void should_return_messageInputModel() {
        var stub = MessageEntityStub.getEntity();
        var result = sendMapper.mapFomMessageEntity(stub);

        Assert.assertTrue(MessageInputModelStub.getMessageInputModel().equals(result));
    }

    @Test
    public void should_return_MessageInputModel_() {
        var stub = MessageOutputModelStub.getMessageOutputModel();
        var result = sendMapper.mapToMessageInputModel(stub);

        Assert.assertTrue(MessageInputModelStub.getMessageInputModel().equals(result));
    }

    @Test
    public void should_return_MessageEntity() {
        var stub = MessageInputModelStub.getMessageInputModel();
        var result = sendMapper.mapToMessageEntity(stub);

        Assert.assertTrue(MessageEntityStub.getEntity().equals(result));
    }

    @Test
    public void should_return_messageOutpuModelList() {
        var messageOutputModel = MessageOutputModelStub.getMessageOutputModelList();
        var stub = MessageEntityStub.getEntityList();
        var result = sendMapper.mapToMessageOutpuModelList(stub);

        Assert.assertEquals(messageOutputModel.size(), result.size());
        Assert.assertEquals(messageOutputModel.get(0).getMessageStatus(), result.get(0).getMessageStatus());
        Assert.assertEquals(messageOutputModel.get(0).getCreationDate(), result.get(0).getCreationDate());
        Assert.assertEquals(messageOutputModel.get(0).getGroupId(), result.get(0).getGroupId());
        Assert.assertEquals(messageOutputModel.get(0).getMongoId(), result.get(0).getMongoId());
        Assert.assertEquals(messageOutputModel.get(0).getResume(), result.get(0).getResume());
        Assert.assertEquals(messageOutputModel.get(0).getMessageStatus(), result.get(0).getMessageStatus());
        Assert.assertEquals(messageOutputModel.get(0).getSubsidiaryId(), result.get(0).getSubsidiaryId());
        Assert.assertEquals(messageOutputModel.get(0).getText(), result.get(0).getText());
        Assert.assertEquals(messageOutputModel.get(0).getUserId(), result.get(0).getUserId());
        Assert.assertEquals(messageOutputModel.get(0).getSent(), result.get(0).getSent());
    }
}
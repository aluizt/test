package br.com.appmessage.send.facade;

import br.com.appmessage.send.mapper.SendMapperImpl;
import br.com.appmessage.send.service.SendService;
import br.com.appmessage.send.stub.MessageEntityStub;
import br.com.appmessage.send.stub.MessageInputModelStub;
import br.com.appmessage.send.stub.MessageOutputModelStub;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SendFacadeImplTest {

    @Mock
    private SendMapperImpl sendMapperImpl;

    @Mock
    private SendService sendService;

    @InjectMocks
    private SendFacadeImpl sendFacadeImpl;

    @Test
    void should_return_message_resubmitted() {
        var messageOutputModel = MessageOutputModelStub.getMessageOutputModel();
        when(sendService.resubmitMessage(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        var response = sendFacadeImpl.resubmitMessage(any());
        Assert.assertTrue(messageOutputModel.equals(response));
    }

    @Test
    void should_return_message_send() {
        var messageOutputModel = MessageOutputModelStub.getMessageOutputModel();
        when(sendService.sendMessage(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        when(sendService.saveMessage(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        when(sendMapperImpl.mapToMessageInputModel(any())).thenReturn(MessageInputModelStub.getMessageInputModel());
        var response = sendFacadeImpl.sendMessage(any());
        Assert.assertTrue(messageOutputModel.equals(response));
    }

    @Test
    void should_return_list_message() {
        var messageOutputModelList = MessageOutputModelStub.getMessageOutputModelList();
        when(sendService.findAllMessage()).thenReturn(MessageOutputModelStub.getMessageOutputModelList());
        var response = sendFacadeImpl.findAllMessage();
        Assert.assertTrue(messageOutputModelList.equals(response));
        Assert.assertEquals(messageOutputModelList.size(), response.size());
    }

    @Test
    void should_return_list_message_by_user_id() {
        var messageOutputModelList = MessageOutputModelStub.getMessageOutputModelList();
        when(sendService.findByUserId(any())).thenReturn(MessageOutputModelStub.getMessageOutputModelList());
        var response = sendFacadeImpl.findByUserId(any());
        Assert.assertTrue(messageOutputModelList.equals(response));
        Assert.assertEquals(messageOutputModelList.size(), response.size());
    }

    @Test
    void should_return_list_message_by_message_id() {
        var messageOutputMode = MessageOutputModelStub.getMessageOutputModel();
        var entity = MessageEntityStub.getEntity();
        when(sendService.findByMongoId(any())).thenReturn(entity);
        when(sendMapperImpl.mapToMessageOutputModel(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        var response = sendFacadeImpl.findByMessageId(any());
        Assert.assertEquals(messageOutputMode.getMongoId(), response.getMongoId());
    }


    @Test
    void should_return_list_message_by_group_id() {
        var messageOutputModelList = MessageOutputModelStub.getMessageOutputModelList();
        when(sendService.findByGroupId(any())).thenReturn(MessageOutputModelStub.getMessageOutputModelList());
        var response = sendFacadeImpl.findByGroupId(any());
        Assert.assertTrue(messageOutputModelList.equals(response));
        Assert.assertEquals(messageOutputModelList.size(), response.size());
    }

    @Test
    void should_return_messages_by_one_subsidiary() {

        var entityStub = MessageEntityStub.getEntity();
        var outputModelList = MessageOutputModelStub.getMessageOutputModelList();
        when(sendService.findBySubsidiary(any())).thenReturn(MessageOutputModelStub.getMessageOutputModelList());
        var respose = sendFacadeImpl.findBySubsidiary(MessageEntityStub.getEntity().getSubsidiaryId());

        Assert.assertEquals(entityStub.getSubsidiaryId(), respose.get(0).getSubsidiaryId());
        Assert.assertEquals(entityStub.getMessageStatus(), respose.get(0).getMessageStatus());
        Assert.assertEquals(entityStub.getCreationDate(), respose.get(0).getCreationDate());
        Assert.assertEquals(entityStub.getGroupId(), respose.get(0).getGroupId());
        Assert.assertEquals(entityStub.getMongoId(), respose.get(0).getMongoId());
        Assert.assertEquals(entityStub.getResume(), respose.get(0).getResume());
        Assert.assertEquals(entityStub.getSent(), respose.get(0).getSent());
        Assert.assertEquals(entityStub.getText(), respose.get(0).getText());
        Assert.assertEquals(entityStub.getUserId(), respose.get(0).getUserId());
        Assert.assertEquals(1, respose.size());
    }
}
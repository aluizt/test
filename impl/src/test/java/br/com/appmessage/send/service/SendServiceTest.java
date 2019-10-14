package br.com.appmessage.send.service;

import br.com.appmessage.send.exception.model.NotFoundException;
import br.com.appmessage.send.mapper.SendMapperImpl;
import br.com.appmessage.send.repository.SendRepository;
import br.com.appmessage.send.sendmessage.SendMessage;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SendServiceTest {

    @Mock
    private SendMapperImpl sendMapperImpl;

    @Mock
    private SendRepository repository;

    @Mock
    private SendMessage sendMessage;

    @InjectMocks
    private SendService sendService;

    @Test
    public void should_return_NotFoundException_when_find_by_userId() {

        var entityStub = MessageEntityStub.getEntity();

        when(repository.findAllByUserId(any())).thenReturn(MessageEntityStub.getEntityList());
        when(sendMapperImpl.mapToMessageOutpuModelList(any())).thenReturn(new ArrayList<>());
        var thrown = assertThrows(NotFoundException.class, () -> sendService.findByUserId(entityStub.getUserId()));
    }

    @Test
    public void should_return_NotFoundException_when_find_by_groupId() {
        var entityStub = MessageEntityStub.getEntity();
        when(sendMapperImpl.mapToMessageOutpuModelList(any())).thenReturn(new ArrayList<>());
        var thrown = assertThrows(NotFoundException.class, () -> sendService.findByGroupId(entityStub.getGroupId()));
    }

    @Test
    public void should_return_NotFoundException_when_find_by_subsidiaryId() {
        var entityStub = MessageEntityStub.getEntity();
        when(sendMapperImpl.mapToMessageOutpuModelList(any())).thenReturn(new ArrayList<>());
        var thrown = assertThrows(NotFoundException.class, () -> sendService.findBySubsidiary(entityStub.getSubsidiaryId()));
    }

    @Test
    public void should_return_NotFoundException_when_find_findAllMessage() {
        var entityStub = MessageEntityStub.getEntity();
        when(sendMapperImpl.mapToMessageOutpuModelList(any())).thenReturn(new ArrayList<>());
        var thrown = assertThrows(NotFoundException.class, () -> sendService.findAllMessage());
    }

    @Test
    public void should_return_NotFoundException_when_find_by_mongoId() {
        var entityStub = MessageEntityStub.getEntity();
        when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
        var thrown = assertThrows(NotFoundException.class, () -> sendService.findByMongoId(entityStub.getMongoId()));
    }

    @Test
    public void should__return_messageOutputModel_when_send_message() {

        var outputModel = MessageOutputModelStub.getMessageOutputModel();
        var entityStub = MessageEntityStub.getEntity();
        var InputModel = MessageInputModelStub.getMessageInputModel();

        when(sendMessage.sendMessage(any())).thenReturn(MessageInputModelStub.getMessageInputModel());
        when(sendService.saveMessage(InputModel)).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        when(repository.save(entityStub)).thenReturn(MessageEntityStub.getEntity());
        when(sendMapperImpl.mapToMessageEntity(any())).thenReturn(entityStub);
        when(sendMapperImpl.mapToMessageOutputModel(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());

        var response = sendService.sendMessage(MessageInputModelStub.getMessageInputModel());

        System.out.println(outputModel.getMongoId());
        System.out.println(response.getMongoId());
        Assert.assertEquals(outputModel.getMongoId(), response.getMongoId());
        Assert.assertTrue(outputModel.equals(response));
    }

    @Test
    public void should__return_messageOutputModel_when_send_message_with_messageStatus_null() {

        var outputModel = MessageOutputModelStub.getMessageOutputModel();
        var entityStub = MessageEntityStub.getEntity();
        var inputModel = MessageInputModelStub.getMessageInputModel();
        var msg = MessageInputModelStub.getMessageInputModel();
        msg.setMessageStatus(null);
        when(sendMessage.sendMessage(any())).thenReturn(MessageInputModelStub.getMessageInputModel());
        when(sendService.saveMessage(inputModel)).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        when(repository.save(entityStub)).thenReturn(MessageEntityStub.getEntity());
        when(sendMapperImpl.mapToMessageEntity(any())).thenReturn(entityStub);
        when(sendMapperImpl.mapToMessageOutputModel(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());

        var response = sendService.sendMessage(msg);

        Assert.assertEquals(outputModel.getMongoId(), response.getMongoId());
        Assert.assertTrue(outputModel.equals(response));
    }

    @Test
    public void should_return_messageOutputModel_when_save_message() {
        var outputModel = MessageOutputModelStub.getMessageOutputModel();
        var InputModel = MessageInputModelStub.getMessageInputModel();
        when(repository.save(any())).thenReturn(MessageEntityStub.getEntity());
        when(sendMapperImpl.mapToMessageEntity(any())).thenReturn(MessageEntityStub.getEntity());
        when(sendMapperImpl.mapToMessageOutputModel(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        var response = sendService.saveMessage(InputModel);
        Assert.assertEquals(outputModel.getMongoId(), response.getMongoId());
        Assert.assertTrue(outputModel.equals(response));
    }

    @Test
    public void should_return_messageOutputModel_when_resubmit_message() {

        var outputModel = MessageOutputModelStub.getMessageOutputModel();
        var entityStub = MessageEntityStub.getEntity();

        when(sendMapperImpl.mapFomMessageEntity(any())).thenReturn(MessageInputModelStub.getMessageInputModel());
        when(sendMapperImpl.mapToMessageOutputModel(any())).thenReturn(MessageOutputModelStub.getMessageOutputModel());
        when(repository.findById(any())).thenReturn(Optional.of(MessageEntityStub.getEntity()));
        when(sendMessage.sendMessage(any())).thenReturn(MessageInputModelStub.getMessageInputModel());
        when(repository.save(any())).thenReturn(MessageEntityStub.getEntity());

        var response = sendService.resubmitMessage(entityStub.getMongoId());

        Assert.assertEquals(outputModel.getMongoId(), response.getMongoId());
        Assert.assertTrue(outputModel.equals(response));
    }

    @Test
    public void should_return_messageOutputModellist_when_find_by_subsidiary() {

        var entityStub = MessageEntityStub.getEntity();
        var entityStubList = MessageEntityStub.getEntityList();

        when(repository.findAllBySubsidiaryId(any())).thenReturn(MessageEntityStub.getEntityList());
        when(sendMapperImpl.mapToMessageOutpuModelList(any()))
                .thenReturn(MessageOutputModelStub.getMessageOutputModelList());

        var response = sendService.findBySubsidiary(entityStub.getSubsidiaryId());

        Assert.assertEquals(entityStubList.get(0).getMongoId(), response.get(0).getMongoId());
        Assert.assertEquals(entityStubList.get(0).getUserId(), response.get(0).getUserId());
        Assert.assertEquals(entityStubList.get(0).getText(), response.get(0).getText());
        Assert.assertEquals(entityStubList.get(0).getSent(), response.get(0).getSent());
        Assert.assertEquals(entityStubList.get(0).getResume(), response.get(0).getResume());
        Assert.assertEquals(entityStubList.get(0).getGroupId(), response.get(0).getGroupId());
        Assert.assertEquals(entityStubList.get(0).getCreationDate(), response.get(0).getCreationDate());
        Assert.assertEquals(entityStubList.get(0).getMessageStatus(), response.get(0).getMessageStatus());
        Assert.assertEquals(entityStubList.get(0).getSubsidiaryId(), response.get(0).getSubsidiaryId());
    }

    @Test
    public void should_return_messageOutputModellist_when_find_by_userId() {

        var entityStub = MessageEntityStub.getEntity();
        var entityStubList = MessageEntityStub.getEntityList();

        when(repository.findAllByUserId(any())).thenReturn(MessageEntityStub.getEntityList());
        when(sendMapperImpl.mapToMessageOutpuModelList(any()))
                .thenReturn(MessageOutputModelStub.getMessageOutputModelList());

        var response = sendService.findByUserId(entityStub.getUserId());

        Assert.assertEquals(entityStubList.get(0).getMongoId(), response.get(0).getMongoId());
        Assert.assertEquals(entityStubList.get(0).getUserId(), response.get(0).getUserId());
        Assert.assertEquals(entityStubList.get(0).getText(), response.get(0).getText());
        Assert.assertEquals(entityStubList.get(0).getSent(), response.get(0).getSent());
        Assert.assertEquals(entityStubList.get(0).getResume(), response.get(0).getResume());
        Assert.assertEquals(entityStubList.get(0).getGroupId(), response.get(0).getGroupId());
        Assert.assertEquals(entityStubList.get(0).getCreationDate(), response.get(0).getCreationDate());
        Assert.assertEquals(entityStubList.get(0).getMessageStatus(), response.get(0).getMessageStatus());
        Assert.assertEquals(entityStubList.get(0).getSubsidiaryId(), response.get(0).getSubsidiaryId());
    }

    @Test
    public void should_return_messageOutputModellist_when_find_by_findAllMessage() {

        var entityStub = MessageEntityStub.getEntity();
        var entityStubList = MessageEntityStub.getEntityList();

        when(repository.findAll()).thenReturn(MessageEntityStub.getEntityList());
        when(sendMapperImpl.mapToMessageOutpuModelList(any()))
                .thenReturn(MessageOutputModelStub.getMessageOutputModelList());


        var response = sendService.findAllMessage();

        Assert.assertEquals(entityStubList.get(0).getMongoId(), response.get(0).getMongoId());
        Assert.assertEquals(entityStubList.get(0).getUserId(), response.get(0).getUserId());
        Assert.assertEquals(entityStubList.get(0).getText(), response.get(0).getText());
        Assert.assertEquals(entityStubList.get(0).getSent(), response.get(0).getSent());
        Assert.assertEquals(entityStubList.get(0).getResume(), response.get(0).getResume());
        Assert.assertEquals(entityStubList.get(0).getGroupId(), response.get(0).getGroupId());
        Assert.assertEquals(entityStubList.get(0).getCreationDate(), response.get(0).getCreationDate());
        Assert.assertEquals(entityStubList.get(0).getMessageStatus(), response.get(0).getMessageStatus());
        Assert.assertEquals(entityStubList.get(0).getSubsidiaryId(), response.get(0).getSubsidiaryId());
    }

    @Test
    public void should_return_messageOutputModellist_when_find_by_groupId() {

        var entityStub = MessageEntityStub.getEntity();
        var entityStubList = MessageEntityStub.getEntityList();

        when(repository.findAllByGroupId(any())).thenReturn(MessageEntityStub.getEntityList());
        when(sendMapperImpl.mapToMessageOutpuModelList(any()))
                .thenReturn(MessageOutputModelStub.getMessageOutputModelList());

        var response = sendService.findByGroupId(any());

        Assert.assertEquals(entityStubList.get(0).getMongoId(), response.get(0).getMongoId());
        Assert.assertEquals(entityStubList.get(0).getUserId(), response.get(0).getUserId());
        Assert.assertEquals(entityStubList.get(0).getText(), response.get(0).getText());
        Assert.assertEquals(entityStubList.get(0).getSent(), response.get(0).getSent());
        Assert.assertEquals(entityStubList.get(0).getResume(), response.get(0).getResume());
        Assert.assertEquals(entityStubList.get(0).getGroupId(), response.get(0).getGroupId());
        Assert.assertEquals(entityStubList.get(0).getCreationDate(), response.get(0).getCreationDate());
        Assert.assertEquals(entityStubList.get(0).getMessageStatus(), response.get(0).getMessageStatus());
        Assert.assertEquals(entityStubList.get(0).getSubsidiaryId(), response.get(0).getSubsidiaryId());
    }

    @Test
    public void should_return_true_when_readConfirmation() {

        var entityStub = MessageEntityStub.getEntity();
        var entityStubList = MessageEntityStub.getEntityList();
        var InputModel = MessageInputModelStub.getMessageInputModel();

        when(sendMapperImpl.mapFomMessageEntity(any()))
                .thenReturn(MessageInputModelStub.getMessageInputModel());

        when(repository.findById(any())).thenReturn(Optional.of(MessageEntityStub.getEntity()));

        var response = sendService.readConfirmation(any());

        Assert.assertTrue(response == true);

    }
}
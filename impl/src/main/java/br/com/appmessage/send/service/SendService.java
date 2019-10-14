package br.com.appmessage.send.service;

import br.com.appmessage.send.enuns.MessageStatus;
import br.com.appmessage.send.exception.model.NotFoundException;
import br.com.appmessage.send.mapper.SendMapperImpl;
import br.com.appmessage.send.model.input.MessageInputModel;
import br.com.appmessage.send.model.output.MessageOutputModel;
import br.com.appmessage.send.repository.SendRepository;
import br.com.appmessage.send.repository.entity.MessageEntity;
import br.com.appmessage.send.sendmessage.SendMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class SendService {

    private final SendMessage sendMessage;
    private final SendRepository sendRepository;
    private final SendMapperImpl sendMapperImpl;

    public MessageOutputModel sendMessage(MessageInputModel messageInputModel) {
        messageInputModel=sendMessage.sendMessage(setStatusMessage(messageInputModel));
        messageInputModel.setSent(true); // msg enviada.
        return saveMessage(messageInputModel);
    }

    public MessageOutputModel resubmitMessage(String messageId) {
        var result = findByMongoId(messageId);
        return sendMessage(sendMapperImpl.mapFomMessageEntity(result));
    }

    public MessageOutputModel saveMessage(MessageInputModel messageInputModel) {
        return sendMapperImpl
                .mapToMessageOutputModel(sendRepository
                        .save(sendMapperImpl
                                .mapToMessageEntity(messageInputModel)));
    }

    public MessageEntity findByMongoId(String mongoId) {
        return sendRepository
                .findById(mongoId)
                .orElseThrow(() -> new NotFoundException("Mensagem " + mongoId + " não encontrada."));
    }

    public List<MessageOutputModel> findAllMessage() {
        var response = sendMapperImpl.mapToMessageOutpuModelList(sendRepository.findAll());
        if (response.isEmpty()) throw new NotFoundException("Não existe mensagens para listar.");
        return response;
    }

    public List<MessageOutputModel> findBySubsidiary(Long subsidiaryId) {
        var response = sendMapperImpl.mapToMessageOutpuModelList(sendRepository.findAllBySubsidiaryId(subsidiaryId));
        if (response.isEmpty()) throw new NotFoundException("Subsidiaria não encontrada !");
        return response;
    }

    public List<MessageOutputModel> findByGroupId(String groupId) {
        var response = sendMapperImpl.mapToMessageOutpuModelList(sendRepository.findAllByGroupId(groupId));
        if (response.isEmpty()) throw new NotFoundException("Grupo não encontrado !");
        return response;
    }

    public List<MessageOutputModel> findByUserId(Long userId) {
        var response = sendMapperImpl.mapToMessageOutpuModelList(sendRepository.findAllByUserId(userId));
        if (response.isEmpty()) throw new NotFoundException("Usuário não encontrado !");
        return response;
    }

    public boolean readConfirmation(String mongoId) {
        var message = sendMapperImpl.mapFomMessageEntity(findByMongoId(mongoId));
        message.setMessageStatus(getMessageStatus(message, MessageStatus.READ_MESSAGE.getStatus()));
        saveMessage(message);
        return true;
    }

    protected MessageInputModel setStatusMessage(MessageInputModel msg) {
        List<String> messageStatus = new ArrayList<>();

        if (msg.getMessageStatus()==null) {
            messageStatus.add(MessageStatus.NEW_MESSAGE.getStatus());
        } else {
            messageStatus=msg.getMessageStatus();
            messageStatus.add(MessageStatus.RESUBMITTED.getStatus());
        }
        msg.setMessageStatus(messageStatus);
        return msg;
    }

    private List<String> getMessageStatus(MessageInputModel msg, String status) {
        var messageStatus = msg.getMessageStatus();
        messageStatus.add(status);

        messageStatus.forEach(a-> System.out.println(a));
        return messageStatus;
    }
}



package br.com.appmessage.contract.v1.facade;


import br.com.appmessage.contract.v1.mapper.MessageMapper;
import br.com.appmessage.contract.v1.model.request.MessageRequest;
import br.com.appmessage.contract.v1.model.response.MessageResponse;
import br.com.appmessage.send.facade.SendFacadeImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MessageFacadeContract {

    private final SendFacadeImpl sendFacadeImpl;

    public MessageResponse sendMessage(MessageRequest appMessageRequest) {
        return MessageMapper.mapToMessageResponse(
                sendFacadeImpl.sendMessage(MessageMapper.mapToMessageInputModel(appMessageRequest)));
    }

    public List<MessageResponse> findAllMessage() {
        return MessageMapper.mapToMessageResponseList(sendFacadeImpl.findAllMessage());
    }

    public List<MessageResponse> findBySubsidiary(Long subsidiaryId) {
        return MessageMapper.mapToMessageResponseList(sendFacadeImpl.findBySubsidiary(subsidiaryId));
    }

    public List<MessageResponse> findByGroupId(String groupId) {
        return MessageMapper.mapToMessageResponseList(sendFacadeImpl.findByGroupId(groupId));
    }

    public List<MessageResponse> findByUserId(Long userId) {
        return MessageMapper.mapToMessageResponseList(sendFacadeImpl.findByUserId(userId));
    }

    public MessageResponse findByMessageId(String messageId){
           return MessageMapper.mapToMessageResponse(sendFacadeImpl.findByMessageId(messageId));
    }

    public MessageResponse resubmitMessage(String messageId){
        return MessageMapper.mapToMessageResponse(sendFacadeImpl.resubmitMessage(messageId));
    }
}

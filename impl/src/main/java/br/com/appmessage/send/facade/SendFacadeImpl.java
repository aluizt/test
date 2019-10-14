package br.com.appmessage.send.facade;

import br.com.appmessage.send.mapper.SendMapperImpl;
import br.com.appmessage.send.model.input.MessageInputModel;
import br.com.appmessage.send.model.output.MessageOutputModel;
import br.com.appmessage.send.service.SendService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SendFacadeImpl {

    private final SendService sendService;
    private final SendMapperImpl sendMapperImpl;

    public MessageOutputModel sendMessage(MessageInputModel messageInputModel){

            return sendService.sendMessage(sendMapperImpl.mapToMessageInputModel(sendService.saveMessage(messageInputModel)));
    }

    public List<MessageOutputModel> findAllMessage(){
          return  sendService.findAllMessage();
    }

    public List<MessageOutputModel> findBySubsidiary(Long subsidiaryId){
        return  sendService.findBySubsidiary(subsidiaryId);
    }

    public List<MessageOutputModel> findByGroupId(String groupId){
        return  sendService.findByGroupId(groupId);
    }

    public List<MessageOutputModel> findByUserId(Long userId){
        return  sendService.findByUserId(userId);
    }

    public MessageOutputModel findByMessageId(String messageId){
        return sendMapperImpl.mapToMessageOutputModel(sendService.findByMongoId(messageId));
    }

    public MessageOutputModel resubmitMessage(String messageId){

        return sendService.resubmitMessage(messageId);
    }
}

package br.com.appmessage.send.mapper;

import br.com.appmessage.send.model.input.MessageInputModel;
import br.com.appmessage.send.model.output.MessageOutputModel;
import br.com.appmessage.send.repository.entity.MessageEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendMapperImpl {

    public List<MessageOutputModel> mapToMessageOutpuModelList(List<MessageEntity> messageEntityList) {
        List<MessageOutputModel> messageOutputModelList = new ArrayList<>();
        messageEntityList.forEach(message -> messageOutputModelList.add(mapToMessageOutputModel(message)));
        return messageOutputModelList;
    }

    public MessageOutputModel mapToMessageOutputModel(MessageEntity messageEntity) {
        return MessageOutputModel.builder()
                .mongoId(messageEntity.getMongoId())
                .creationDate(messageEntity.getCreationDate())
                .messageStatus(messageEntity.getMessageStatus())
                .groupId(messageEntity.getGroupId())
                .resume(messageEntity.getResume())
                .subsidiaryId(messageEntity.getSubsidiaryId())
                .text(messageEntity.getText())
                .userId(messageEntity.getUserId())
                .sent(messageEntity.getSent())
                .build();
    }

    public MessageInputModel mapFomMessageEntity(MessageEntity messageEntity){
            return MessageInputModel.builder()
                    .sent(messageEntity.getSent())
                    .mongoId(messageEntity.getMongoId())
                    .messageStatus(messageEntity.getMessageStatus())
                    .subsidiaryId(messageEntity.getSubsidiaryId())
                    .groupId(messageEntity.getGroupId())
                    .userId(messageEntity.getUserId())
                    .creationDate(messageEntity.getCreationDate())
                    .resume(messageEntity.getResume())
                    .text(messageEntity.getText())
                    .build();
    }

    public MessageInputModel mapToMessageInputModel(MessageOutputModel messageOutputModel) {
        return MessageInputModel.builder()
                .mongoId(messageOutputModel.getMongoId())
                .text(messageOutputModel.getText())
                .resume(messageOutputModel.getResume())
                .creationDate(messageOutputModel.getCreationDate())
                .userId(messageOutputModel.getUserId())
                .groupId(messageOutputModel.getGroupId())
                .subsidiaryId(messageOutputModel.getSubsidiaryId())
                .messageStatus(messageOutputModel.getMessageStatus())
                .sent(messageOutputModel.getSent())
                .build();
    }

    public MessageEntity mapToMessageEntity(MessageInputModel messageInputModel) {
        return MessageEntity.builder()
                .mongoId(messageInputModel.getMongoId())
                .messageStatus(messageInputModel.getMessageStatus())
                .creationDate(messageInputModel.getCreationDate())
                .groupId(messageInputModel.getGroupId())
                .resume(messageInputModel.getResume())
                .subsidiaryId(messageInputModel.getSubsidiaryId())
                .text(messageInputModel.getText())
                .userId(messageInputModel.getUserId())
                .sent(messageInputModel.getSent())
                .build();
    }
}

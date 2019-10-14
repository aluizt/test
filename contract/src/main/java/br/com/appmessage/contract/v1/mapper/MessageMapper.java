package br.com.appmessage.contract.v1.mapper;

import br.com.appmessage.contract.v1.model.request.MessageRequest;
import br.com.appmessage.contract.v1.model.response.MessageResponse;
import br.com.appmessage.send.model.input.MessageInputModel;
import br.com.appmessage.send.model.output.MessageOutputModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageMapper {

    public static MessageInputModel mapToMessageInputModel(MessageRequest appMessageRequest) {
        return MessageInputModel.builder()
                .subsidiaryId(appMessageRequest.getSubsidiaryId())
                .groupId(appMessageRequest.getGroupId())
                .userId(appMessageRequest.getUserId())
                .creationDate(appMessageRequest.getCreationDate())
                .resume(appMessageRequest.getResume())
                .text(appMessageRequest.getText())
                .build();
    }


    public static MessageResponse mapToMessageResponse(MessageOutputModel messageOutputModel) {
        return MessageResponse.builder()
                .mongoId(messageOutputModel.getMongoId())
                .creationDate(messageOutputModel.getCreationDate())
                .messageStatus(messageOutputModel.getMessageStatus())
                .groupId(messageOutputModel.getGroupId())
                .resume(messageOutputModel.getResume())
                .subsidiaryId(messageOutputModel.getSubsidiaryId())
                .text(messageOutputModel.getText())
                .userId(messageOutputModel.getUserId())
                .build();
    }

    public static List<MessageResponse> mapToMessageResponseList(List<MessageOutputModel> messageOutputModelList) {
        List<MessageResponse> messageResponseList = new ArrayList<>();
        messageOutputModelList.forEach(message -> messageResponseList.add(mapToMessageResponse(message)));
        return messageResponseList;
    }

    public static MessageRequest mapFromParam(Long subsidiaryId,
                                              String resume,
                                              String text) {

        return MessageRequest.builder()
                .subsidiaryId(subsidiaryId)
                .creationDate(LocalDate.now())
                .resume(resume)
                .text(text)
                .build();
    }
}

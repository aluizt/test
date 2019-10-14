package br.com.appmessage.contract.v1.stubs;

import br.com.appmessage.contract.v1.model.request.MessageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageRequestStub {

    public static  MessageRequest  getMessageRequest(){
        return MessageRequest.builder()
                .creationDate(LocalDate.now())
                .groupId("P100")
                .resume("Conforme nova norma...")
                .subsidiaryId(1000l)
                .text("Conforme nova norma, para identificar")
                .userId(1001l)
                .build();
    }

    public static List<MessageRequest> getMessageRequestList(){
        List<MessageRequest> list = new ArrayList<>();
        list.add(getMessageRequest());
        return list;
    }
}

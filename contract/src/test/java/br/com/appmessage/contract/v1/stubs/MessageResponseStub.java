package br.com.appmessage.contract.v1.stubs;

import br.com.appmessage.contract.v1.model.response.MessageResponse;
import br.com.appmessage.send.model.output.MessageOutputModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageResponseStub {

    public static MessageResponse  getMessageResponse(){

        List<String> list = new ArrayList<>();
        list.add("new");

        return MessageResponse .builder()
                .mongoId("5d9f0a176517ed2dc568bb4b")
                .userId(1001l)
                .text("Conforme nova norma, para identificar")
                .subsidiaryId(100l)
                .resume("Conforme nova norma...")
                .groupId("P100")
                .creationDate(LocalDate.now())
                .messageStatus(list)
                .build();
    }

    public static List<MessageResponse> getMessageResponseList(){
        List<MessageResponse> list = new ArrayList<>();
        list.add(getMessageResponse());
        return list;
    }


}

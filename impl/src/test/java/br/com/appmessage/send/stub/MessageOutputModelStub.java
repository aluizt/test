package br.com.appmessage.send.stub;

import br.com.appmessage.send.model.output.MessageOutputModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageOutputModelStub {

    public static MessageOutputModel getMessageOutputModel(){
        List<String> list = new ArrayList<>();
        list.add("new");

        return MessageOutputModel.builder()
                .mongoId("5d9f0a176517ed2dc568bb4b")
                .userId(1001l)
                .text("Conforme nova norma, para identificar")
                .subsidiaryId(100l)
                .resume("Conforme nova norma...")
                .groupId("P100")
                .creationDate(LocalDate.now())
                .messageStatus(list)
                .sent(true)
                .build();
    }

    public static List<MessageOutputModel> getMessageOutputModelList(){
        List<MessageOutputModel> list = new ArrayList<>();
        list.add(getMessageOutputModel());
        return list;
    }
}

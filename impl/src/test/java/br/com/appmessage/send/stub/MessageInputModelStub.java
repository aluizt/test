package br.com.appmessage.send.stub;

import br.com.appmessage.send.model.input.MessageInputModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageInputModelStub {

    public static MessageInputModel getMessageInputModel(){
        List<String> list = new ArrayList<>();
        list.add("new");
        return MessageInputModel.builder()
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

    public static List<MessageInputModel> getMessageInputModelList(){
        List<MessageInputModel> list = new ArrayList<>();
        list.add(getMessageInputModel());
        return list;
    }

}

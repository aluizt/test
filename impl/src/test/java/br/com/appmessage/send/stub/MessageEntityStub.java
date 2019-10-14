package br.com.appmessage.send.stub;

import br.com.appmessage.send.repository.entity.MessageEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MessageEntityStub {

    public static MessageEntity getEntity() {
        List<String> list = new ArrayList<>();
        list.add("new");
        return MessageEntity.builder()
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

    public static List<MessageEntity> getEntityList() {
        List<MessageEntity> list = new ArrayList<>();
        list.add(getEntity());
        return list;
    }
}

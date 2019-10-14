package br.com.appmessage.send.util;

import br.com.appmessage.send.model.input.MessageInputModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrintMessage {

    public static void printMsg(MessageInputModel messageInputModel) {

        log.info("MSG : " + messageInputModel.toString());
        log.info("Hrader : " + messageInputModel);
        log.info("Subsidiaria : " + messageInputModel.getSubsidiaryId());
        log.info("Data        : " + messageInputModel.getCreationDate());
        log.info("Resumo      : " + messageInputModel.getResume());
        log.info("Texto       : " + messageInputModel.getText());
        var msgStatus = messageInputModel.getMessageStatus();
        msgStatus.forEach(status -> log.info("Status : "+status.toString()));
    }
}

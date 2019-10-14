package br.com.appmessage.send.util;

import br.com.appmessage.send.model.input.MessageInputModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private JsonUtil(){}

    public static byte[] toJson(MessageInputModel messageInputModel) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsBytes(messageInputModel);
        } catch (JsonProcessingException e) {
            log.error("Erro em JsonUtil : "+e);
            return new byte[0];
        }
    }
}

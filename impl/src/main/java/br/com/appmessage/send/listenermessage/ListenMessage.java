package br.com.appmessage.send.listenermessage;

import br.com.appmessage.send.exception.model.NotFoundException;
import br.com.appmessage.send.model.input.MessageInputModel;
import br.com.appmessage.send.service.SendService;
import br.com.appmessage.send.util.PrintMessage;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class ListenMessage {

    private final SendService sendService;
    private static final String EXCHANGE_NAME = "${rabbit.exchange}";
    private static final String QUEUE_NAME = "${rabbit.queue.listen}";

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = QUEUE_NAME),
            exchange = @Exchange(value = EXCHANGE_NAME, type = "fanout")))
    @RabbitHandler
    public void onOrderMessage(@Payload MessageInputModel messageInputModel,
                               @Headers Map<String, Object> headers,
                               @Header(AmqpHeaders.DELIVERY_TAG) long tag,
                               Channel channel) {
        verifyMessage(channel, headers, tag, messageInputModel);
    }

    private void verifyMessage(Channel channel, Map<String, Object> headers, long tag, MessageInputModel msg) {

        sendService.readConfirmation(readHeader(headers, "messageId"));

        try {
            channel.basicAck(tag, false);
            PrintMessage.printMsg(msg);
        } catch (IOException  e) {
            log.error("Erro em ListenMessage : "+e);
        }
    }

    private String readHeader(Map<String, Object> headers, String key) {
        var response = headers.entrySet().stream()
                .filter(k -> k.getKey().equals(key))
                .collect(Collectors.toList());
        if (response.isEmpty()) throw new NotFoundException("Id da mensagem recebida não cadastrado!");
        return (String) response.get(0).getValue();
    }

}

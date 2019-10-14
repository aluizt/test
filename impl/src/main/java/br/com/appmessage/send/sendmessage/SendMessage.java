package br.com.appmessage.send.sendmessage;

import br.com.appmessage.send.conection.ConectionFactory;
import br.com.appmessage.send.enuns.ExchangeType;
import br.com.appmessage.send.enuns.MessageStatus;
import br.com.appmessage.send.exception.model.SendMessageException;
import br.com.appmessage.send.model.input.MessageInputModel;
import br.com.appmessage.send.util.JsonUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Component
@AllArgsConstructor
@Slf4j
public class SendMessage {

    private static final String EXCHANGE_NAME = "defaultSendExchange";
    private static final String QUEUE_NAME = "allSubsidiaryQueue";
    private static final String ROUTING_KEY = "*.*";
    private static final boolean DURABLE = true;
    private static final boolean EXCLUSIVE = false;
    private static final boolean AUTO_DELETE = false;

    public MessageInputModel sendMessage(MessageInputModel msg) {

        var factory = new ConectionFactory().getConection();

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel() ) {

            channel.queueDeclare(QUEUE_NAME, DURABLE, EXCLUSIVE, AUTO_DELETE, null);
            channel.exchangeDeclare(EXCHANGE_NAME, ExchangeType.FANOUT.getExchangeName(), DURABLE);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, getProps(msg), JsonUtil.toJson(msg));

            return msg;

        } catch (TimeoutException | IOException e) {
            log.error("Erro em SendMessage : "+e);
            throw new SendMessageException("Não foi possível enviar a mensagem !");
        }
    }

    private AMQP.BasicProperties getProps(MessageInputModel msg) {
        return new AMQP.BasicProperties.Builder()
                .contentType("application/json")
                .headers(getHeader(msg))
                .build();
    }

    private Map<String, Object> getHeader(MessageInputModel msg) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("subsidiaryId", msg.getSubsidiaryId());
        if(msg.getMessageStatus()==null) {
            headers.put("messageStatus", MessageStatus.NEW_MESSAGE.getStatus());
        }else{
            headers.put("messageStatus", MessageStatus.RESUBMITTED.getStatus());
        }
        headers.put("messageId", msg.getMongoId());
        return headers;
    }

}

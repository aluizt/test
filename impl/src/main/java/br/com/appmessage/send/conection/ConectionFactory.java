package br.com.appmessage.send.conection;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class ConectionFactory {

    public ConnectionFactory getConection() {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        return factory;
    }
}

package br.com.appmessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RabbitListener
public class AppMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMessageApplication.class, args);
    }

}

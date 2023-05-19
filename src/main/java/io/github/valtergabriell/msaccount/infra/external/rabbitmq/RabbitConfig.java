package io.github.valtergabriell.msaccount.infra.external.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue deleteCardQueue(){
        return new Queue("delete-card-queue", true);
    }

}

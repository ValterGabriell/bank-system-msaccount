package io.github.valtergabriell.msaccount.infra.external.rabbitmq.send;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCardRequest {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public void deleteCardRequest(String id) {
        rabbitTemplate.convertAndSend(queue.getName(), id);
    }
}

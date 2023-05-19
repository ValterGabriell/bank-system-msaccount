package io.github.valtergabriell.msaccount.infra.external.rabbitmq.receive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.valtergabriell.msaccount.application.ClientService;
import io.github.valtergabriell.msaccount.application.dto.ReceiveDataForCreateNewClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteClient {

    private final ClientService clientService;

    public DeleteClient(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = {"delete-client-queue"})
    public void receiveRequest(@Payload String payload) {
        ObjectMapper om = new ObjectMapper();
        try {
            log.info("Recebendo dados: {}", payload);
            String value = om.readValue(payload, String.class);
            clientService.deleteAccountByIdentifier(value);
        } catch (JsonProcessingException e) {
            log.error("Falha ao receber dados: {}", payload);
        }
    }


}

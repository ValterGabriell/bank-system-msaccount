package io.github.valtergabriell.msaccount.infra.external.rabbitmq.receive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.valtergabriell.msaccount.application.ClientService;
import io.github.valtergabriell.msaccount.application.dto.ReceiveDataForCreateNewClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReceiveNewClient {

    private final ClientService clientService;

    public ReceiveNewClient(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = {"create-client-queue"})
    public void receiveRequest(@Payload String payload) {
        ObjectMapper om = new ObjectMapper();
        try {
            log.info("Recebendo dados: {}", payload);
            ReceiveDataForCreateNewClient receiveDataForCreateNewClient = om.readValue(payload, ReceiveDataForCreateNewClient.class);
            clientService.createClient(receiveDataForCreateNewClient);
        } catch (JsonProcessingException e) {
            log.error("Erro ao receber solicitacao de criar novo cliente: {}", e.getMessage());
        }
    }


}

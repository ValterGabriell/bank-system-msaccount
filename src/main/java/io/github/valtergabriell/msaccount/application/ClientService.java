package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.ReceiveDataForCreateNewClient;
import io.github.valtergabriell.msaccount.application.exception.RequestExceptions;
import io.github.valtergabriell.msaccount.application.validator.IdentifierValidator;
import io.github.valtergabriell.msaccount.entity.Client;
import io.github.valtergabriell.msaccount.infra.ClientRepository;
import io.github.valtergabriell.msaccount.infra.external.rabbitmq.send.DeleteCardRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.github.valtergabriell.msaccount.application.exception.ExceptionsValues.*;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final DeleteCardRequest deleteCardRequest;

    public ClientService(ClientRepository clientRepository, DeleteCardRequest deleteCardRequest) {
        this.clientRepository = clientRepository;
        this.deleteCardRequest = deleteCardRequest;
    }
    public void createClient(ReceiveDataForCreateNewClient request) {
        if (!isIdAlreadySavedOnDatabase(request.getId())) {
            Client client = request.toModel();
            clientRepository.save(client);
        } else {
            throw new RequestExceptions(ALREADY_ON_DATABASE);
        }
    }

    public Client getAccountByIdentifier(String id) {
        validatingIdentifier(id);
        if (isIdAlreadySavedOnDatabase(id)) {
            Optional<Client> client = clientRepository.findById(id);
            return client.get();
        } else {
            throw new RequestExceptions(USER_NOT_FOUND);
        }
    }

    private static void validatingIdentifier(String id) {
        IdentifierValidator cv = new IdentifierValidator();
        cv.validateFieldSize(id);
        cv.hasOnlyOneDigitOnWholeNumber(id);
    }

    public void deleteAccountByIdentifier(String id) {
        validatingIdentifier(id);
        if (isIdAlreadySavedOnDatabase(id)) {
            clientRepository.deleteById(id);
            deleteCardRequest.deleteCardRequest(id);
        } else {
            throw new RequestExceptions(USER_NOT_FOUND);
        }
    }

    private boolean isIdAlreadySavedOnDatabase(String id) {
        return clientRepository.findById(id).isPresent();
    }
}

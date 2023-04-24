package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.CommonResponse;
import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import io.github.valtergabriell.msaccount.application.exception.RequestException;
import io.github.valtergabriell.msaccount.application.validator.CPFValidator;
import io.github.valtergabriell.msaccount.application.validator.EmailValidator;
import io.github.valtergabriell.msaccount.application.validator.PhoneNumberValidator;
import io.github.valtergabriell.msaccount.entity.Client;
import io.github.valtergabriell.msaccount.infra.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import static io.github.valtergabriell.msaccount.application.exception.ExceptionsValues.*;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public CommonResponse<CreateClientResponse> createClient(CreateClientRequest request, URI headerLocation) {
        CPFValidator cpfValidator = new CPFValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        CommonResponse<CreateClientResponse> commonResponse = new CommonResponse<>();

        boolean isPhoneNumberCorrectSize = phoneNumberValidator.validateFieldSize(request.getClientPhoneNumber());
        boolean hasPhoneNumberOnlyOneDigit = phoneNumberValidator.hasOnlyOneDigitOnWholeNumber(request.getClientPhoneNumber());
        boolean isIdCorrectSize = cpfValidator.validateFieldSize(request.getId());
        boolean hasIdOnlyOneDigit = cpfValidator.hasOnlyOneDigitOnWholeNumber(request.getId());
        boolean isValidEmail = EmailValidator.isValidEmail(request.getClientEmail());

        if (isIdCorrectSize
                && isValidEmail
                && isPhoneNumberCorrectSize
                && hasIdOnlyOneDigit
                && hasPhoneNumberOnlyOneDigit
                && birthDateIsBiggerThan18YearsSinceCurrentDate(request.getBirthDate())) {
            if (!isIdAlreadySavedOnDatabase(request.getId())) {
                //saving at database
                Client client = request.toModel();
                client.setAccountDate(LocalDate.now());
                Client clientSaved = clientRepository.save(client);

                //Creating object to response
                CreateClientResponse clientResponse = new CreateClientResponse();
                clientResponse.setId(clientSaved.getId());
                clientResponse.setClientEmail(clientSaved.getClientEmail());
                clientResponse.setClientName(clientSaved.getClientName());
                clientResponse.setClientPhoneNumber(clientSaved.getClientPhoneNumber());
                clientResponse.setGender(clientSaved.getGender());
                clientResponse.setBirthDate(clientSaved.getBirthDate());
                clientResponse.setAccountDate(clientSaved.getAccountDate());
                clientResponse.setIncome(clientSaved.getIncome());

                commonResponse.setData(clientResponse);
                commonResponse.setMessage(ACCOUNT_CREATED);
                commonResponse.setHeaderLocation(headerLocation.toString());
            } else {
                throw new RequestException(ALREADY_ON_DATABASE);
            }
        }
        return commonResponse;
    }

    public Client getAccountByCpf(String id) {
        if (isIdAlreadySavedOnDatabase(id)) {
            Optional<Client> client = clientRepository.findById(id);
            return client.get();
        } else {
            throw new RequestException(USER_NOT_FOUND);
        }
    }

    public void deleteAccountByCpf(String id) {
        if (isIdAlreadySavedOnDatabase(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new RequestException(USER_NOT_FOUND);
        }
    }

    private boolean birthDateIsBiggerThan18YearsSinceCurrentDate(LocalDate birthDate) {
        int birthDateYear = birthDate.getYear();
        int currentYear = LocalDate.now().getYear();
        boolean isMajorAge = currentYear - birthDateYear > 17;
        if (!isMajorAge) {
            throw new RequestException(AGE_INVALID);
        }
        return true;
    }

    private boolean isIdAlreadySavedOnDatabase(String id) {
        return clientRepository.findById(id).isPresent();
    }
}

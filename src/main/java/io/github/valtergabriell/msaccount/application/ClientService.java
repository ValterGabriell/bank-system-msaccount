package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import io.github.valtergabriell.msaccount.application.validator.CPFValidator;
import io.github.valtergabriell.msaccount.application.validator.PhoneNumberValidator;
import io.github.valtergabriell.msaccount.infra.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public CreateClientResponse createClient(CreateClientRequest request) {
        CPFValidator cpfValidator = new CPFValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();


        boolean isCPFCorrectSize = cpfValidator.validateFieldSize(request.getCpf());
        boolean isPhoneNumberCorrectSize = phoneNumberValidator.validateFieldSize(request.getClientPhoneNumber());


        if (isCPFCorrectSize && isPhoneNumberCorrectSize) {
            log.info("VALIDACOES PASSARAM");
        }else{
            log.error("VALIDACOES ERRO");
        }

        return new CreateClientResponse();

    }
}

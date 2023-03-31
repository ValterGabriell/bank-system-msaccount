package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.CommonResponse;
import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import io.github.valtergabriell.msaccount.application.validator.CPFValidator;
import io.github.valtergabriell.msaccount.application.validator.EmailValidator;
import io.github.valtergabriell.msaccount.application.validator.PhoneNumberValidator;
import io.github.valtergabriell.msaccount.infra.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public CommonResponse<CreateClientResponse> createClient(CreateClientRequest request, URI headerLocation) {
        CPFValidator cpfValidator = new CPFValidator();
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        CommonResponse<CreateClientResponse> commonResponse = new CommonResponse<>();

        boolean isPhoneNumberCorrectSize = phoneNumberValidator.validateFieldSize(request.getClientPhoneNumber());
        boolean hasPhoneNumberOnlyOneDigit = phoneNumberValidator.hasOnlyOneDigitOnWholeNumber(request.getClientPhoneNumber());
        boolean isCPFCorrectSize = cpfValidator.validateFieldSize(request.getCpf());
        boolean hasCPFOnlyOneDigit = cpfValidator.hasOnlyOneDigitOnWholeNumber(request.getCpf());
        boolean isValidEmail = EmailValidator.isValidEmail(request.getClientEmail());




            if (isCPFCorrectSize) {
                if (isPhoneNumberCorrectSize) {
                    if (!hasCPFOnlyOneDigit) {
                        if (!hasPhoneNumberOnlyOneDigit) {
                            if (isValidEmail) {
                                log.info("Validations Done! \n " + request);

                                log.info("Saving at database");

                                //TODO: save at database


                                //Creating object to response
                                CreateClientResponse clientResponse = new CreateClientResponse();


                                clientResponse.setCpf(request.getCpf());
                                clientResponse.setClientEmail(request.getClientEmail());
                                clientResponse.setClientName(request.getClientName());
                                clientResponse.setClientPhoneNumber(request.getClientPhoneNumber());
                                clientResponse.setGender(request.getGender());
                                clientResponse.setBirthDate(request.getBirthDate());
                                clientResponse.setAccountDate(LocalDateTime.now().toLocalDate());

                                commonResponse.setData(clientResponse);
                                commonResponse.setMessage("Conta criada com sucesso!");
                                commonResponse.setHeaderLocation(headerLocation.toString());

                                log.info("Objects created \n " + commonResponse);

                            } else {
                                log.error("Email is not valid");
                                commonResponse.setData(null);
                                commonResponse.setMessage("Email inválido");
                            }
                        } else {
                            log.error("Phone number cannot have only one digit");
                            commonResponse.setData(null);
                            commonResponse.setMessage("Número de telefone inválido");
                        }
                    } else {
                        log.error("CPF cannot have only one digit");
                        commonResponse.setData(null);
                        commonResponse.setMessage("CPF inválido");
                    }
                } else {
                    log.error("Phone number size incorrect");
                    commonResponse.setData(null);
                    commonResponse.setMessage("Tamanho de telefone incorreto");
                }
            } else {
                log.error("CPF size incorrect");
                commonResponse.setData(null);
                commonResponse.setMessage("Tamanho CPF incorreto");
            }

        return commonResponse;

    }

}

package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.CommonResponse;
import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import io.github.valtergabriell.msaccount.application.dto.CreateClientResponse;
import io.github.valtergabriell.msaccount.application.exception.NotFoundException;
import io.github.valtergabriell.msaccount.application.validator.CPFValidator;
import io.github.valtergabriell.msaccount.application.validator.EmailValidator;
import io.github.valtergabriell.msaccount.application.validator.PhoneNumberValidator;
import io.github.valtergabriell.msaccount.entity.Client;
import io.github.valtergabriell.msaccount.infra.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

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
                            if (birthDateIsBiggerThan18YearsSinceCurrentDate(request.getBirthDate())) {
                                if (!cpfAlreadySavedOnDatabase(request.getCpf())){
                                    log.info("Validations Done! \n " + request);

                                    log.info("Saving at database");

                                    //saving at database
                                    Client client = request.toModel();
                                    client.setAccountDate(LocalDate.now());
                                    Client clientSaved = clientRepository.save(client);


                                    //Creating object to response
                                    CreateClientResponse clientResponse = new CreateClientResponse();
                                    clientResponse.setCpf(clientSaved.getCpf());
                                    clientResponse.setClientEmail(clientSaved.getClientEmail());
                                    clientResponse.setClientName(clientSaved.getClientName());
                                    clientResponse.setClientPhoneNumber(clientSaved.getClientPhoneNumber());
                                    clientResponse.setGender(clientSaved.getGender());
                                    clientResponse.setBirthDate(clientSaved.getBirthDate());
                                    clientResponse.setAccountDate(clientSaved.getAccountDate());
                                    clientResponse.setIncome(clientSaved.getIncome());

                                    commonResponse.setData(clientResponse);
                                    commonResponse.setMessage("Conta criada com sucesso!");
                                    commonResponse.setHeaderLocation(headerLocation.toString());
                                }else{
                                    log.error("CPF not valid");
                                    commonResponse.setData(null);
                                    commonResponse.setMessage("CPF já cadastrado no nosso banco de dados!!!");
                                }

                            } else {
                                log.error("Age not valid");
                                commonResponse.setData(null);
                                commonResponse.setMessage("Você precisa ter mais de 17 anos para abrir uma conta!");
                            }
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

    public Client getAccountByCpf(String cpf) throws NotFoundException {
        Optional<Client> client = clientRepository.findById(cpf);
        return client.get();
    }

    private boolean birthDateIsBiggerThan18YearsSinceCurrentDate(LocalDate birthDate) {
        int birthDateYear = birthDate.getYear();
        int currentYear = LocalDate.now().getYear();
        return currentYear - birthDateYear > 17;
    }

    private boolean cpfAlreadySavedOnDatabase(String cpf) {
        return clientRepository.findById(cpf).isPresent();
    }
}

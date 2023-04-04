package io.github.valtergabriell.msaccount.application.dto;

import io.github.valtergabriell.msaccount.entity.Client;
import io.github.valtergabriell.msaccount.entity.enums.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateClientRequest {
    private String cpf;
    private LocalDate birthDate;
    private String clientName;
    private String clientPhoneNumber;
    private String clientEmail;
    private Gender gender;
    private String password;
    private BigDecimal income;

    public Client toModel() {
        return new Client(cpf, birthDate, clientName, clientPhoneNumber, clientEmail, gender, password, income);
    }
}

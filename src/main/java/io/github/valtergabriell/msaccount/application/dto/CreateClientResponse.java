package io.github.valtergabriell.msaccount.application.dto;


import io.github.valtergabriell.msaccount.entity.enums.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateClientResponse {
    private String id;
    private LocalDate birthDate;
    private LocalDate accountDate;
    private String clientName;
    private String clientPhoneNumber;
    private String clientEmail;
    private Gender gender;
    private BigDecimal income;
}

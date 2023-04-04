package io.github.valtergabriell.msaccount.entity;


import io.github.valtergabriell.msaccount.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @Column(length = 11, nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate birthDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate accountDate;

    @Column(length = 80, nullable = false)
    private String clientName;

    @Column(length = 16, nullable = false)
    private String clientPhoneNumber;

    @Column(nullable = false)
    private String clientEmail;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal income;

    public Client(String cpf, LocalDate birthDate, String clientName, String clientPhoneNumber, String clientEmail, Gender gender, String password, BigDecimal income) {
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientEmail = clientEmail;
        this.gender = gender;
        this.password = password;
        this.income = income;
    }
}

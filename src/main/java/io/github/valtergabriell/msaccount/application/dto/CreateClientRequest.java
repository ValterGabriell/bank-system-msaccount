package io.github.valtergabriell.msaccount.application.dto;

import io.github.valtergabriell.msaccount.entity.Client;
import io.github.valtergabriell.msaccount.entity.enums.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }




    public boolean isCPFCorrectSize(){
        return cpfValidator.validateFieldSize(request.getCpf());
    }

}

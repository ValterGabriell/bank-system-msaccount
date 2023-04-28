package io.github.valtergabriell.msaccount.application.dto;


import io.github.valtergabriell.msaccount.entity.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceiveDataForCreateNewClient {
    private String id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal income;

    public ReceiveDataForCreateNewClient() {
    }

    public ReceiveDataForCreateNewClient(String id, String name, String email, String phone, BigDecimal income) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.income = income;
    }

    public Client toModel() {
        return new Client(
                this.id,
                LocalDate.now(),
                this.name,
                this.phone,
                this.email,
                this.income
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}

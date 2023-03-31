package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {


    @DisplayName(value = "Verify if CPF has 11 digits")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945"})
    void itShouldReturnTrueWhenCpfHasElevenDigitsAndFalseWhenIsNot(String cpf){
        assertTrue(cpf.length() == 11);
    }

    @DisplayName(value = "Verify if CPF has just one type of digit")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void itShouldReturnFalseIfCpfHasJustOneTypeOfNumber(String value){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            builder.append(value);
        }
        assertFalse(!builder.toString().equals("11111111111"));
    }

    @DisplayName(value = "Verify if phone number has 13 digits")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945","1234567894562"})
    void itShouldReturnTrueWhenPhoneNumberHasFourteenDigitsAndFalseWhenIsNot(String phone){
        assertTrue(phone.length() == 13);
    }

    @DisplayName(value = "Verify if phone starts with 55")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945","12345678945612", "553256"})
    void itShouldReturnTrueWhenPhoneNumberStartsWithFive(String phone){
        assertTrue(phone.substring(0, 2).equals("55"));
    }

}
package io.github.valtergabriell.msaccount.application;

import io.github.valtergabriell.msaccount.application.dto.CreateClientRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Pattern;

import static io.github.valtergabriell.msaccount.application.util.CommonMethod.EMAIL_PATTERN;
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
    void itShouldReturnTrueIfCpfHasJustOneTypeOfNumber(String value){
        String newString = "1".repeat(11);
        assertTrue(value.equals(newString));
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

    @DisplayName("Verify if is email valid")
    @ParameterizedTest
    @ValueSource(strings = {"vgabrielbri@hotmail.com", "用户名@领域.电脑"})
    void itShouldReturnTrueIfEmailIsValid(String email){
        String regexPattern = EMAIL_PATTERN;
        assertTrue(Pattern.compile(regexPattern).matcher(email).matches());
    }

}
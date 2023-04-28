package io.github.valtergabriell.msaccount.application.validator;

import io.github.valtergabriell.msaccount.application.exception.RequestExceptions;

import java.util.regex.Pattern;

import static io.github.valtergabriell.msaccount.application.exception.ExceptionsValues.EMAIL_PATTERN;


public class EmailValidator {
    public static boolean isValidEmail(String email) {
        String regexPattern = EMAIL_PATTERN;
        boolean matches = Pattern.compile(regexPattern).matcher(email).matches();
        if (!matches){
            throw new RequestExceptions("Email inválido!");
        }
        return true;
    }

}

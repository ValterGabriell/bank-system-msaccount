package io.github.valtergabriell.msaccount.application.validator;

import java.util.regex.Pattern;

import static io.github.valtergabriell.msaccount.application.util.CommonMethod.EMAIL_PATTERN;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        String regexPattern = EMAIL_PATTERN;
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

}

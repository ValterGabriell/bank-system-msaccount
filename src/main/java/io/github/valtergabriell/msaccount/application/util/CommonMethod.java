package io.github.valtergabriell.msaccount.application.util;

public class CommonMethod {

    public static boolean checkIfValueHasOnlyOneDigitRepeatly(String value, int count){
        boolean isValueSameSizeThanNewString = false;
        for (int i = 0; i < 11; i++) {
            String newString = String.valueOf(i).repeat(count);
            if (value.equals(newString)) {
                isValueSameSizeThanNewString = true;
                break;
            }
        }

        return isValueSameSizeThanNewString;
    }

    public static int CPF_COUNT = 11;
    public static int PHONE_NUMBER_COUNT = 13;
    public static String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
            + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

}

package io.github.valtergabriell.msaccount.application.validator;

public class PhoneNumberValidator extends NumberFieldsValidation {
    @Override
    public boolean hasRepeatDigits(String value, int maxValue) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < maxValue; j++) {
                builder.append(i);
                if (value.equals(builder)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean validateFieldSize(String value) {
        return value.length() == 13;
    }
}

package io.github.valtergabriell.msaccount.application.validator;

public abstract class NumberFieldsValidation {
    public abstract boolean validateFieldSize(String value);

    public abstract boolean hasOnlyOneDigitOnWholeNumber(String value);

    public boolean isCnpj(String value) {
        if (value.length() > 11) {
            return value.length() == 14;
        }
        return false;
    }

    public boolean isCpf(String value) {
        return value.length() == 11;
    }


}

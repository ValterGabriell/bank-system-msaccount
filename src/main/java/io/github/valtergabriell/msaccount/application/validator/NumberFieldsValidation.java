package io.github.valtergabriell.msaccount.application.validator;

public abstract class NumberFieldsValidation {
    public abstract boolean validateFieldSize(String value);

    public abstract boolean hasOnlyOneDigitOnWholeNumber(String value);


}

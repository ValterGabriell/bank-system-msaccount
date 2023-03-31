package io.github.valtergabriell.msaccount.application.validator;

import io.github.valtergabriell.msaccount.application.util.CommonMethod;

import static io.github.valtergabriell.msaccount.application.util.CommonMethod.CPF_COUNT;

public class CPFValidator extends NumberFieldsValidation {


    @Override
    public boolean validateFieldSize(String value) {
        return value.length() == 11;
    }

    @Override
    public boolean hasOnlyOneDigitOnWholeNumber(String value) {
        return CommonMethod.checkIfValueHasOnlyOneDigitRepeatly(value, CPF_COUNT);
    }
}

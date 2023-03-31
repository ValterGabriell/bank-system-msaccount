package io.github.valtergabriell.msaccount.application.validator;

import io.github.valtergabriell.msaccount.application.util.CommonMethod;


import static io.github.valtergabriell.msaccount.application.util.CommonMethod.PHONE_NUMBER_COUNT;

public class PhoneNumberValidator extends NumberFieldsValidation {

    @Override
    public boolean validateFieldSize(String value) {
        return value.length() == 13;
    }

    public boolean hasOnlyOneDigitOnWholeNumber(String value) {
        return CommonMethod.checkIfValueHasOnlyOneDigitRepeatly(value, PHONE_NUMBER_COUNT);
    }

}

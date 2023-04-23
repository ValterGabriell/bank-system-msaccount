package io.github.valtergabriell.msaccount.application.validator;

import io.github.valtergabriell.msaccount.application.exception.RequestException;
import io.github.valtergabriell.msaccount.application.util.CommonMethod;

import static io.github.valtergabriell.msaccount.application.exception.ExceptionsValues.*;


public class PhoneNumberValidator extends NumberFieldsValidation {

    @Override
    public boolean validateFieldSize(String value) {
        boolean isCpfValidLenght = value.length() == PHONE_NUMBER_COUNT;
        if (!isCpfValidLenght) {
            throw new RequestException(CPF_INVALID);
        }
        return true;
    }

    public boolean hasOnlyOneDigitOnWholeNumber(String value) {
        boolean hasOnlyOneDigitRepeatly = CommonMethod.checkIfValueHasOnlyOneDigitRepeatly(value, CPF_COUNT);
        if (hasOnlyOneDigitRepeatly){
            throw new RequestException(DIGITS_REPEATLY);
        }
        return false;
    }

}

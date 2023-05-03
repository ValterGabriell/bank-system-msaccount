package io.github.valtergabriell.msaccount.application.validator;

import io.github.valtergabriell.msaccount.application.exception.RequestExceptions;
import io.github.valtergabriell.msaccount.application.util.CommonMethod;

import static io.github.valtergabriell.msaccount.application.exception.ExceptionsValues.*;


public class IdentifierValidator extends NumberFieldsValidation {


    @Override
    public boolean validateFieldSize(String value) {
        if (isCnpj(value)){
            boolean isCpfValidLenght = value.length() == CNPJ_COUNT;
            if (!isCpfValidLenght) {
                throw new RequestExceptions(CNPJ_INVALID);
            }
            return true;
        } else if (isCpf(value)){
            boolean isCpfValidLenght = value.length() == CPF_COUNT;
            if (!isCpfValidLenght) {
                throw new RequestExceptions(CPF_INVALID);
            }
            return true;
        } else{
            throw new RequestExceptions(INVALID_LENGHT);
        }
    }

    @Override
    public boolean hasOnlyOneDigitOnWholeNumber(String value) {
        boolean hasOnlyOneDigitRepeatly = CommonMethod.checkIfValueHasOnlyOneDigitRepeatly(value, CPF_COUNT);
        if (hasOnlyOneDigitRepeatly){
            throw new RequestExceptions(DIGITS_REPEATLY);
        }
        return false;
    }
}

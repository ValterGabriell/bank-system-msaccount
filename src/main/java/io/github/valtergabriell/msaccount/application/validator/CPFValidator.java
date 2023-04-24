package io.github.valtergabriell.msaccount.application.validator;

import io.github.valtergabriell.msaccount.application.exception.RequestException;
import io.github.valtergabriell.msaccount.application.util.CommonMethod;

import static io.github.valtergabriell.msaccount.application.exception.ExceptionsValues.*;


public class CPFValidator extends NumberFieldsValidation {


    @Override
    public boolean validateFieldSize(String value) {
        if (isCnpj(value)){
            boolean isCpfValidLenght = value.length() == CNPJ_COUNT;
            if (!isCpfValidLenght) {
                throw new RequestException(CNPJ_INVALID);
            }
            return true;
        } else if (isCpf(value)){
            boolean isCpfValidLenght = value.length() == CPF_COUNT;
            if (!isCpfValidLenght) {
                throw new RequestException(CPF_INVALID);
            }
            return true;
        } else{
            throw new RequestException(INVALID_LENGHT);
        }
    }

    @Override
    public boolean hasOnlyOneDigitOnWholeNumber(String value) {
        boolean hasOnlyOneDigitRepeatly = CommonMethod.checkIfValueHasOnlyOneDigitRepeatly(value, CPF_COUNT);
        if (hasOnlyOneDigitRepeatly){
            throw new RequestException(DIGITS_REPEATLY);
        }
        return false;
    }
}

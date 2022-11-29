package com.secxndary.filiusmeretrixproject.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<CellPassword, String> {

    @Override
    public void initialize(CellPassword paramA) {  }

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext context) {
        if (pass == null) {
            return false;
        }
        if (pass.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")) {
            return true;
        }
        //mkyonG $$1
        //латинские буквы верхий регистр
        //латинские буквы нижний регистр
        //специсимвол хоть один
        //хотя бы одно число
        //строка состоит не менее, чем из 6 вышеупомянутых символов

        else {
            return false;
        }
    }
}


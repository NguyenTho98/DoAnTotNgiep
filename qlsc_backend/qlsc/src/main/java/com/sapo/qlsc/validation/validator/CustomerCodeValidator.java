package com.sapo.qlsc.validation.validator;

import com.sapo.qlsc.validation.anotation.CustomerCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerCodeValidator implements ConstraintValidator<CustomerCode, String> {

    @Override
    public void initialize(CustomerCode constraintAnnotation) {

    }

    @Override
    public boolean isValid(String customerCode, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return (customerCode == null) || (customerCode.length() == 0 || customerCode.length() >= 4 && customerCode.matches("[a-zA-Z]{2}[0-9]{1,4}"));
        } catch (Exception e) {
            return false;
        }
    }
}

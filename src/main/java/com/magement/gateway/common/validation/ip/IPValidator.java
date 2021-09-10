package com.magement.gateway.common.validation.ip;

import org.apache.commons.validator.routines.InetAddressValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IPValidator implements ConstraintValidator<ValidIP, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        InetAddressValidator validator = InetAddressValidator.getInstance();
        return validator.isValidInet4Address(value);
    }
}

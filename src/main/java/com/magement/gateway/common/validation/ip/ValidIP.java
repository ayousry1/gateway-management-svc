package com.magement.gateway.common.validation.ip;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IPValidator.class)
public @interface ValidIP {
    String message() default "invalid IPV4 format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

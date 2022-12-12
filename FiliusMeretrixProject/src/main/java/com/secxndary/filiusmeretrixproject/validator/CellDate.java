package com.secxndary.filiusmeretrixproject.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CellDate {

    String message() default "{Date}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

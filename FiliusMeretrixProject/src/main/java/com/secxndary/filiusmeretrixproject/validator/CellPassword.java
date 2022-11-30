package com.secxndary.filiusmeretrixproject.validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

// TODO: date validation
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface CellPassword {

    String message() default "{Password}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

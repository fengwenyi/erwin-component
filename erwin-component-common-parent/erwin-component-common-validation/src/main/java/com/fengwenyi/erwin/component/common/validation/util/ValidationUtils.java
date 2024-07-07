package com.fengwenyi.erwin.component.common.validation.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-04-02
 */
public class ValidationUtils {

    public static <T> List<String> valid(T t) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> errors = validator.validate(t);
            return errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        }
    }

}

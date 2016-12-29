package com.riders.giddy.api.v1.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by rik on 12/30/16.
 */
public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}

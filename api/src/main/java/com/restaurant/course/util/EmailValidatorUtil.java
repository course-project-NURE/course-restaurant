package com.restaurant.course.util;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidatorUtil {
    public static boolean validate(String email){
        return EmailValidator.getInstance()
                .isValid(email);
    }
}

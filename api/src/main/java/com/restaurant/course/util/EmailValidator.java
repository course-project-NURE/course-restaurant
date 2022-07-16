package com.restaurant.course.util;

import java.util.regex.Pattern;

public class EmailValidator {
    private static String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";


    public static boolean validate(String email){
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public EmailValidator(){}

}

package com.shopme.admin.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Created 14/01/2022 - 17:53
 * @Package com.shopme.admin.exception
 * @Project ShopmeProject
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

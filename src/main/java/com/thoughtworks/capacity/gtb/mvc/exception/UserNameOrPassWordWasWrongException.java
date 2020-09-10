package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserNameOrPassWordWasWrongException extends Exception {
    public UserNameOrPassWordWasWrongException() {
        super("用户名或密码错误");
    }
}

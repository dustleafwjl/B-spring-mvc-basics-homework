package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserHasExistException extends Exception {
    public UserHasExistException() {
        super("用户已存在");
    }
}


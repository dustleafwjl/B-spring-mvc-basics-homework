package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.ErrorResult;
import com.thoughtworks.capacity.gtb.mvc.exception.UserHasExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handler(MethodArgumentNotValidException exception) {
        String defaultMessage = exception.getBindingResult().getFieldError().getDefaultMessage();
        return ErrorResult.builder().code("400").message(defaultMessage).build();
    }

    @ExceptionHandler(UserHasExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResult handler(UserHasExistException exception) {
        String defaultMessage = exception.getMessage();
        return ErrorResult.builder().code("400").message(defaultMessage).build();
    }
}

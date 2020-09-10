package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserHasExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPassWordWasWrongException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import com.thoughtworks.capacity.gtb.mvc.validation.UserCheckSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Validated({UserCheckSequence.class}) User user) throws UserHasExistException {
        userService.registerUser(user);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public User loginUser(@RequestBody @Validated({UserCheckSequence.class}) User user) throws UserNameOrPassWordWasWrongException {
        return userService.loginUser(user);
    }
}

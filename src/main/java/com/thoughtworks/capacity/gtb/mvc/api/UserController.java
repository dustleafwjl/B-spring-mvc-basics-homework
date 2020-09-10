package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserHasExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPassWordWasWrongException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import com.thoughtworks.capacity.gtb.mvc.validation.UserCheckSequence;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

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

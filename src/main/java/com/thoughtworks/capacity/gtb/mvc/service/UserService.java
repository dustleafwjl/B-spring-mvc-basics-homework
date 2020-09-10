package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    public static HashMap<Integer, User> userDao = new HashMap<Integer, User>();
    public static int generatedId = 1;
    public User registerUser(User user) {
        userDao.put(generatedId, user);
        generatedId ++;
        return userDao.get(generatedId - 1);
    }
}

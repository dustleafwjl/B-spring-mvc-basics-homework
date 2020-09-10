package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserHasExistException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    public static HashMap<Integer, User> userDao = new HashMap<Integer, User>();
    public static int generatedId = 1;
    public User registerUser(User user) throws UserHasExistException {
        if(IsUserHasExist(user)) throw new UserHasExistException();
        userDao.put(generatedId, user);
        generatedId ++;
        return userDao.get(generatedId - 1);
    }

    private boolean IsUserHasExist(User user) {
        for(User saveUser : userDao.values()) {
            if(saveUser.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public static void clearup() {
        userDao.clear();
    }
}

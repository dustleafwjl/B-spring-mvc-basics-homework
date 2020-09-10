package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserHasExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPassWordWasWrongException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    public static HashMap<Integer, User> userDao = new HashMap<Integer, User>();
    public static int generatedId = 1;
    public User registerUser(User user) throws UserHasExistException {
        if(IsUserHasExist(user)) throw new UserHasExistException();
        user.setId(generatedId);
        userDao.put(generatedId, user);
        generatedId ++;
        return userDao.get(generatedId - 1);
    }
    public User loginUser(User user) throws UserNameOrPassWordWasWrongException {
        User saveUser = getUserByUserName(user.getUsername());
        if(saveUser == null) {
            throw new UserNameOrPassWordWasWrongException();
        } else if(!saveUser.getPassword().equals(user.getPassword())) {
            throw new UserNameOrPassWordWasWrongException();
        } else {
            return saveUser;
        }
    }

    private User getUserByUserName(String username) {
        for(User user : userDao.values()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
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
        generatedId = 1;
    }

}

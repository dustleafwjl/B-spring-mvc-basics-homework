package com.thoughtworks.capacity.gtb.mvc.repo;

import com.thoughtworks.capacity.gtb.mvc.domain.User;

import java.util.HashMap;

public class UserRepo {
    public static HashMap<Integer, User> userDao = new HashMap<Integer, User>();
    public static int generatedId = 1;

    public static void clearup() {
        userDao.clear();
        generatedId = 1;
    }

    public static User getUserByUserName(String username) {
        for(User user : userDao.values()) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void save(User user) {
        user.setId(generatedId);
        userDao.put(generatedId, user);
        generatedId ++;
    }
}

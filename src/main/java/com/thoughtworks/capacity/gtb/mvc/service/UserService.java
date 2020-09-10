package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserHasExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPassWordWasWrongException;
import com.thoughtworks.capacity.gtb.mvc.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.thoughtworks.capacity.gtb.mvc.repo.UserRepo.generatedId;

@Service
public class UserService {
    public HashMap<Integer, User> userDao = UserRepo.userDao;
    public User registerUser(User user) throws UserHasExistException {
        if(IsUserHasExist(user)) throw new UserHasExistException();
        UserRepo.save(user);
        return userDao.get(generatedId - 1);
    }



    public User loginUser(User user) throws UserNameOrPassWordWasWrongException {
        User saveUser = UserRepo.getUserByUserName(user.getUsername());
        if(saveUser == null) {
            throw new UserNameOrPassWordWasWrongException();
        } else if(!saveUser.getPassword().equals(user.getPassword())) {
            throw new UserNameOrPassWordWasWrongException();
        } else {
            return saveUser;
        }
    }



    private boolean IsUserHasExist(User user) {
        for(User saveUser : userDao.values()) {
            if(saveUser.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
}

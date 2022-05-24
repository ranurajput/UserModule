package com.knoldus.chat_messenger.service;

import com.knoldus.chat_messenger.Model.User;
import com.knoldus.chat_messenger.repository.UserRepository;
import com.knoldus.chat_messenger.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
/**
 * User Service.
 */

@Service
public class UserServices {
    /**
     *
     */
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserValidation userValidation;

    /**
     *
     * @param receivedUserDetails
     * @return
     */
    public String addNewUserService(final User receivedUserDetails) {
        String checkMobile = userValidation
                .validateMobile(receivedUserDetails
                .getMobile());
        String userExists="";
        String validatePassword = userValidation.isValidPassword(receivedUserDetails.getPassword());
        if(checkMobile == "" && validatePassword == ""  && !userRepository.existsByMobile(receivedUserDetails.getMobile())){
            User user = new User();
            user.setName(receivedUserDetails.getName());
            user.setMobile(receivedUserDetails.getMobile());
            user.setPassword(receivedUserDetails.getPassword());
            user.setActive(true);
            Date date = new Date();
            user.setCreatedDate(date);
            user.setUpdatedDate(date);
            userRepository.save(user);
            return "User saved\n";
        }
        if ( userRepository.existsByMobile(receivedUserDetails.getMobile())){
            userExists += "already exist";
        }

        return checkMobile + validatePassword + userExists;
    }
    public String updateUserService(User receivedUserDetails) {
        String checkMobile = userValidation
                .validateMobile(receivedUserDetails
                        .getMobile());
        String validatePassword = userValidation.isValidPassword(receivedUserDetails.getPassword());
        if(userRepository.existsById(receivedUserDetails.getId()) && !userRepository.existsByMobile(receivedUserDetails.getMobile())){
            if (checkMobile == "" && validatePassword == ""){
                Date date = new Date();
                User editUser = userRepository.getById(receivedUserDetails.getId());
                editUser.setName(receivedUserDetails.getName());
                editUser.setPassword(receivedUserDetails.getPassword());
                editUser.setMobile(receivedUserDetails.getMobile());
                editUser.setUpdatedDate(date);
                userRepository.save(editUser);
                return "user updated\n";
            }
            return checkMobile + validatePassword;
        }
        return "user does not exists";
    }
    public String deleteUserService(User receivedUserDetails) {
        if (userRepository.existsById(receivedUserDetails.getId())){
            Date date = new Date();
            User editUser = userRepository.getById(receivedUserDetails.getId());
            editUser.setUpdatedDate(date);
            editUser.setActive(false);
            userRepository.save(editUser);
            return editUser.getName() + " is deleted\n";
        }
        return receivedUserDetails.getName() + " does not exist";
    }
    public String loginService(User receivedUserDetails){
        // For Authentication
        User user = userRepository.fetchUser(receivedUserDetails.getMobile(), receivedUserDetails.getPassword());
        if(user != null) {
            return "Welcome " +user.getName() + "\n ";
        }
        return "Incorrect mobile no. or password\n";
    }
    public Iterable<User> fetchUserListService(){
        return userRepository.findAll();
    }
}

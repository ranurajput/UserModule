package com.knoldus.chat_messenger.controller;
import com.knoldus.chat_messenger.Model.User;
import com.knoldus.chat_messenger.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserServices userServices;
    @PostMapping(path="/save")
    public @ResponseBody String addNewUser (@RequestBody User receivedUserDetails) {
       return userServices.addNewUserService(receivedUserDetails);
    }
    @PostMapping(path="/edit")
    public @ResponseBody String updateUser (@RequestBody User receivedUserDetails) {
        return userServices.updateUserService(receivedUserDetails);
    }
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestBody User receivedUserDetails) {
      return userServices.deleteUserService(receivedUserDetails);
    }
    @PostMapping(path = "/login")
    public @ResponseBody String loginUser(@RequestBody User receivedUserDetails) {
       return userServices.loginService(receivedUserDetails);
    }
    @PostMapping(path = "/fetch/list")
    public @ResponseBody Iterable<User> fetchUserList() {
       return userServices.fetchUserListService();
    }
}

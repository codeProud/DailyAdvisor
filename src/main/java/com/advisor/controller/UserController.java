package com.advisor.controller;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.response.UserProfileResponse;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "/getUserProfile/{userId}" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<UserProfileResponse> getUserProfileByUserId(@PathVariable Long userId)
    {
        User user = userService.findUserById(userId);
        if(user != null){ //TODO make exception catch
            UserProfileResponse userProfileResponse = userService.createUserProfileResponseByUser(user);
            return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "/getUser/{userId}" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<UserProfileResponse> getUserByUserId(@PathVariable Long userId)
    {
        User user = userService.findUserById(userId);
        if(user != null){ //TODO make exception catch
            UserProfileResponse userProfileResponse = userService.createUserResponseByUser(user);
            return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = { "/updateUserProfile" }, method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity updateUserProfile(@RequestBody UserProfileRequest userProfileRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        userService.updateUserProfile(userProfileRequest, userService.createUserProfileResponseByUser(user).getUserId());

        return new ResponseEntity(HttpStatus.OK);
    }

}

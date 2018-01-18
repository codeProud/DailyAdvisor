package com.advisor.controller;

import javax.validation.Valid;
import com.advisor.model.entity.User;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity createNewUser(@RequestBody @Valid User user) {
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists == null) {
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
		}
	}

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity createNewUser() {
        return new ResponseEntity(HttpStatus.OK);
    }


}

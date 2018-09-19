package com.example.dsboot.controller;


import com.example.dsboot.dto.UserDto;
import com.example.dsboot.model.User;
import com.example.dsboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value="/user")
@Api(description = "用户管理")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ApiOperation(value = "user login")
    public UserDto userLogin(
            @RequestParam String userName,
            @RequestParam String password
    ){

        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        return  userService.login(user);

//        UserDto userDto = new UserDto();
//        if(userName == null || userName.isEmpty()
//           || password == null || password.isEmpty()){
//            userDto.setAccessToken(null);
//            userDto.setUid(null);
//            userDto.setUserName(null);
//            userDto.setMessage("用户或密码不正确");
//            return userDto;
//        }else{
//            userDto.setUserName(userName);
//            userDto.setMessage("success");
//            userDto.setAccessToken(UUID.randomUUID().toString());
//            return userDto;
//        }

    }

    @RequestMapping(value = "/serviceUA",method = RequestMethod.GET)
    @ApiOperation(value = "service UA")
    public String serviceUA(
            @RequestParam String userId
    ){
        return userId;
    }
}

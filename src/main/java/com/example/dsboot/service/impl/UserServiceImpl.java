package com.example.dsboot.service.impl;

import com.example.dsboot.dto.UserDto;
import com.example.dsboot.mapper.TokenMapper;
import com.example.dsboot.mapper.UserMapper;
import com.example.dsboot.model.Token;
import com.example.dsboot.model.User;
import com.example.dsboot.model.UserExample;
import com.example.dsboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenMapper tokenMapper;



    @Override
    public List<User> getUsers(User user) {
        UserExample ue = new UserExample();
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        return  userMapper.selectByExample(ue);
    }

    @Override
    public boolean isExistUser(User user) {
        UserExample ue = new UserExample();
        user.setName(user.getName());
        user.setPassword(user.getPassword());
        List<User> users = userMapper.selectByExample(ue);
        //if have user, return true.
        if(users.size()>0){
            return true;
        }
        //if no user, return false.
        return false;
    }

    @Override
    @Transactional
    public UserDto login(User user) {
        UserDto udto = new UserDto();
        List<User> existUser = getUsers(user);
        //if user existed, then generate access token for
        if(existUser.size()>0){
            Token token = new Token();
            token.setUserid(existUser.get(0).getId());
            token.setToken(UUID.randomUUID().toString());
            tokenMapper.insertSelective(token);
            udto.setMessage("登陆成功！");
            udto.setAccessToken(token.getToken());
        }else{
            udto.setMessage("用户不存在或密码错误！");
        }

        udto.setUserName(user.getName());
        return udto;
    }
}

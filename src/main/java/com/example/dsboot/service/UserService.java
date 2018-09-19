package com.example.dsboot.service;

import com.example.dsboot.dto.UserDto;
import com.example.dsboot.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getUsers(User user);

    boolean isExistUser(User user);

    UserDto login(User user);

}

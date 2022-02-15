package com.vti.demo.service;

import com.vti.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserInfo(String username);
    User findByUserName(String username);

}

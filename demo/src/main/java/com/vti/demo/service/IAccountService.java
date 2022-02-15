package com.vti.demo.service;

import com.vti.demo.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    List<Account> getAllUsers();
}

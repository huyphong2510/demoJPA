package com.vti.demo.service;

import com.vti.demo.entity.Account;
import com.vti.demo.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = repository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(
                account.getUsername(),
                account.getPassword(),
                AuthorityUtils.createAuthorityList("account"));
    }

    @Override
    public List<Account> getAllUsers() {
        return repository.findAll();
    }
}
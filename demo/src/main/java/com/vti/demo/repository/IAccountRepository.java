package com.vti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.demo.entity.Account;

public interface IAccountRepository extends JpaRepository<Account, Short> {
    public Account findByUsername(String username);

}

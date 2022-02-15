package com.vti.demo.repository;

import com.vti.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

    public User findByUserName(String name);


}

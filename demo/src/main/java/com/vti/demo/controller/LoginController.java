package com.vti.demo.controller;

import java.security.Principal;

import com.vti.demo.entity.User;
import com.vti.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.demo.dto.LoginInfoDTO;
import com.vti.demo.entity.Account;
import com.vti.demo.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private IUserService iUserService;

    @GetMapping()
    public ResponseEntity<?> login(Principal principal) {

        String username = principal.getName();
        User entity = (User) iUserService.getUserInfo(username);

        LoginInfoDTO dto = new LoginInfoDTO((short) entity.getId(), entity.getFullName(), entity.getRole());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

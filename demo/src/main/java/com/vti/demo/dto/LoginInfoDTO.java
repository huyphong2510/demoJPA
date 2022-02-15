package com.vti.demo.dto;


public class LoginInfoDTO {
    private short id;

    private String fullName;

    private String role;


    public LoginInfoDTO(short id, String fullName,String role) {
        this.id = id;
        this.fullName = fullName;
        this.role  = role;

    }

    public short getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

}

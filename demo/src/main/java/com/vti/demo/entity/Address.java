package com.vti.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "AddressID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "name", length = 30, nullable = false, unique = true)
    private String name;


}
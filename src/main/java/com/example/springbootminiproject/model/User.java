package com.example.springbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        //unique auto-generated id for each user

    @Column
    private String username;  //represents each user's username

    @Column(unique = true)
    private String emailAddress;   //represents each user's email, must be unique

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;       //represents each user's password, is write-only, not visible in database

    //default constructor
    public User() {
    }

    public User(Long id, String username, String emailAddress, String password) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}

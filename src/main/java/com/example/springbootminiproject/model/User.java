package com.example.springbootminiproject.model;

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
}

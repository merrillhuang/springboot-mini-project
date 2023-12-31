package com.example.springbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "user")   // Each user has a list of Genres, one User to Many Genres
    @LazyCollection(LazyCollectionOption.FALSE)     // Eagerly fetches genreList, genreList is loaded automatically when a User is loaded
    private List<Genre> genreList;

    @OneToMany(mappedBy = "user")    // Each user has a list of Books, one User to Many Books
    @LazyCollection(LazyCollectionOption.FALSE)     // Eagerly fetches bookList, bookList is loaded automatically when a User is loaded
    private List<Book> bookList;

    //default no args constructor
    public User() {
    }

    // Constructor that initializes all the fields
    public User(Long id, String username, String emailAddress, String password) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.example.springbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean adult;  // Denotes whether a Genre is intended for adults

    @ManyToOne      // Many Genres belong to One User
    @JoinColumn(name = "user_id")       // Name of foreign key (to users table) column is user_id
    @JsonIgnore     // Prevents recursively loading User, which loads a list of Genres, which each load a User, and so on
    private User user;

    @OneToMany(mappedBy = "genre", orphanRemoval = true)    // One Genre has Many Books, If a Genre is removed from the database, all of its Books are removed as well
    @LazyCollection(LazyCollectionOption.FALSE)     // Eagerly fetches bookList, bookList is loaded automatically when a User is loaded
    private List<Book> bookList;

    // No args default constructor
    public Genre() {
    }

    // Constructor that initializes all fields
    public Genre(Long id, String name, boolean adult) {
        this.id = id;
        this.name = name;
        this.adult = adult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adult=" + adult +
                '}';
    }
}

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore     // Prevents recursively loading User, which loads a list of Genres, which each load a User, and so on
    private User user;

    @OneToMany(mappedBy = "genre", orphanRemoval = true)    // If a Genre is removed from the database, all of its Books are removed as well
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adult=" + adult +
                '}';
    }
}

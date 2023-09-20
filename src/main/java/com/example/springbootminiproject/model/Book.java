package com.example.springbootminiproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private int pages;

    @ManyToOne      // Many Books belong to One Genre
    @JoinColumn(name = "genre_id")      // Name of foreign key (to genres table) column is genre_id
    @JsonIgnore     // Prevents recursively loading Genre, which loads a list of Books, which each load a Genre, and so on
    private Genre genre;

    @ManyToOne      // Many Books belong to One User
    @JoinColumn(name = "user_id")       // Name of foreign key (to users table) column is user_id
    @JsonIgnore     // Prevents recursively loading User, which loads a list of Books, which each load a User, and so on
    private User user;


    // Default no args constructor
    public Book() {
    }

    // Constructor that initializes all fields
    public Book(Long id, String title, String author, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }
}

package com.example.springbootminiproject.model;

import javax.persistence.*;

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

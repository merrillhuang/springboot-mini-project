package com.example.springbootminiproject.repository;

import com.example.springbootminiproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

    Genre findByIdAndUserId(Long genreId, Long userId);

    List<Genre> findByUserId(Long userId);
}

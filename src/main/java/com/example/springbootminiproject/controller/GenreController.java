package com.example.springbootminiproject.controller;

import com.example.springbootminiproject.model.Genre;
import com.example.springbootminiproject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")  // http://localhost:9094/api
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(path = "/genres/")  // http://localhost:9094/api/genres/
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping(path = "/genres/{genreId}") // http://localhost:9094/api/genres/1/
    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenreById(genreId);
    }
}

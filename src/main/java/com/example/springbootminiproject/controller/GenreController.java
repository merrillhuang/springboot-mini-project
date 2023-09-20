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

    @GetMapping(path = "/genres/{genreId}")     // http://localhost:9094/api/genres/1/
    public Optional<Genre> getGenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.getGenreById(genreId);
    }

    @PostMapping(path = "/genres/")  // http://localhost:9094/api/genres/
    public Genre createGenre(@RequestBody Genre genreObject) {
        return genreService.createGenre(genreObject);
    }

    @PutMapping(path = "/genres/{genreId}")     // http://localhost:9094/api/genres/1/
    public Genre updateGenreById(@PathVariable(value = "genreId") Long genreId, @RequestBody Genre genreObject) {
        return genreService.updateGenreById(genreId, genreObject);
    }

    @DeleteMapping(path = "/categories/{genreId}") // http://localhost:9094/api/genres/1/
    public Optional<Genre> deletegenre(@PathVariable(value = "genreId") Long genreId) {
        return genreService.deleteGenreById(genreId);
    }
}

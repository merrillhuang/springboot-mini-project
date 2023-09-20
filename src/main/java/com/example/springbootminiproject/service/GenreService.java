package com.example.springbootminiproject.service;

import com.example.springbootminiproject.exception.InformationNotFoundException;
import com.example.springbootminiproject.model.Genre;
import com.example.springbootminiproject.model.User;
import com.example.springbootminiproject.repository.BookRepository;
import com.example.springbootminiproject.repository.GenreRepository;
import com.example.springbootminiproject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    private BookRepository bookRepository;

    // Creates a logger for this class
    Logger logger = Logger.getLogger(GenreService.class.getName());

    // Creates a GenreRepository instance
    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Creates a BookRepository instance
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Gets the current logged in User from the application's memory
     * @return The User that is currently logged in.
     */
    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Runs a SQL query for all Genres in genres table that have the User id of the current logged in User.
     * @return The list of the current User's Genres if found, error if not found.
     */
    public List<Genre> getGenres() {
        List<Genre> genreList = genreRepository.findByUserId(GenreService.getCurrentLoggedInUser().getId());
        if (genreList.isEmpty()) {
            throw new InformationNotFoundException("No saved Genres found under User id " + GenreService,getCurrentLoggedInUser().getId() + ".");
        } else {
            return genreList;
        }
    }
}

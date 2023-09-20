package com.example.springbootminiproject.service;

import com.example.springbootminiproject.exception.InformationExistException;
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
import java.util.Optional;
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
            throw new InformationNotFoundException("No saved genres found under user id " + GenreService.getCurrentLoggedInUser().getId() + ".");
        } else {
            return genreList;
        }
    }

    /**
     * Finds a certain genre belonging to the current logged in user by the genre id number.
     * @param genreId The genreId passed in by the Http request.
     * @return An optional containing the found Genre, or an error.
     */
    public Optional<Genre> getGenreById(Long genreId) {
        Optional<Genre> genreOptional = Optional.of(genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId()));
        if (genreOptional.isPresent()) {
            return genreOptional;
        } else {
            throw new InformationNotFoundException("No genre with genre id " + genreId + " found under user with id " + GenreService.getCurrentLoggedInUser().getId() + ".");
        }
    }

    /**
     * Saves a new Genre entry to the database if there aren't any entries in the database that match the passed in Genre.
     * @param genreObject The Genre passed in by the Http request.
     * @return The Genre after being saved in the database if it is a new entry, error if not.
     */
    public Genre createGenre(Genre genreObject) {
        Genre genre = genreRepository.findByName(genreObject.getName());
        if (genre != null) {
            throw new InformationExistException("Genre with name " + genreObject.getName() + " already exists.");
        } else {
            genreObject.setUser(GenreService.getCurrentLoggedInUser());
            return genreRepository.save(genreObject);
        }
    }

    /**
     * Updates the Genre with given genreId if the passed in details are different than the existing details.
     * @param genreId The id passed in from the Http request.
     * @param genreObject The Genre passed in from the Http request.
     * @return An optional containing the Genre with updated details if the provided details are different and Genre exists in the database, errors if not.
     */
    public Genre updateGenreById(Long genreId, Genre genreObject) {
        Optional<Genre> genreOptional = Optional.of(genreRepository.findByIdAndUserId(genreId, GenreService.getCurrentLoggedInUser().getId()));
        if (genreOptional.isPresent()) {
            if (genreOptional.get().getName().equals(genreObject.getName()) &&
                    genreOptional.get().getAdult() == (genreObject.getAdult())) {
                throw new InformationExistException("Genre with id " + genreId + " already has the the attributes of " + genreObject + ".");
            } else {
                genreOptional.get().setName(genreObject.getName());
                genreOptional.get().setAdult(genreObject.getAdult());
                return genreRepository.save(genreOptional.get());
            }
        } else {
            throw new InformationNotFoundException("Genre with id " + genreId + " not found under current user.");
        }
    }
}

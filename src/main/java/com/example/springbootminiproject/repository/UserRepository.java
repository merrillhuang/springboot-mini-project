package com.example.springbootminiproject.repository;

import com.example.springbootminiproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Uses SQL statement to find a User from the database by their email address.
     * @param emailAddress The email address passed in by the HttpRequest
     * @return The User that has the corresponding email address.
     */
    User findUserByEmailAddress(String emailAddress);

    /**
     * Checks if a User with the given email address already exists in the database.
     * @param emailAddress The email address passed in by the HttpRequest
     * @return True if a User with the given email address already exists, False if not.
     */
    boolean existsByEmailAddress(String emailAddress);
}

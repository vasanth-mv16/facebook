package com.facebook.service;

import com.facebook.model.User;

import java.util.Collection;

/**
 * Provides service for user details.
 *
 * @version 1.0
 * @author vasanth
 */
public interface UserService {

    /**
     * Adds user details.
     *
     * @param user the user to be added
     * @return true if the user is successfully added, false otherwise
     */
    boolean add(final User user);

    /**
     * Retrieves the collection of user details.
     *
     * @return the collection of users
     */
    Collection<User> getUserDetails();

    /**
     * Updates user details.
     *
     * @param user the user to be updated
     * @return true if the user details are successfully updated, false otherwise
     */
    boolean updateDetail(final User user);

    /**
     * Deletes user details.
     *
     * @param id the id of the user to be deleted
     * @return true if the user details are successfully deleted, false otherwise
     */
    boolean deleteDetail(final Long id);

    /**
     * Validates user sign-in details.
     *
     * @param id the id of the user to sign in
     * @return true if the sign-in is successful, false otherwise
     */
    boolean signInDetail(final Long id);

    /**
     * Retrieves a user by id.
     *
     * @param id the id of the user to retrieve
     * @return the user with the specified id
     */
    User getUser(final Long id);

}


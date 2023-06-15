package com.facebook.service;

import com.facebook.model.User;

import java.util.Collection;

/**
 * <p>
 *     Provides service for the user details.
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public interface UserService {

    /**
     * <p>
     *     Adds user details.
     * </p>
     *
     * @param user Represents the user to be add
     * @return true if the user is successfully added, false otherwise
     */
    boolean add(final User user);

    /**
     * <p>
     *     Retrieves the collection of user details.
     * </p>
     *
     * @return the collection of users
     */
    Collection<User> get();

    /**
     * <p>
     *     Updates user details
     * </p>
     *
     * @param user Represents the user to be update
     * @return true if the user details are updated, false otherwise
     */
    boolean update(final User user);

    /**
     * <p>
     *     Deletes user details.
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return true if the user details are successfully deleted, false otherwise
     */
    boolean delete(final Long id);

    /**
     * <p>
     *     Validates user sign-in details.
     * </p>
     *
     * @param user Represents user to sign in
     * @return true if the sign-in is successful, false otherwise
     */
    boolean signIn(final User user);

    /**
     * <p>
     *     Retrieves a user by id
     * </p>
     *
     * @param id Represents the id of the user to retrieve
     * @return {@link User}
     */
    User getById(final Long id);

    /**
     * <p>
     *     Retrieves user id
     * </p>
     *
     * @param user Refers the user to get the id
     * @return Returns the id of the user
     */
    Long getUserId(final User user);
}


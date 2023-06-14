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
    Collection<User> getUserDetails();

    /**
     * <p>
     *     Updates user details
     * </p>
     *
     * @param user Represents the user to be update
     * @return true if the user details are updated, false otherwise
     */
    boolean updateDetail(final User user);

    /**
     * <p>
     *     Deletes user details.
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return true if the user details are successfully deleted, false otherwise
     */
    boolean deleteDetail(final Long id);

    /**
     * <p>
     *     Validates user sign-in details.
     * </p>
     *
     * @param user Represents user to sign in
     * @return true if the sign-in is successful, false otherwise
     */
    boolean signInDetail(final User user);

    /**
     * <p>
     *     Retrieves a user by id
     * </p>
     *
     * @param id Represents the id of the user to retrieve
     * @return {@link User}
     */
    User getUser(final Long id);

}


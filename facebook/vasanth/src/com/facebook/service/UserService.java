package com.facebook.service;

import com.facebook.model.Post;
import com.facebook.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides service for the user
 *
 * @version 1.0
 * @author vasanth
 */
public interface UserService {

    /**
     * Given the user add details
     *
     * @param user the user has to add
     * @return the id of the user
     */
    boolean add(final User user);

    /**
     * Given the user get details
     *
     * @return user detail
     */
    Collection<User> getUserDetails();

    /**
     * Given the user update details
     *
     * @param user the user has to update
     * @return true,if the condition is correct otherwise false
     */
    boolean updateDetail(final User user);

    /**
     * Given the user delete details
     *
     * @param id the id has to delete
     * @return true, if the condition is true otherwise false
     */
    boolean deleteDetail(final Long id);

    /**
     * Given the user sign in details
     *
     * @param user the user has to sign in
     * @return true,if the sign in success otherwise false
     */
    boolean signInDetail(final User user);

    /**
     * Gets the user through id
     *
     * @param id to get a user
     * @return id of the user
     */
    User getUser(final Long id);

}


package com.facebook.controller;

import com.facebook.model.Post;
import com.facebook.model.User;
import com.facebook.service.UserService;
import com.facebook.service.serviceImpl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Given controller act us for request and respond
 *
 * @version 1.0
 * @author vasanth
 */
public class UserController {

    private static final UserService USER_SERVICE = new UserServiceImpl();

    /**
     * Checks the user to be created
     *
     * @param user the user has to added
     * @return Returns the userService object through addUser
     */
    public boolean create(final User user) {
        return USER_SERVICE.add(user);
    }

    /**
     * Gets the user details
     *
     * @return Returns the userService object through getUser
     */
    public Collection<User> getUserDetails() {
        return USER_SERVICE.getUserDetails();
    }

    /**
     * Checks the user to be updated
     *
     * @param user the user has to update
     * @return Returns the userService object through updateUser
     */
    public boolean isUpdate(final User user) {
        return USER_SERVICE.updateDetail(user);
    }

    /**
     * Checks the user sign in
     *
     * @param user the user has to sign in
     * @return Returns the userService object through signInUser
     */
    public boolean isSignIn(final User user) {
        return USER_SERVICE.signInDetail(user);
    }

    /**
     * Gets the user delete details
     *
     * @param id the id has to delete
     * @return Returns the userService object through deleteUser
     */
    public boolean isDelete(final Long id) {
        return USER_SERVICE.deleteDetail(id);
    }

    /**
     * Gets the user detail
     *
     * @param id to get the user through id
     * @return user detail
     */
    public User getUser(final Long id) {
        return USER_SERVICE.getUser(id);
    }

}


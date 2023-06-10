package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.UserService;
import com.facebook.service.serviceImpl.UserServiceImpl;

import java.util.Collection;

/**
 * Given controller act us for request and respond
 *
 * @version 1.0
 * @author vasanth
 */
public class UserController {

    private static UserController USER_CONTROLLER;
    private static final UserService USER_SERVICE = new UserServiceImpl();


    UserController() {}

    public static UserController getUserController() {

        if (USER_CONTROLLER == null) {
            USER_CONTROLLER = new UserController();
        }
        return USER_CONTROLLER;
    }




    /**
     * Checks the user to be created
     *
     * @param user the user has to created
     * @return returns the userService object through addUser
     */
    public boolean create(final User user) {
        return USER_SERVICE.add(user);
    }

    /**
     * Retrieves the user details.
     *
     * @return collection of user details.
     */
    public Collection<User> getUserDetails() {
        return USER_SERVICE.getUserDetails();
    }

    /**
     * Updates the user details.
     *
     * @param user the user has to updated
     * @return returns the userService object through updateUser
     */
    public boolean isUpdate(final User user) {
        return USER_SERVICE.updateDetail(user);
    }

    /**
     * Checks the user sign in
     *
     * @param id the user id has to sign in
     * @return returns the userService object through signInUser
     */
    public boolean isSignIn(final Long id) {
        return USER_SERVICE.signInDetail(id);
    }

    /**
     * Gets the user delete details
     *
     * @param id the id has to delete
     * @return returns the userService object through deleteUser
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


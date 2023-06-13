package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.UserService;
import com.facebook.service.serviceImpl.UserServiceImpl;

import java.util.Collection;

/**
 * <p>
 *     Given controller act us for request and respond
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class UserController {

    private static UserController USER_CONTROLLER;
    private static final UserService USER_SERVICE = UserServiceImpl.getUserServiceImpl();

    public UserController() {}

    public static UserController getUserController() {

        if (USER_CONTROLLER == null) {
            USER_CONTROLLER = new UserController();
        }
        return USER_CONTROLLER;
    }

    /**
     * <p>
     *     Checks the user to be created
     * </p>
     *
     * @param user the user has to created
     * @return returns the userService object through addUser
     */
    public boolean create(final User user) {
        return USER_SERVICE.add(user);
    }

    /**
     * <p>
     *     Retrieves the user details.
     * </p>
     *
     * @return collection of user details.
     */
    public Collection<User> getUserDetails() {
        return USER_SERVICE.getUserDetails();
    }

    /**
     * <p>
     *     Updates the user details.
     * </p>
     *
     * @param user the user has to updated
     * @return returns the userService object through updateUser
     */
    public boolean isUpdate(final User user) {
        return USER_SERVICE.updateDetail(user);
    }

    /**
     * <p>
     *     Checks the user sign in
     * </p>
     *
     * @param user the user has to sign in
     * @return returns the userService object through signInUser
     */
    public boolean isSignIn(final User user) {
        return USER_SERVICE.signInDetail(user);
    }

    /**
     * <p>
     *     Gets the user delete details
     * </p>
     *
     * @param id the id has to delete
     * @return returns the userService object through deleteUser
     */
    public boolean isDelete(final Long id) {
        return USER_SERVICE.deleteDetail(id);
    }

    /**
     * <p>
     *     Gets the user detail
     * </p>
     *
     * @param id to get the user through id
     * @return user detail
     */
    public User getUser(final Long id) {
        return USER_SERVICE.getUser(id);
    }

}


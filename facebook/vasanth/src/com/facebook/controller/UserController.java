package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.UserService;
import com.facebook.service.Impl.UserImpl;

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
    private static final UserService USER_SERVICE = UserImpl.getInstance();

    private UserController() {}

    public static UserController getInstance() {
        return null == USER_CONTROLLER ? USER_CONTROLLER = new UserController() : USER_CONTROLLER;
    }

    /**
     * <p>
     *     Checks the user to be created
     * </p>
     *
     * @param user Represents user has to created
     * @return boolean - True if the user is created, false otherwise.
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
    public Collection<User> get() {
        return USER_SERVICE.get();
    }

    /**
     * <p>
     *     Updates the user details.
     * </p>
     *
     * @param user Represents user has to updated
     * @return boolean - True if the user is updated, false otherwise.
     */
    public boolean isUpdate(final User user) {
        return USER_SERVICE.update(user);
    }

    /**
     * <p>
     *     Checks the user sign in
     * </p>
     *
     * @param user Represents user has to sign in
     * @return boolean - True if the user is sign in, false otherwise.
     */
    public boolean isSignIn(final User user) {
        return USER_SERVICE.signIn(user);
    }

    /**
     * <p>
     *     Checks the user delete details
     * </p>
     *
     * @param id Represents the user id to delete
     * @return boolean - True if the user is deleted, false otherwise.
     */
    public boolean isDelete(final Long id) {
        return USER_SERVICE.delete(id);
    }

    /**
     * <p>
     *     Gets the user detail
     * </p>
     *
     * @param id Represents the user through id
     * @return {@link User}
     */
    public User getById(final Long id) {
        return USER_SERVICE.getById(id);
    }

    public Long getUserId(final User user) {
        return USER_SERVICE.getUserId(user);
    }

}


package com.facebook.service.Impl;

import com.facebook.model.User;
import com.facebook.service.UserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * <p>
 * Implements the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static UserService userServiceImpl;
    private static final List<User> USER_LIST = new ArrayList<>();

    /**
     * <p>
     * Default constructor for user service
     * </p>
     */
    private UserServiceImpl() {
    }

    /**
     * <p>
     * Gets the instance of user service implementation
     * </p>
     *
     * @return Returns the singleton instance of the user service implementation class.
     */
    public static UserService getInstance() {
        if (null == userServiceImpl) {
            userServiceImpl = new UserServiceImpl();
        }

        return userServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Refers {@link User} to be add
     * @return True if the user is successfully added, false otherwise
     */
    public boolean signUp(final User user) {
        for (final User existingUser : USER_LIST) {
            return !(existingUser.getMobileNumber().equals(user.getMobileNumber()));
        }

        return USER_LIST.add(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Refers {@link User} to get the id
     * @return Returns the id of the user
     */
    public Long getUserId(final User user) {
        for (final User existingUser : USER_LIST) {

            if (existingUser.getName().equals(user.getName())) {
                return existingUser.getId();
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Refers {@link User} to be update
     * @param id Represents the user id
     * @return True if the user details are updated, false otherwise
     */
    public boolean update(final User user, Long id) {
        for (User existingUser : USER_LIST) {

            if (existingUser.getId().equals(id)) {
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                existingUser.setMobileNumber(user.getMobileNumber());

                return true;
            }
        }

        return false;
    }


    /**
     * {@inheritDoc}
     *
     * @param user Refers {@link User} to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    public boolean signIn(final User user) {
        if (null != user.getEmail()) {
            return user_email(user);
        } else {
            return user_mobile_number(user);
        }
    }

    public boolean user_mobile_number (final User user) {
        for (User existingUser : USER_LIST) {

            if ((user.getMobileNumber().equals(existingUser.getMobileNumber()) &&
                    user.getPassword().equals(existingUser.getPassword()))) {
                return true;
            }
        }
        return false;
    }
    public boolean user_email (final User user) {
        for (User existingUser : USER_LIST) {
            if (existingUser.getEmail().equals(user.getEmail()) &&
                    existingUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents the id of the user to be deleted
     * @return True if the user details are successfully deleted, false otherwise
     */
    public boolean delete(final Long id) {
        final User user = get(id);

        if (null != user) {
            return USER_LIST.remove(user);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents the id of the user to retrieve
     * @return Returns {@link User} through id
     */
    public User get(final Long id) {
        for (final User existingUser : USER_LIST) {

            if (existingUser.getId().equals(id)) {
                return existingUser;
            }
        }

        return null;
    }
}


package com.facebook.service.Impl;

import com.facebook.model.User;
import com.facebook.service.UserService;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * <p>
 * Implements the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserImpl implements UserService {

    private static UserImpl userImpl;
    private static final List<User> USER_LIST = new ArrayList<>();

    /**
     * <p>
     * Default constructor for user service
     * </p>
     */
    private UserImpl() {
    }

    /**
     * <p>
     * Gets the instance of user service implementation
     * </p>
     *
     * @return Returns the singleton instance of the user service implementation class.
     */
    public static UserImpl getInstance() {
        if (null == userImpl) {
            userImpl = new UserImpl();
        }

        return userImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user {@link User}Represents the user to be add
     * @return True if the user is successfully added, false otherwise
     */
    public boolean add(final User user) {
        for (final User existingUser : USER_LIST) {

            return !(existingUser.getMobileNumber().equals(user.getMobileNumber()));
        }
        return USER_LIST.add(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user {@link User}Refers the user to get the id
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
     * @param user {@link User}Represents the user to be update
     * @param id Represents the user id
     * @return True if the user details are updated, false otherwise
     */
    public boolean update(final User user, Long id) {
        final ListIterator<User> listIterator = USER_LIST.listIterator();

        while (listIterator.hasNext()) {
            final User existingUser = listIterator.next();

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
     * @param user {@link User}Represents user to sign in
     * @return True if the sign-in is successful, false otherwise
     */
    public boolean signIn(final User user) {
        final Iterator<User> iterator = USER_LIST.iterator();

        while (iterator.hasNext()) {
            final User existingUser = iterator.next();

            if (existingUser.getMobileNumber().equals(user.getMobileNumber()) ||
                    existingUser.getEmail().equals(user.getEmail()) &&
                            (existingUser.getPassword().equals(user.getPassword()))) {
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
     * @return {@link User}
     */
    public User get(final Long id) {
        final Iterator<User> iterator = USER_LIST.iterator();

        while (iterator.hasNext()) {
            final User existingUser = iterator.next();

            if (existingUser.getId().equals(id)) {
                return existingUser;
            }
        }

        return null;
    }
}


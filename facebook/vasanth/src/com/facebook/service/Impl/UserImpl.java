package com.facebook.service.Impl;

import com.facebook.model.User;
import com.facebook.service.UserService;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * <p>
 *     Implements the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserImpl implements UserService {

    private static final List<User> USER_LIST = new ArrayList<>();
    private static UserImpl USER_IMPL;

    private UserImpl() {}

    public static UserImpl getInstance() {
        return null == USER_IMPL ? USER_IMPL = new UserImpl() : USER_IMPL;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the user to be add
     * @return true if the user is successfully added, false otherwise
     */
    public boolean add(final User user) {
        for (final User existingUser : USER_LIST) {

            if (existingUser.getMobileNumber().equals(user.getMobileNumber())) {
                return false;
            }
        }
        return USER_LIST.add(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Refers the user to get the id
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
     *  @return the collection of users
     */
    public Collection<User> get() {
        return USER_LIST;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the user to be update
     * @return true if the user details are updated, false otherwise
     */
    public boolean update(final User user) {
        final ListIterator<User> listIterator = USER_LIST.listIterator();

        while (listIterator.hasNext()) {
            final User existingUser = listIterator.next();

            if (existingUser.getId().equals(user.getId())) {
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());

                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     *  @param user Represents user to sign in
     *  @return true if the sign-in is successful, false otherwise
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
     * @return true if the user details are successfully deleted, false otherwise
     */
    public boolean delete(final Long id) {
        final User user = getById(id);

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
    public User getById(final Long id) {
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


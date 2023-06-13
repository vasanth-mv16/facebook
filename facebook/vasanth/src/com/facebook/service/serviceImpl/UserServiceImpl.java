package com.facebook.service.serviceImpl;

import com.facebook.model.User;
import com.facebook.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * <p>
 *     Implements the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static final Collection<User> USER_LIST = new ArrayList<>();
    private static UserServiceImpl USER_SERVICE_IMPL;

    public UserServiceImpl() {}

    public static UserServiceImpl getUserServiceImpl() {

        if (USER_SERVICE_IMPL == null) {
            USER_SERVICE_IMPL = new UserServiceImpl();
        }
        return USER_SERVICE_IMPL;
    }


    /**
     * {@inheritDoc}
     */
    public boolean add(final User user) {
        for (final User existingUser : USER_LIST) {

            if (existingUser.getName().equals(user.getName())) {
                return false;
            }
        }
        return USER_LIST.add(user);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<User> getUserDetails() {
        return USER_LIST;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateDetail(final User user) {
        final Iterator<User> iterator = USER_LIST.iterator();

        while (iterator.hasNext()) {
            final User existingUser = iterator.next();

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
     */
    public boolean signInDetail(final User user) {
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
     */
    public boolean deleteDetail(final Long id) {
        final User user = getUser(id);

        if (user != null) {
            return USER_LIST.remove(user);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    public User getUser(final Long id) {
        final Iterator<User> iterator = USER_LIST.iterator();

        while (iterator.hasNext()) {
            final User existingUser = iterator.next();

            if (existingUser.getId() == id) {
                return existingUser;
            }
        }

        return null;
    }

}


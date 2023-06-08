package com.facebook.service.serviceImpl;

import com.facebook.model.User;
import com.facebook.service.UserService;

import java.util.*;

/**
 * Implements the following services for the user
 *
 * @author vasanth
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static final List<User> USER_LIST = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    public boolean add(final User user) {
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
        final Iterator<User> iterator = USER_LIST.iterator();

        while (iterator.hasNext()) {
            final User existingUser = iterator.next();

            if (existingUser.getId() == id) {
                iterator.remove();

                return true;
            }
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


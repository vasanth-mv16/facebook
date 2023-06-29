package com.facebook.service.Impl2;

import com.facebook.DAOConnection.ImplDAO.UserDAOImpl;
import com.facebook.model.User;
import com.facebook.service.UserService;

public class UserServiceDAOImpl implements UserService {

    private static final UserDAOImpl USER_DAO = new UserDAOImpl();

    public boolean signUp(final User user) {
        return USER_DAO.signUp(user);
    }

    @Override
    public boolean update(User user, Long id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean signIn(final User user) {
        return (null != user.getEmail() ?user_email(user) : user_mobileNumber(user));
    }

    public boolean user_mobileNumber(final User user) {
        return USER_DAO.user_mobileNumber(user);
    }

    public boolean user_email(final User user) {
        return USER_DAO.user_email(user);
    }
    @Override
    public User get(Long id) {
        return USER_DAO.get(id);
    }

    @Override
    public Long getUserId(User user) {
        return null;
    }
}

package com.facebook.DAOConnection.ImplDAO;

import com.facebook.DAOConnection.Connection;
import com.facebook.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl {

    public boolean signUp(final User user) {
        final String sql = "insert into users(name, phonenumber, password, email, gender, dateofbirth ) values(?, ?, ?, ?, ?, ?)";

        try {
            final PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getDateOfBirth());
            preparedStatement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean user_mobileNumber (final User user) {
        final String sql = "select * from users where phonenumber = ? and password = ?;";

        try {
            final PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2,user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final Exception exception) {
            System.out.println(exception);
        }
        return false;
    }

    public boolean user_email (final User user) {
        final String sql = "select * from users where email = ? and password = ?;";

        try {
            final PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final Exception exception) {
            System.out.println(exception);
        }
        return false;
    }

    public User get (final Long id) {
        final String sql = "select * from users where id = ?";

        try {
            final PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getString("gender"));
                user.setDateOfBirth(resultSet.getString("dateofbirth"));

                return user;
            }
        } catch (final Exception exception) {
            System.out.println(exception);
        }
        return null;
    }
}

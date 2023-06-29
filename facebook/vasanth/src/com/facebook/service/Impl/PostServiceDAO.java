package com.facebook.service.Impl;

import com.facebook.DAOConnection.Connection;
import com.facebook.model.Post;

import java.sql.PreparedStatement;

public class PostServiceDAO {

    public boolean insert_post(Post posts) {
        final String sql = "insert into posts values (?,?,?,?,?);";

        try {
            PreparedStatement preparedStatement = Connection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, posts.getId());
            preparedStatement.setString(2, posts.getCaption());
            preparedStatement.setString(3, posts.getLocation());
            preparedStatement.setLong(4, posts.getUserId());
            preparedStatement.setTimestamp(5,posts.getUploadTime());
            preparedStatement.executeUpdate();

        } catch (Exception exception) {
            System.out.println(exception);
        }
        return false;
    }
}

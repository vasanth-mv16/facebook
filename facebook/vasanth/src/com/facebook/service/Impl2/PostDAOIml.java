package com.facebook.service.Impl2;

import com.facebook.DAOConnection.JDBCConnection;
import com.facebook.model.Post;
import com.facebook.service.PostService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class PostDAOIml implements PostService {

    private static PostService postDAOImpl;

    private PostDAOIml() {
    }

    public static PostService getInstance() {
        if (null == postDAOImpl) {
            postDAOImpl = new PostDAOIml();
        }

        return postDAOImpl;
    }

    @Override
    public boolean create(final Post posts) {
        final String sql = "insert into posts(user_id, caption, location, uploadtime) values (?,?,?,?);";

        try {
            PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, posts.getUserId());
            preparedStatement.setString(2, posts.getCaption());
            preparedStatement.setString(3, posts.getLocation());
            preparedStatement.setTimestamp(4,posts.getUploadTime());
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public Collection<Post> getAll(final Long user_id) {
        final Collection<Post> POSTS = new ArrayList<>();
        final String sql = "select * from posts where user_id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1,user_id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Post post = new Post();

                post.setUserId(resultSet.getLong("user_id"));
                post.setId(resultSet.getLong("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setLocation(resultSet.getString("location"));
                post.setUploadTime(resultSet.getTimestamp("uploadtime"));
                POSTS.add(post);

            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return POSTS;
    }

    @Override
    public Post get(final Long id) {
        final String sql = "select * from posts where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Post post = new Post();

                post.setUserId(resultSet.getLong("user_id"));
                post.setId(resultSet.getLong("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setLocation(resultSet.getString("location"));
                post.setUploadTime(resultSet.getTimestamp("uploadtime"));

                return post;
            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    @Override
    public boolean update(final Post post) {
        final String sql = "update posts set caption = ?, location = ? where id = ? AND user_id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, post.getCaption());
            preparedStatement.setString(2, post.getLocation());
            preparedStatement.setLong(3, post.getId());
            preparedStatement.setLong(4, post.getUserId());
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(final Long id) {
        final String sql = "delete from posts where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }
}

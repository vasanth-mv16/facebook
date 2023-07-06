package com.facebook.service.Impl2;

import com.facebook.DAOConnection.JDBCConnection;
import com.facebook.model.Like;
import com.facebook.service.LikeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class LikeDAOImpl implements LikeService {

    private static LikeService likeDAOImpl;

    private LikeDAOImpl() {
    }

    public static LikeService getInstance() {
        if(null == likeDAOImpl) {
            likeDAOImpl = new LikeDAOImpl();
        }

        return likeDAOImpl;
    }
    @Override
    public boolean create(final Like like) {
        final String sql = "insert into likes(user_id, post_id) values (?,?);";

        try {
            PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, like.getUserId());
            preparedStatement.setLong(2, like.getPostId());
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public Collection<Like> get(final Long userId) {
        final Collection<Like> likes = new ArrayList<>();
        final String sql = "select * from likes where user_id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Like like = new Like();

                like.setUserId(resultSet.getLong("user_id"));
                like.setPostId(resultSet.getLong("post_id"));
                like.setId(resultSet.getLong("id"));
                likes.add(like);
            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return likes;
    }

    @Override
    public Long getCount(final Long postId) {
        final String sql = "select post_id, count(post_id) as post_count from likes where post_id = ? group by post_id;";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Like like = new Like();

                like.setPostId(resultSet.getLong("post_id"));
            }
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return postId;
    }

    @Override
    public boolean delete(Long likeId) {
        final String sql = "delete from likes where id = ?";

        try (final PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, likeId);
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }
}

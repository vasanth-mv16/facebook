package com.facebook.service.Impl2;

import com.facebook.DAOConnection.JDBCConnection;
import com.facebook.model.Comment;
import com.facebook.service.CommentService;

import java.sql.PreparedStatement;

public class CommentDAOImpl implements CommentService {

    private static CommentService commentDAOImpl;

    private CommentDAOImpl() {}

    public static CommentService getInstance() {
        if(null == commentDAOImpl) {
            commentDAOImpl = new CommentDAOImpl();
        }

        return commentDAOImpl;
    }

    @Override
    public boolean create(final Comment comment) {
        final String sql = "insert into comments(user_id, post_id) values (?,?);";

        try {
            PreparedStatement preparedStatement = JDBCConnection.getConnection().prepareStatement(sql);

            preparedStatement.setLong(1, comment.getUserId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.executeUpdate();

            return true;
        } catch (final Exception exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        final String sql = "delete from comments where id = ?";

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

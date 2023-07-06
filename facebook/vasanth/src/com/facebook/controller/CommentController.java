package com.facebook.controller;

import com.facebook.model.Comment;
import com.facebook.service.CommentService;
import com.facebook.service.Impl2.CommentDAOImpl;

public class CommentController {

    private static CommentController commentController;
    private static final CommentService COMMENT_DAO = CommentDAOImpl.getInstance();
    private CommentController() {}

    public static CommentController getInstance() {
        if(null == commentController) {
            commentController = new CommentController();
        }
        return commentController;
    }

    public boolean create(final Comment comment) {
        return COMMENT_DAO.create(comment);
    }

    public boolean delete(final Long id) {
        return COMMENT_DAO.delete(id);
    }
}

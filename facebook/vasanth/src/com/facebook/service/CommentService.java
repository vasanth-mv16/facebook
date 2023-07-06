package com.facebook.service;

import com.facebook.model.Comment;

public interface CommentService {

    boolean create(final Comment comment);

    boolean delete(final Long id);
}

package com.facebook.model;


/**
 * Represents the post like
 *
 * @version 1.0
 * @author vasanth
 */
public class Like {

    private Long userId;
    private Long postId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(final Long postId) {
        this.postId = postId;
    }

    public String toString() {
        return String.format(" USER ID =%d POST ID =%d", userId,postId);
    }
}

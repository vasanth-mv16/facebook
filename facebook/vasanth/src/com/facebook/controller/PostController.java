package com.facebook.controller;

import com.facebook.model.Post;
import com.facebook.model.User;
import com.facebook.service.PostService;
import com.facebook.service.serviceImpl.PostServiceImpl;

import java.util.Collection;

/**
 * Given controller acts as request and respond
 *
 * @version 1.0
 * @author vasanth
 */
public class PostController {

    private static final PostService POST_SERVICE = new PostServiceImpl();

    /**
     * Checks the post to be created
     *
     * @param post to create the post
     * @return post of the user
     */
    public boolean isPostCreate(final Post post) {
        return POST_SERVICE.addPost(post);
    }

    /**
     * Gets the post details
     *
     * @return post of the user
     */
    public Collection<Post> getPostDetails() {
        return POST_SERVICE.getPost();
    }

    /**
     * Gets the post detail using id
     *
     * @param id the id has to get the post
     * @return get post for specific id
     */
    public Post getPostDetailUsingId(final Long id) {
        return POST_SERVICE.getPostUsingId(id);
    }

    /**
     * Checks the post is updated
     *
     * @param post to update the post
     * @return updated post
     */
    public boolean isPostUpdate(final Post post) {
        return POST_SERVICE.updatePostDetail(post);
    }

}

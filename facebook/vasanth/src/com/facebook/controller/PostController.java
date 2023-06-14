package com.facebook.controller;

import com.facebook.model.Post;
import com.facebook.service.PostService;
import com.facebook.service.serviceImpl.PostServiceImpl;

import java.util.Collection;

/**
 * <p>
 *     Given controller acts as request and respond
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class PostController {

    private static PostController POST_CONTROLLER;
    private static final PostService POST_SERVICE = PostServiceImpl.getInstance();

    private PostController() {}

    public static PostController getInstance() {
        return (null == POST_CONTROLLER) ? POST_CONTROLLER = new PostController() : POST_CONTROLLER;
    }

    /**
     * <p>
     *     Checks the post to be created
     * </p>
     *
     * @param post to create the post
     * @return boolean - True if the post is created, false otherwise.
     */
    public boolean Create(final Post post) {
        return POST_SERVICE.create(post);
    }

    /**
     * <p>
     *     Gets the post details
     * </p>
     *
     * @return Collection of post of the user
     */
    public Collection<Post> get() {
        return POST_SERVICE.get();
    }

    /**
     * <p>
     *     Gets the post detail using id
     * </p>
     *
     * @param id Represents the id of the post
     * @return {@link Post}
     */
    public Post getUsingId(final Long id) {
        return POST_SERVICE.getUsingId(id);
    }

    /**
     * <p>
     *     Checks the post is updated
     * </p>
     *
     * @param post Represents the post to update
     * @return boolean - True if the post is updated, false otherwise.
     */
    public boolean isPostUpdate(final Post post) {
        return POST_SERVICE.update(post);
    }

}

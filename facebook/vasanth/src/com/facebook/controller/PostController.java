package com.facebook.controller;

import com.facebook.model.Post;
import com.facebook.service.PostService;
import com.facebook.service.Impl.PostImpl;

import java.util.Collection;

/**
 * <p>
 * Given controller acts as request and respond
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostController {

    private static PostController postController;
    private static final PostService POST_SERVICE = PostImpl.getInstance();

    /**
     * <p>
     * Default constructor for post controller
     * </p>
     */
    private PostController() {
    }

    /**
     * <p>
     * Gets the instance of post controller
     * </p>
     *
     * @return Returns the singleton instance of the post controller class.
     */
    public static PostController getInstance() {
        if (null == postController) {
            postController = new PostController();
        }

        return postController;
    }

    /**
     * <p>
     * Checks the post to be created
     * </p>
     *
     * @param post {@link Post}To create the post
     * @return True if the post is created, false otherwise.
     */
    public boolean create(final Post post) {
        return POST_SERVICE.create(post);
    }

    /**
     * <p>
     * Gets the post details
     * </p>
     *
     * @return Collection of post of the user
     */
    public Collection<Post> get() {
        return POST_SERVICE.get();
    }

    /**
     * <p>
     * Gets the post detail using id
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
     * Checks the post is updated
     * </p>
     *
     * @param post {@link Post}Represents the post to update
     * @return True if the post is updated, false otherwise.
     */
    public boolean update(final Post post) {
        return POST_SERVICE.update(post);
    }
}

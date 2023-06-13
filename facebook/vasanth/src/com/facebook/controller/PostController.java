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
    private static final PostService POST_SERVICE = PostServiceImpl.getPostServiceImpl();

    private PostController() {}

    public static PostController getPostController() {
        if (POST_CONTROLLER == null) {
            POST_CONTROLLER = new PostController();
        }
        return POST_CONTROLLER;
    }

    /**
     * <p>
     *     Checks the post to be created
     * </p>
     *
     * @param post to create the post
     * @return post of the user
     */
    public boolean Create(final Post post) {
        return POST_SERVICE.create(post);
    }

    /**
     * <p>
     *     Gets the post details
     * </p>
     *
     * @return post of the user
     */
    public Collection<Post> get() {
        return POST_SERVICE.get();
    }

    /**
     * <p>
     *     Gets the post detail using id
     * </p>
     *
     * @param id the id has to get the post
     * @return get post for specific id
     */
    public Post getUsingId(final Long id) {
        return POST_SERVICE.getUsingId(id);
    }

    /**
     * <p>
     *     Checks the post is updated
     * </p>
     *
     * @param post to update the post
     * @return updated post
     */
    public boolean isPostUpdate(final Post post) {
        return POST_SERVICE.update(post);
    }

}

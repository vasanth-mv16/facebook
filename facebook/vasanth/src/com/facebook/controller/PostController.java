package com.facebook.controller;

import com.facebook.model.Post;
import com.facebook.service.Impl2.PostDAOIml;
import com.facebook.service.PostService;
import com.facebook.service.Impl.PostServiceImpl;

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
    private static final PostService POST_SERVICE_DAO = PostDAOIml.getInstance();
    private static final PostService POST_SERVICE = PostServiceImpl.getInstance();

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
     * @param post Refer {@link Post} to create
     * @return True if the post is created, false otherwise.
     */
    public boolean create(final Post post) {
        return POST_SERVICE_DAO.create(post);
    }

    /**
     * <p>
     * Gets the post details
     * </p>
     *
     * @return Collection of post of the user
     */
    public Collection<Post> getALl(final Long userId) {
        return POST_SERVICE_DAO.getAll(userId);
    }

    /**
     * <p>
     * Gets the post detail using id
     * </p>
     *
     * @param id Represents the id of the post
     * @return Returns {@link Post} of the user by id
     */
    public Post get(final Long id) {
        return POST_SERVICE_DAO.get(id);
    }

    /**
     * <p>
     * Checks the post is updated
     * </p>
     *
     * @param post Refers {@link Post} to update
     * @return True if the post is updated, false otherwise.
     */
    public boolean update(final Post post) {
        return POST_SERVICE_DAO.update(post);
    }

    public boolean delete(final Long id) {
        return POST_SERVICE_DAO.delete(id);
    }
}

package com.facebook.controller;

import com.facebook.model.Like;
import com.facebook.service.LikeService;
import com.facebook.service.Impl.LikeServiceImpl;

import java.util.Collection;

/**
 * <p>
 *    Given controller acts as request and respond
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class LikeController {

    private static LikeController likeController;
    private static final LikeService LIKE_SERVICE = LikeServiceImpl.getInstance();

    /**
     * <p>
     * Default constructor for like controller
     * </p>
     */
    private LikeController() {}

    /**
     * <p>
     * Gets the instance of like controller
     * </p>
     *
     * @return Returns the singleton instance of the like controller class.
     */
    public static LikeController getInstance() {
        if (null == likeController) {
            likeController = new LikeController();
        }

        return likeController;
    }

    /**
     * <p>
     *    Checks the like to be created
     * </p>
     *
     * @param like {@link Like}Represents like to created
     * @return True if the like is created, false otherwise.
     */
    public boolean create(final Like like) {
        return LIKE_SERVICE.create(like);
    }

    /**
     * <p>
     *    Gets the like details
     * </p>
     *
     * @return Collection of like of the post
     */
    public Collection<Like> get() {
        return LIKE_SERVICE.get();
    }

    /**
     * <p>
     *    Gets the like count details
     * </p>
     *
     * @param postId Represents the user id has to get the post
     * @return returns the like count of the post
     */
    public Long getCount(final Long postId) {
        return LIKE_SERVICE.getCount(postId);
    }
}

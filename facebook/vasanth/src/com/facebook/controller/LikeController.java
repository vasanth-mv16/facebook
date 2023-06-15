package com.facebook.controller;

import com.facebook.model.Like;
import com.facebook.service.LikeService;
import com.facebook.service.Impl.LikeImpl;

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

    private static LikeController LIKE_CONTROLLER;
    private static final LikeService LIKE_SERVICE = LikeImpl.getInstance();

    private LikeController() {}

    public static LikeController getInstance() {
        return LIKE_CONTROLLER == null ? LIKE_CONTROLLER = new LikeController() : LIKE_CONTROLLER;
    }

    /**
     * <p>
     *    Checks the like to be created
     * </p>
     *
     * @param like Represents like to created
     * @return boolean - True if the like is created, false otherwise.
     */
    public boolean isCreate(final Like like) {
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

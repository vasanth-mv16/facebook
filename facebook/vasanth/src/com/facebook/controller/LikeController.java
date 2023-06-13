package com.facebook.controller;

import com.facebook.model.Like;
import com.facebook.service.LikeService;
import com.facebook.service.serviceImpl.LikeServiceImpl;

import java.util.Collection;

/**
 * Given controller acts as request and respond
 *
 * @version 1.0
 * @author vasanth
 */
public class LikeController {

    private static LikeController LIKE_CONTROLLER;
    private static final LikeService LIKE_SERVICE = new LikeServiceImpl();

    private LikeController() {}

    public static LikeController getLikeController() {

        if (LIKE_CONTROLLER == null) {
            LIKE_CONTROLLER = new LikeController();
        }
        return LIKE_CONTROLLER;
    }
    /**
     * Checks the like to be created
     *
     * @param like to create the like
     * @return like of the post
     */
    public boolean createLike(final Like like) {
        return LIKE_SERVICE.createLike(like);
    }

    /**
     * Gets the like details
     *
     * @return like of the post
     */
    public Collection<Like> getAllLike() {
        return LIKE_SERVICE.getLikeList();
    }

    /**
     * Gets the like count details
     *
     * @param userId the user id has to get the post
     * @return get the like count of the post
     */
    public long getLikeCount(final Long userId) {
        return LIKE_SERVICE.getLikeCount(userId);
    }
}

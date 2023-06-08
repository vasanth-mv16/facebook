package com.facebook.service;

import com.facebook.model.Like;

import java.util.Collection;


/**
 * Provides the service for the like
 *
 * @version 1.0
 * @author vasanth
 */
public interface LikeService {

    /**
     * Checks the like is created
     *
     * @param like to create the like
     * @return like of the post
     */
    boolean createLike(final Like like);

    /**
     * Gets the like list details
     *
     * @return collection of likes
     */
    Collection<Like> getLikeList();

    /**
     * Gets the like count for the user
     *
     * @param userId to get the like count
     * @return get the like count of the user
     */
    Long getLikeCount(final Long userId);
}

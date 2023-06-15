package com.facebook.service;

import com.facebook.model.Like;

import java.util.Collection;

/**
 * <p>
 *    Provides the service for the like
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public interface LikeService {

    /**
     * <p>
     *    Checks the like is created
     * </p>
     *
     * @param like Represents the like to create
     * @return boolean - True if the like is created, false otherwise.
     */
    boolean create(final Like like);

    /**
     * <p>
     *    Gets the like list details
     * </p>
     *
     * @return Collection of likes
     */
    Collection<Like> get();

    /**
     * <p>
     *    Gets the like count for the user
     * </p>
     *
     * @param postId Represents the user id to get the like count
     * @return Gets the like count of the user
     */
    Long getCount(final Long postId);
}

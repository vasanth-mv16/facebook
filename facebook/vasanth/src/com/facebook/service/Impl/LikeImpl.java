package com.facebook.service.Impl;

import com.facebook.model.Like;
import com.facebook.service.LikeService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * Implements the following services for the like
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class LikeImpl implements LikeService {

    private static LikeImpl likeImpl;
    private static final Collection<Like> LIKE_LIST = new ArrayList<>();

    /**
     * <p>
     * Default constructor for like service implementation
     * </p>
     */
    private LikeImpl() {}

    /**
     * <p>
     * Gets the instance of like service implementation
     * </p>
     *
     * @return Returns the singleton instance of the like service implementation class.
     */
    public static LikeImpl getInstance() {
        if (null == likeImpl) {
            likeImpl = new LikeImpl();
        }

        return likeImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param like {@link Like}Represents the like to create
     * @return boolean - True if the like is created, false otherwise.
     */
    public boolean create(final Like like) {
        return LIKE_LIST.add(like);
    }

    /**
     * {@inheritDoc}
     *
     * @return Collection of likes
     */
    public Collection<Like> get() {
        return LIKE_LIST;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents the post id to get the like count
     * @return Gets the like count of the user
     */
    public Long getCount(final Long postId) {
        Long likeCount = 0L;

        for (final Like like : LIKE_LIST) {
            if (like.getPostId().equals(postId)) {
                likeCount++;
            }
        }
        return likeCount;
    }

}

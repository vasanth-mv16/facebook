package com.facebook.service.serviceImpl;

import com.facebook.model.Like;
import com.facebook.service.LikeService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implements the following services for the like
 *
 * @version 1.0
 * @author vasanth
 */
public class LikeServiceImpl implements LikeService {

    private static final Collection<Like> LIKE_LIST = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    public boolean createLike(final Like like) {
        return LIKE_LIST.add(like);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Like> getLikeList() {
        return LIKE_LIST;
    }

    /**
     * {@inheritDoc}
     */
    public Long getLikeCount(final Long userId) {
        long likeCount = 0;
        for (final Like like : LIKE_LIST) {
            if (like.getUserId().equals(userId)) {
                likeCount++;
            }
        }

        return likeCount;
    }

}

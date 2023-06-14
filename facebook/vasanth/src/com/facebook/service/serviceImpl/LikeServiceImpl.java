package com.facebook.service.serviceImpl;

import com.facebook.model.Like;
import com.facebook.service.LikeService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 *    Implements the following services for the like
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class LikeServiceImpl implements LikeService {

    private static LikeServiceImpl LIKE_SERVICEIMPL;
    private static final Collection<Like> LIKE_LIST = new ArrayList<>();

    private LikeServiceImpl() {}

    public static LikeServiceImpl getInstance() {
        return (null == LIKE_SERVICEIMPL) ? LIKE_SERVICEIMPL = new LikeServiceImpl() : LIKE_SERVICEIMPL;
    }
    /**
     * {@inheritDoc}
     *
     * @param like Represents the like to create
     * @return boolean - True if the like is created, false otherwise.
     */
    public boolean createLike(final Like like) {
        return LIKE_LIST.add(like);
    }

    /**
     * {@inheritDoc}
     *
     * @return Collection of likes
     */
    public Collection<Like> getLikeList() {
        return LIKE_LIST;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Represents the post id to get the like count
     * @return Gets the like count of the user
     */
    public Long getLikeCount(final Long postId) {
        Long likeCount = 0L;
        for (final Like like : LIKE_LIST) {
            if (like.getPostId().equals(postId)) {
                likeCount++;
            }
        }

        return likeCount;
    }

}

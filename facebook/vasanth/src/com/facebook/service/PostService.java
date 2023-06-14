package com.facebook.service;

import com.facebook.model.Post;

import java.util.Collection;

/**
 * <p>
 *     Provides service for the post
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public interface PostService {

    /**
     * <p>
     *     Checks if the post details are created, and adds the post.
     * </p>
     *
     * @param post Represents post to be added
     * @return true if the post is successfully added, false otherwise
     */
    boolean create(final Post post);


    /**
     * <p>
     *     Retrieves the collection of post details.
     * </p>
     *
     * @return the collection of posts
     */
    Collection<Post> get();

    /**
     * <p>
     *     Retrieves the post details using the post id.
     * </p>
     *
     * @param id Represents the id of the post to retrieve
     * @return {@link Post}
     */
    Post getUsingId(final Long id);

    /**
     * <p>
     *     Checks if the post details are updated.
     * </p>
     *
     * @param post Represents the post to be updated
     * @return true if the post is successfully updated, false otherwise
     */
    boolean update(final Post post);

}

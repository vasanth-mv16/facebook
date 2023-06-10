package com.facebook.service;

import com.facebook.model.Post;
import com.facebook.model.User;

import java.util.Collection;

/**
 * Provides service for the post
 *
 * @version 1.0
 * @author vasanth
 */
public interface PostService {

    /**
     * Checks if the post details are created, and adds the post.
     *
     * @param post the post to be added
     * @return true if the post is successfully added, false otherwise
     */
    boolean addPost(final Post post);


    /**
     * Retrieves the collection of post details.
     *
     * @return the collection of posts
     */
    Collection<Post> getPost();

    /**
     * Retrieves the post details using the post id.
     *
     * @param id the id of the post to retrieve
     * @return the post with the specified id
     */
    Post getPostUsingId(final Long id);

    /**
     * Checks if the post details are updated, and updates the post.
     *
     * @param post the post to be updated
     * @return true if the post is successfully updated, false otherwise
     */
    boolean updatePostDetail(final Post post);

}

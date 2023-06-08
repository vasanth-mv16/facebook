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
     * Checks the post details are created
     *
     * @param post Represents the user
     * @return true, if post to get otherwise false
     */
    boolean addPost(final Post post);


    /**
     * Gets the post details
     *
     * @return collection of post
     */
    Collection<Post> getPost();

    /**
     * Gets the post details using post id
     *
     * @param id the id to get the post
     * @return post of the user
     */
    Post getPostUsingId(final Long id);

    /**
     * Checks the post to be updated
     *
     * @param post to update a post
     * @return true, if post to be updated otherwise false
     */
    boolean updatePostDetail(final Post post);

}

package com.facebook.service.Impl;

import com.facebook.model.Post;
import com.facebook.service.PostService;

import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * <p>
 * Implementation of the PostService interface for managing posts.
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostImpl implements PostService {

    private static final List<Post> POSTS = new ArrayList<>();
    private static PostImpl postImpl;

    /**
     * <p>
     * Default constructor for post service implementation
     * </p>
     */
    private PostImpl() {
    }

    /**
     * <p>
     * Gets the instance of post service implementation
     * </p>
     *
     * @return Returns the singleton instance of the post service implementation class.
     */
    public static PostImpl getInstance() {
        if (null == postImpl) {
            postImpl = new PostImpl();
        }
        return postImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param post {@link Post}Represents post to be added
     * @return True if the post is successfully added, false otherwise
     */
    public boolean create(Post post) {
        return POSTS.add(post);
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents the id of the post to retrieve
     * @return {@link Post}
     */
    public Post getById(final Long id) {
        final ListIterator<Post> iterator = POSTS.listIterator();

        while (iterator.hasNext()) {
            final Post existingPost = iterator.next();

            if (existingPost.getId().equals(id)) {
                return existingPost;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of posts
     */
    public Collection<Post> get() {
        return POSTS;
    }

    /**
     * {@inheritDoc}
     *
     * @param post {@link Post}Represents the post to be updated
     * @return True if the post is successfully updated, false otherwise
     */
    public boolean update(final Post post) {
        final Iterator<Post> iterator = POSTS.iterator();

        while (iterator.hasNext()) {
            final Post existingPost = iterator.next();

            if (existingPost.getId().equals(post.getId())) {
                existingPost.setCaption(post.getCaption());
                existingPost.setLocation(post.getLocation());

                return true;
            }
        }

        return false;
    }
}

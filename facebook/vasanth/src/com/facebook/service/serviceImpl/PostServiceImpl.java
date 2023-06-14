package com.facebook.service.serviceImpl;

import com.facebook.model.Post;
import com.facebook.service.PostService;

import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Implementation of the PostService interface for managing posts.
 *
 * @version 1.0
 * @author vasanth
 */
public class PostServiceImpl implements PostService {

    private static final List<Post> POSTS = new ArrayList<>();
    private static PostServiceImpl POST_SERVICE_IMPL;

    private PostServiceImpl() {}

    public static PostServiceImpl getPostServiceImpl() {
        return (null == POST_SERVICE_IMPL) ? POST_SERVICE_IMPL = new PostServiceImpl() : POST_SERVICE_IMPL;
    }

    /**
     * {@inheritDoc}
     *
     * @param post Represents post to be added
     * @return true if the post is successfully added, false otherwise
     */
    public boolean create(Post post) {
         return POSTS.add(post);
    }

    /**
     * {@inheritDoc}
     *
     * @return the collection of posts
     */
    public Collection<Post> get() {
        return POSTS;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents the id of the post to retrieve
     * @return {@link Post}
     */
    public Post getUsingId(final Long id) {
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
     * @param post Represents the post to be updated
     * @return true if the post is successfully updated, false otherwise
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

package com.facebook.service.serviceImpl;

import com.facebook.model.Post;
import com.facebook.service.PostService;

import java.util.*;

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
        if (POST_SERVICE_IMPL == null) {
            POST_SERVICE_IMPL = new PostServiceImpl();
        }
        return POST_SERVICE_IMPL;
    }

    /**
     * {@inheritDoc}
     */
    public boolean create(Post post) {
         return POSTS.add(post);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Post> get() {
        return POSTS;
    }

    /**
     * {@inheritDoc}
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

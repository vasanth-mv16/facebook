package com.facebook.service.serviceImpl;

import com.facebook.model.Post;
import com.facebook.service.PostService;

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

    private static final Collection<Post> POSTS = new ArrayList<>();
    private static PostServiceImpl POST_SERVICE_IMPL;

    public PostServiceImpl() {}

    public static PostServiceImpl getPostServiceImpl() {
        if (POST_SERVICE_IMPL == null) {
            POST_SERVICE_IMPL = new PostServiceImpl();
        }
        return POST_SERVICE_IMPL;
    }

    /**
     * {@inheritDoc}
     */
    public boolean addPost(Post post) {
         return POSTS.add(post);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Post> getPost() {
        return POSTS;
    }

    /**
     * {@inheritDoc}
     */
    public Post getPostUsingId(final Long id) {
        final Iterator<Post> iterator = POSTS.iterator();

        while (iterator.hasNext()) {
            final Post existingPost = iterator.next();

            if (existingPost.getId() == id) {
                return existingPost;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updatePostDetail(final Post post) {
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

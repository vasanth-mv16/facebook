package com.facebook.service.serviceImpl;

import com.facebook.model.Post;
import com.facebook.model.User;
import com.facebook.service.PostService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Implements the following services for the post
 *
 * @version 1.0
 * @author vasanth
 */
public class PostServiceImpl implements PostService {

    private static final Collection<Post> POSTS = new ArrayList<>();
    private static final Collection<User> USER_LIST = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    public boolean addPost(final Post post) {
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

        for (final Post post : POSTS) {

            if (post.getId() == id) {
                return post;
            }
        }
//        final Iterator<Post> iterator = POST_LIST.iterator();
//
//        while (iterator.hasNext()) {
//            final Post existingPost = iterator.next();
//
//            if (existingPost.getId() == id) {
//                return existingPost;
//            }
//        }

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

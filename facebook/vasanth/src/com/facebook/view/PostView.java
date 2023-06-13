package com.facebook.view;

import com.facebook.controller.PostController;
import com.facebook.model.Post;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Scanner;

/**
 * <p>
 *     Manages the view for posts, including creating, retrieving, updating, and printing post details.
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class PostView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserView USER_VIEW = UserView.getUserView();
    private static final LikeView LIKE_VIEW = LikeView.getLikeView();
    private static final PostController POST_CONTROLLER = PostController.getPostController();
    private static final UserValidation USER_VALIDATION = UserValidation.getUserValidation();
    private static  PostView postView;
    private static Long postId = 1L;

    private PostView() {}

    public static PostView getPostView() {
        if (postView == null) {
            postView= new PostView();
        }
        return postView;
    }
    /**
     * <p>
     *     Shows the menu details for the user to post and edit
     * </p>
     *
     * @param user represents the post
     *
     */
    public void displayPostDetails(final User user) {
        System.out.println(String.join("\n","CLICK 1 TO CREATE","CLICK 2 TO GET","CLICK 3 TO GET USING ID",
                "CLICK 4 TO UPDATE","CLICK 5 TO DISPLAY LIKE DETAILS","CLICK 6 TO DISPLAY USER OPTIONS"));

        switch (USER_VIEW.getChoice()) {
            case 1 :
                createPost();
                break;
            case 2 :
                getPostDetails();
                break;
            case 3 :
                getPostDetailUsingId();
                break;
            case 4:
                updatePost();
                break;
            case 5:
                LIKE_VIEW.displayLikeDetails(user);
                break;
            case 6:
                USER_VIEW.displaysUserOptions(user);
                break;
            default :
                System.out.println("INVALID CHOICE,SELECT THE ABOVE CHOICE");
                displayPostDetails(user);
        }
        displayPostDetails(user);
    }

    /**
     * <p>
     * Creates a new post with the user, caption, and location.
     * </p>
     */
    private void createPost() {
        final Post post = new Post();
        final Timestamp timestamp = Timestamp.from(Instant.now());

        System.out.println("ENTER YOUR USER ID:");
        post.setUserId(Long.valueOf(SCANNER.nextLine()));
        post.setId(postId++);
        System.out.println("ENTER YOUR CAPTION FOR YOUR POST:");
        post.setCaption(SCANNER.nextLine().trim());
        System.out.println("ENTER YOUR LOCATION FOR YOUR POST:");
        post.setLocation(SCANNER.nextLine().trim());
        post.setDateTime(timestamp);
        System.out.println((POST_CONTROLLER.Create(post)) ? "SUCCESSFULLY POSTED" : "FAILED TO POST");
    }

    /**
     * <p>
     *     Retrieves and prints the details of the post
     * </p>
     */
    public Collection<Post> getPostDetails() {
        System.out.println(POST_CONTROLLER.get());
        return (POST_CONTROLLER.get());
    }

    /**
     * <p>
     *     Retrieves and returns a User object based on the provided user ID
     * </p>
     *
     * @return {@link Post} the post updated by the user
     */
    public Post getPostDetailUsingId() {
        System.out.println("ENTER YOUR POST ID:");
        final Post post = POST_CONTROLLER.getUsingId(Long.valueOf(SCANNER.nextLine()));
        System.out.println(post);

        return post;
    }
    /**
     * <p>
     *     Updates the post by the user to edit the caption and location
     * </p>
     */
    public void updatePost() {
        final Post post = new Post();
        final Post get = getPostDetailUsingId();

        post.setId(get.getId());
        System.out.println("DO YOU WANT TO EDIT CAPTION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine())) {
            System.out.println("ENTER YOUR CAPTION FOR YOUR POST:");
            post.setCaption(SCANNER.nextLine().trim());
        } else {
            post.setCaption(get.getCaption());
        }
        System.out.println("DO YOU WANT TO EDIT LOCATION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine())) {
            System.out.println("ENTER YOUR LOCATION FOR YOUR POST:");
            post.setLocation(SCANNER.nextLine().trim());
        } else {
            post.setLocation(get.getLocation());
        }

        if (POST_CONTROLLER.isPostUpdate(post)) {
            System.out.println("POST UPDATED");
        } else {
            System.out.println("NOT UPDATED");
            updatePost();
        }
    }
}

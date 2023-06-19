package com.facebook.view;

import com.facebook.controller.PostController;
import com.facebook.model.Post;
import com.facebook.view.validation.PostValidation;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Scanner;

/**
 * <p>
 * Manages the view for posts, including creating, retrieving, updating, and printing post details.
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final LikeView LIKE_VIEW = LikeView.getInstance();
    private static final PostController POST_CONTROLLER = PostController.getInstance();
    private static final PostValidation POST_VALIDATION = PostValidation.getInstance();
    private static PostView postView;
    private static Long postId = 1L;

    /**
     * <p>
     * Default constructor for post view
     * </p>
     */
    private PostView() {
    }

    /**
     * <p>
     * Gets the instance of the post view
     * </p>
     *
     * @return Returns the singleton instance of the post view class.
     */
    public static PostView getInstance() {
        if (null == postView) {
            postView = new PostView();
        }

        return postView;
    }

    /**
     * <p>
     * Shows the menu details for the user to post and edit
     * </p>
     *
     * @param userId Refer the user id for the post
     */
    public void displayPostDetails(final Long userId) {
        System.out.println(String.join("\n", "CLICK 1 TO CREATE", "CLICK 2 TO GET", "CLICK 3 TO GET USING ID",
                "CLICK 4 TO UPDATE", "CLICK 5 TO DISPLAY LIKE DETAILS", "CLICK 6 TO DISPLAY USER OPTIONS"));

        switch (getChoice()) {
            case 1:
                create(userId);
                break;
            case 2:
                getAll();
                break;
            case 3:
                get();
                break;
            case 4:
                update();
                break;
            case 5:
                LIKE_VIEW.displayLikeDetails(userId);
                break;
            case 6:
                USER_VIEW.displaysUserOptions(userId);
                break;
            default:
                System.out.println("INVALID CHOICE,SELECT THE ABOVE CHOICE");
                displayPostDetails(userId);
        }
        displayPostDetails(userId);
    }

    /**
     * <p>
     * Creates a new post with the user, caption, and location.
     * </p>
     *
     * @param userId Refer the user id for the post
     */
    private void create(final Long userId) {
        final Post post = new Post();
        final Timestamp postUploadTime = Timestamp.from(Instant.now());

        post.setUserId(userId);
        post.setId(postId++);
        post.setCaption(getCaption());
        post.setLocation(getLocation());
        post.setUploadTime(postUploadTime);

        if (POST_CONTROLLER.create(post)) {
            System.out.println("SUCCESSFULLY POSTED");
            System.out.println(post.getId());
        } else {
            System.out.println("FAILED TO POST");
            create(userId);
        }
    }

    /**
     * <p>
     * Collects the post caption
     * </p>
     *
     * @return The caption of the post
     */
    private String getCaption() {
        System.out.println("ENTER YOUR CAPTION FOR YOUR POST:");

        return SCANNER.nextLine().trim();
    }

    /**
     * <p>
     * Collects the post location
     * </p>
     *
     * @return Tha location of the post
     */
    private String getLocation() {
        System.out.println("ENTER YOUR LOCATION FOR YOUR POST:");

        return SCANNER.nextLine().trim();
    }

    /**
     * <p>
     * Retrieves and prints the details of the post
     * </p>
     *
     * @return Collection of post
     */
    private Collection<Post> getAll() {
        System.out.println(POST_CONTROLLER.getALl());

        return POST_CONTROLLER.getALl();
    }

    /**
     * <p>
     * Retrieves and returns a User object based on the provided user ID
     * </p>
     *
     * @return Returns {@link Post} of the user
     */
    private Post get() {
        final Post post = POST_CONTROLLER.get(getPostId());
        System.out.println(post);

        if (null == post) {
            System.out.println("ENTER AN VALID POST ID");

            return get();
        }

        return post;
    }

    /**
     * <p>
     * Updates the post by the user to edit the caption and location
     * </p>
     */
    private void update() {
        final Post post = new Post();
        final Post get = get();

        post.setId(get.getId());
        System.out.println("DO YOU WANT TO EDIT CAPTION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        post.setCaption((POST_VALIDATION.validateAccess(SCANNER.nextLine())) ? getCaption() : get.getCaption());
        System.out.println("DO YOU WANT TO EDIT LOCATION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        post.setLocation((POST_VALIDATION.validateAccess(SCANNER.nextLine())) ? getLocation() : get.getLocation());

        if (POST_CONTROLLER.update(post)) {
            System.out.println("POST UPDATED");
        } else {
            System.out.println("NOT UPDATED");
            update();
        }
    }

    /**
     * <p>
     * Collects and validates the user's choice as an integer value
     * </p>
     *
     * @return The validated choice
     */
    private int getChoice() {
        try {
            System.out.println("ENTER YOUR CHOICE :");
            final int choice = Integer.parseInt(SCANNER.nextLine());

            if (POST_VALIDATION.validateChoice(String.valueOf(choice))) {
                return choice;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getChoice();
    }

    /**
     * <p>
     * Gets the post id detail
     * </p>
     *
     * @return Returns the post id of the user
     */
    private Long getPostId() {
        try {
            System.out.println("ENTER THE POST ID:");
            final Long postId = Long.valueOf(SCANNER.nextLine());

            if (POST_VALIDATION.validatePostId(String.valueOf(postId))) {
                return postId;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getPostId();
    }
}

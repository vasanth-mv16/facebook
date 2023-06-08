package com.facebook.view;

import com.facebook.controller.PostController;
import com.facebook.model.Post;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * Represents the posts, posted by the user
 *
 * @version 1.0
 * @author vasanth
 */
public class PostView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserView USER_VIEW = new UserView();
    private static final LikeView LIKE_VIEW = new LikeView();
    private static final PostController POST_CONTROLLER = new PostController();
    private static final UserValidation USER_VALIDATION = new UserValidation();
    private static Long postId = 0L;
    private static final List<Post> userPost = new ArrayList<>();

    /**
     * Shows the menu details for the user to post and edit
     *
     * @param user Represents the post
     */
    public void printPostDetails(final User user) {
        System.out.println("CLICK 1 TO CREATE, 2 TO GET, 3 TO GET USING ID, 4 TO UPDATE, 5 TO PRINT LIKE DETAILS, " +
                "6 TO PRINT USER OPTIONS");

        switch (USER_VIEW.getChoice()) {
            case 1 :
                createPost(user);
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
                LIKE_VIEW.printLikeDetails(user);
            case 6:
//                USER_VIEW.printUserOptions();
                break;
            default :
                System.out.println("INVALID CHOICE,SELECT THE ABOVE CHOICE");
                printPostDetails(user);
        }
        printPostDetails(user);
    }

    /**
     * Shows the user post details
     *
     * @param user Represents by the user
     */
    private void createPost(final User user) {
        try {
            final Post post = new Post();

            post.setId(++postId);
            System.out.println("ENTER YOUR CAPTION FOR YOUR POST:");
            post.setCaption(SCANNER.nextLine().trim());
            System.out.println("ENTER YOUR LOCATION FOR YOUR POST:");
            post.setLocation(SCANNER.nextLine().trim());
            post.setDateTime(LocalDateTime.now());
            post.setUser(user);

            if (POST_CONTROLLER.isPostCreate(post)) {
                System.out.println("SUCCESSFULLY POSTED");
            } else {
                System.out.println("FAILED TO POST");
            }
        } catch (Exception exception) {
            System.out.println("ID NOT FOUND,ENTER CORRECT ID");
            createPost(user);
        }
    }

    /**
     * Gets the post details
     */
    public void getPostDetails() {
        System.out.println(POST_CONTROLLER.getPostDetails());
    }

    /**
     * Gets the post details using post id
     *
     * @return {@link Post} post
     */
    public Post getPostDetailUsingId() {
        System.out.println("ENTER YOUR POST ID:");
        System.out.println(POST_CONTROLLER.getPostDetailUsingId(SCANNER.nextLong()));

        return null;
    }
    /**
     * Sets the update for the post
     */
    public void updatePost() {
        final Post post = new Post();
        final Post get = getPostDetailUsingId();

        post.setId(getPostId());
        System.out.println("DO YOU WANT TO EDIT CAPTION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            post.setCaption(get.getCaption());
        } else {
            System.out.println("ENTER YOUR CAPTION FOR YOUR POST:");
            post.setCaption(SCANNER.nextLine().trim());
        }
        System.out.println("DO YOU WANT TO EDIT LOCATION, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            post.setLocation(get.getLocation());
        } else {
            System.out.println("ENTER YOUR LOCATION FOR YOUR POST:");
            post.setLocation(SCANNER.nextLine().trim());
        }

        if (POST_CONTROLLER.isPostUpdate(post)) {
            System.out.println("POST UPDATED");
        } else {
            System.out.println("NOT UPDATED");
            updatePost();
        }
    }

    /**
     * Gets the post id detail
     *
     * @return {@link Long} postId
     */
    public Long getPostId() {
        try {
            System.out.println("ENTER THE POST ID:");
            final Long postId = Long.valueOf(SCANNER.nextLine());

            if (USER_VALIDATION.isValidatePostId(String.valueOf(postId))) {
                return postId;
            }
        } catch (final InputMismatchException | NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN NUMBER");
        }

        return getPostId();
    }

}

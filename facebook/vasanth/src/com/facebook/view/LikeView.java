package com.facebook.view;

import com.facebook.controller.LikeController;
import com.facebook.model.Like;
import com.facebook.view.validation.UserValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 * Represents the like for the post by user
 * </p>
 */
public class LikeView {

    private static LikeView likeView;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LikeController LIKE_CONTROLLER = LikeController.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final PostView POST_VIEW = PostView.getInstance();
    private static final UserValidation USER_VALIDATION = UserValidation.getInstance();
    private static Long id = 1L;

    /**
     * <p>
     * Default constructor for like view
     * </p>
     */
    private LikeView() {
    }

    /**
     * <p>
     * Gets the instance of the like view
     * </p>
     *
     * @return Returns the singleton instance of the like view class.
     */
    public static LikeView getInstance() {
        if (null == likeView) {
            likeView = new LikeView();
        }

        return likeView;
    }

    public void displayLikeDetails(final Long id) {
        System.out.println(String.join("\n", "CLICK 1 TO CREATE LIKE", "CLICK 2 TO GET ALL LIKES",
                "CLICK 3 TO GET LIKE COUNT", "CLICK 4 TO DISPLAY POST DETAILS"));

        switch (USER_VIEW.getChoice()) {
            case 1:
                create();
                break;
            case 2:
                getDetail();
                break;
            case 3:
                getLikeCountDetail();
                break;
            case 4:
                POST_VIEW.displayPostDetails(id);
                break;
            case 5:
                USER_VIEW.displaysUserOptions(id);
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                displayLikeDetails(id);
        }
        displayLikeDetails(id);
    }

    /**
     * <p>
     * Shows the like details
     * </p>
     */
    private void create() {
        final Like like = new Like();

        like.setId(id++);
        like.setUserId(getUserId());
        like.setPostId(getPostId());
        System.out.println(LIKE_CONTROLLER.isCreate(like) ? "LIKED" : "NOT LIKED");
    }

    /**
     * <p>
     * Gets the likes by the user
     * </p>
     */
    private void getDetail() {
        System.out.println(LIKE_CONTROLLER.get());
    }

    /**
     * <p>
     * Gets the likes count for the post
     * </p>
     */
    private void getLikeCountDetail() {
        try {
            System.out.println("ENTER THE POST ID:");
            Long postId = Long.parseLong(SCANNER.nextLine());
            Long likeCount = LIKE_CONTROLLER.getCount(postId);

            System.out.println("TOTAL LIKE FOR POST ID " + postId + ": " + likeCount);
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
            getLikeCountDetail();
        }
    }

    /**
     * <p>
     * Gets the user id detail
     * </p>
     *
     * @return Returns the user id of the user
     */
    private Long getUserId() {
        try {
            System.out.println("ENTER THE USER ID:");
            final Long userId = Long.valueOf(SCANNER.nextLine());

            if (USER_VALIDATION.validateUserId(String.valueOf(userId))) {
                return userId;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getUserId();
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

            if (USER_VALIDATION.validatePostId(String.valueOf(postId))) {
                return postId;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getPostId();
    }
}

package com.facebook.view;

import com.facebook.controller.LikeController;
import com.facebook.model.Like;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the like by the user
 */
public class LikeView {

    private static LikeView LIKE_VIEW;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LikeController LIKE_CONTROLLER = LikeController.getLikeController();
    private static final UserView USER_VIEW = UserView.getUserView();
    private static final PostView POST_VIEW = PostView.getPostView();
    private static final UserValidation USER_VALIDATION = UserValidation.getUserValidation();

    public LikeView() {}

    public static LikeView getLikeView() {

        if (LIKE_VIEW == null) {
            LIKE_VIEW = new LikeView();
        }
        return LIKE_VIEW;
    }
    public void displayLikeDetails(final User user) {
        System.out.println("ENTER 1 TO CREATE LIKE, 2 TO GET ALL LIKES, 3 TO GET LIKE COUNT, 4 TO DISPLAY POST DETAILS");

        switch (USER_VIEW.getChoice()) {
            case 1:
                createLike();
                break;
            case 2:
                getAllLike();
                break;
            case 3:
                getLikeCount();
                break;
            case 4:
                POST_VIEW.displayPostDetails(user);
                break;
            case 5:
                USER_VIEW.displaysUserOptions(user);
                break;
            default :
                System.out.println("Invalid choice, select the above choice");
                displayLikeDetails(user);
        }
        displayLikeDetails(user);
    }

    /**
     * Shows the like details
     */
    public void createLike() {
        final Like like = new Like();

        like.setUserId(getUserId());
        like.setPostId(getPostId());
        System.out.println(LIKE_CONTROLLER.createLike(like) ? "LIKED" : "NOT LIKED");
    }

    /**
     * Gets the likes by the user
     */
    public void getAllLike() {
        System.out.println(LIKE_CONTROLLER.getAllLike());
    }

    /**
     * Gets the likes count for the user
     */
    public void getLikeCount() {
        try {
            System.out.println("ENTER THE USER ID:");
            Long userId = Long.parseLong(SCANNER.nextLine());
            Long likeCount = LIKE_CONTROLLER.getLikeCount(userId);

            System.out.println("TOTAL LIKE FOR USER ID " + userId + ": " + likeCount);

        } catch (Exception exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
            getLikeCount();
        }
    }

    /**
     * Gets the post id detail
     *
     * @return {@link Long} postId
     */
    public Long getUserId() {
        try {
            System.out.println("ENTER THE USER ID:");
            final Long userId = Long.valueOf(SCANNER.nextLine());

            if (USER_VALIDATION.isValidateUserId(String.valueOf(userId))) {
                return userId;
            }
        } catch (final InputMismatchException | NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN NUMBER");
        }

        return getUserId();
    }

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

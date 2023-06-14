package com.facebook.view;

import com.facebook.controller.LikeController;
import com.facebook.model.Like;
import com.facebook.view.validation.UserValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>
 *    Represents the like for the post by user
 * </p>
 */
public class LikeView {

    private static LikeView LIKE_VIEW;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LikeController LIKE_CONTROLLER = LikeController.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final PostView POST_VIEW = PostView.getInstance();
    private static final UserValidation USER_VALIDATION = UserValidation.getInstance();

    private LikeView() {}

    public static LikeView getInstance() {
        return (null == LIKE_VIEW) ? LIKE_VIEW = new LikeView() : LIKE_VIEW;
    }

    public void displayLikeDetails(final Long id) {
        System.out.println(String.join("\n","CLICK 1 TO CREATE LIKE", "CLICK 2 TO GET ALL LIKES",
                "CLICK 3 TO GET LIKE COUNT","CLICK 4 TO DISPLAY POST DETAILS"));

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
                POST_VIEW.displayPostDetails(id);
                break;
            case 5:
                USER_VIEW.displaysUserOptions(id);
                break;
            default :
                System.out.println("Invalid choice, select the above choice");
                displayLikeDetails(id);
        }
        displayLikeDetails(id);
    }

    /**
     * <p>
     *     Shows the like details
     * </p>
     */
    public void createLike() {
        final Like like = new Like();

        like.setUserId(getUserId());
        like.setPostId(getPostId());
        System.out.println(LIKE_CONTROLLER.createLike(like) ? "LIKED" : "NOT LIKED");
    }

    /**
     * <p>
     *     Gets the likes by the user
     * </p>
     */
    public void getAllLike() {
        System.out.println(LIKE_CONTROLLER.getAllLike());
    }

    /**
     * <p>
     *     Gets the likes count for the post
     * </p>
     */
    public void getLikeCount() {
        try {
            System.out.println("ENTER THE POST ID:");
            Long postId = Long.parseLong(SCANNER.nextLine());
            Long likeCount = LIKE_CONTROLLER.getLikeCount(postId);

            System.out.println("TOTAL LIKE FOR POST ID " + postId + ": " + likeCount);
        } catch (final Exception exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
            getLikeCount();
        }
    }

    /**
     * <p>
     *     Gets the user id detail
     * </p>
     *
     * @return Returns the user id of the user
     */
    public Long getUserId() {
        try {
            System.out.println("ENTER THE USER ID:");
            final Long userId = Long.valueOf(SCANNER.nextLine());

            if (USER_VALIDATION.validateUserId(String.valueOf(userId))) {
                return userId;
            }
        } catch (final InputMismatchException | NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN NUMBER");
        }

        return getUserId();
    }

    /**
     * <p>
     *     Gets the post id detail
     * </p>
     *
     * @return Returns the post id of the user
     */
    public Long getPostId() {
        try {
            System.out.println("ENTER THE POST ID:");
            final Long postId = Long.valueOf(SCANNER.nextLine());

            if (USER_VALIDATION.validatePostId(String.valueOf(postId))) {
                return postId;
            }
        } catch (final InputMismatchException | NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN NUMBER");
        }

        return getPostId();
    }
}

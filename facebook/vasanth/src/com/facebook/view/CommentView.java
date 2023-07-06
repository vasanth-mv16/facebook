package com.facebook.view;

import com.facebook.controller.CommentController;
import com.facebook.controller.LikeController;
import com.facebook.model.Comment;
import com.facebook.view.validation.CommentValidation;
import com.facebook.view.validation.PostValidation;

import java.util.Scanner;

public class CommentView {

    private static CommentView commentView;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CommentController  COMMENT_CONTROLLER = CommentController.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static final PostView POST_VIEW = PostView.getInstance();
    private static final PostValidation POST_VALIDATION = PostValidation.getInstance();
    private static  final CommentValidation COMMENT_VALIDATION = CommentValidation.getInstance();
    private static Long id = 1L;

    private CommentView() {}

    public static CommentView getInstance() {
        if (null == commentView) {
            commentView = new CommentView();
        }

        return commentView;
    }

    public void displayCommentDetails(final Long userId) {
        System.out.println();

        switch(USER_VIEW.getChoice()) {
            case 1:
                create(userId);
                break;
            default :
                System.out.println();
                displayCommentDetails(userId);
        }

    }

    private void create(final Long userId) {
        final Comment comment = new Comment();

        comment.setId(id++);
        comment.setUserId(userId);
        comment.setPostId(getPostId());
        comment.setMessage(comment.getMessage());
        System.out.println(COMMENT_CONTROLLER.create(comment) ? "COMMENTED" : "NOT COMMENTED");
    }

    private void delete() {
        System.out.println(COMMENT_CONTROLLER.delete(getCommentId()) ? "COMMENT DELETED" : "NOT DELETED");
    }

    private Long getCommentId() {
        try {
            System.out.println("ENTER THE COMMENT ID:");
            final Long commentId = Long.valueOf(SCANNER.nextLine());

            if (COMMENT_VALIDATION.validateCommentId(String.valueOf(commentId))) {
                return commentId;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }
        return null;
    }

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

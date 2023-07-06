package com.facebook.view.validation;

public class CommentValidation {

    private static CommentValidation commentValidation;

    private CommentValidation() {}

    public static CommentValidation getInstance() {
        if(null == commentValidation) {
            commentValidation = new CommentValidation();
        }
        return commentValidation;
    }

    public boolean validateCommentId(final String commentId) {
        return commentId.matches("[\\d]");
    }
}

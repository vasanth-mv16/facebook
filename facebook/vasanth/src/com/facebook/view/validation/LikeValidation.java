package com.facebook.view.validation;

public class LikeValidation {

    private static LikeValidation likeValidation;

    /**
     * <p>
     * Default constructor for like validation
     * </p>
     */
    private LikeValidation() {}

    /**
     * <p>
     * Gets the instance of the like validation
     * </p>
     *
     * @return Returns the singleton instance of the like validation class.
     */
    public static LikeValidation getInstance() {
        if(null == likeValidation) {
            likeValidation = new LikeValidation();
        }
        return likeValidation;
    }

    /**
     * <p>
     * Validates a like id string using a regular expression pattern
     * </p>
     *
     * @param likeId The like id to be validated
     * @return boolean - True if the user id is valid, false otherwise.
     */
    public boolean validateLikeId(final String likeId) {
        return likeId.matches("[\\d]");
    }
}

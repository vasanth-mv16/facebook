package com.facebook.view.validation;

public class PostValidation {

    private static PostValidation postValidation;

    /**
     * <p>
     * Default constructor for user validation
     * </p>
     */
    private PostValidation() {

    }

    /**
     * <p>
     * Gets the instance of the user validation
     * </p>
     *
     * @return Returns the singleton instance of the user validation class.
     */
    public static PostValidation getInstance() {

        if (null == postValidation) {
            postValidation = new PostValidation();
        }

        return postValidation;
    }

    /**
     * <p>
     * Validates a choice string using a regular expression pattern.
     * </p>
     *
     * @param choice The choice has to validated
     * @return boolean - True if the choice is valid, false otherwise.
     */
    public boolean validateChoice(final String choice) {
        return choice.matches("\\d{1,2}");
    }

    /**
     * <p>
     * Validates a check string using a regular expression pattern.
     * </p>
     *
     * @param access The access to be validated
     * @return boolean - True if the check is valid, false otherwise.
     */
    public boolean validateAccess(final String access) {
        return (access.equalsIgnoreCase("yes") || access.equalsIgnoreCase("y"));
    }

    /**
     * <p>
     * Validates a post id string using a regular expression pattern
     * </p>
     *
     * @param postId The post id to be validated
     * @return boolean - True if the user id is valid, false otherwise.
     */
    public boolean validatePostId(final String postId) {
        return postId.matches("[\\d]");
    }

}

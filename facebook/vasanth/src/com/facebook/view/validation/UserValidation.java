package com.facebook.view.validation;


/**
 * Given class used for validation the user details
 *
 * @version 1.0
 * @author vasanth
 */
public class UserValidation {

    private static UserValidation userValidation;

    UserValidation() {}

    public static UserValidation getUserValidation() {

        if (userValidation == null) {
            userValidation = new UserValidation();
        }
        return userValidation;
    }
    /**
     * Validates a name string using a regular expression pattern.
     *
     * @param name - The name string to be validated.
     * @return boolean - True if the name is valid, false otherwise.
     */
    public boolean isValidateName(final String name) {
        return name.matches("^[a-zA-Z_]+\\.?");
    }

    /**
     * Validates a mobile number string using a regular expression pattern.
     *
     * @param mobileNumber - The mobile Number string to be validated.
     * @return boolean - True if the mobileNumber is valid, false otherwise.
     */
    public boolean isValidateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("(^\\+(91){1,2}[6-9][0-9]{9}$)");
    }

    /**
     * Validates a email string using a regular expression pattern.
     *
     * @param email The email string to be validated.
     * @return boolean - True if the email is valid, false otherwise.
     */
    public boolean isValidateEmail(final String email) {
        return email.matches("^[a-z0-9._]+@[a-z]+\\.[a-z-]{2,3}");
    }

    /**
     * Validates a password string using a regular expression pattern.
     *
     * @param password The password string to be validated.
     * @return boolean - True if the password is valid, false otherwise.
     */
    public boolean isValidatePassword(final String password) {
        return password.matches("^(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    /**
     * Validates a date of birth string using a regular expression pattern.
     *
     * @param dateOfBirth The date of birth has to validated
     * @return boolean - True if the date of birth is valid, false otherwise.
     */
    public boolean isValidateDateOfBirth(final String dateOfBirth) {
        return dateOfBirth.matches("^(?:(?:(?:0?[1-9]|1\\d|2[0-8])-(?:0?[1-9]|1[0-2]))|(?:(?:29|30)-(?:0?[13-9]|1[0-2]))|(?:(?:0?1|0?[3-9]|1[0-9]|2[0-8])-02))-(?:(?!0000)\\d{4})$");
    }

    /**
     * Validates a choice string using a regular expression pattern.
     *
     * @param choice The choice has to validated
     * @return boolean - True if the choice is valid, false otherwise.
     */
    public boolean isValidateChoice(final String choice ) {
        return choice.matches("\\d{1,2}");
    }

    /**
     * Validates a check string using a regular expression pattern.
     *
     * @param accessForNo The access to be validated
     * @return boolean - True if the check is valid, false otherwise.
     */
    public boolean isValidateCheckForNo(final String accessForNo) {
        return accessForNo.equalsIgnoreCase("no") || accessForNo.equalsIgnoreCase("n");
    }

    public boolean isValidateCheckForYes(final String accessForYes) {
        return accessForYes.equalsIgnoreCase("yes") || accessForYes.equalsIgnoreCase("y");
    }
    /**
     * Validates a userId string using a regular expression pattern.
     *
     * @param userId The user id to be validated
     * @return boolean - True if the user id is valid, false otherwise.
     */
    public boolean isValidateUserId(final String userId) {
        return userId.matches("[\\d]");
    }

    /**
     * Validates a post id string using a regular expression pattern.
     *
     * @param postId The post id to be validated
     * @return boolean - True if the user id is valid, false otherwise.
     */
    public boolean isValidatePostId(final String postId) {
        return postId.matches("[\\d]");
    }
    
}

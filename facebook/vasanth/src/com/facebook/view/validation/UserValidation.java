package com.facebook.view.validation;

/**
 * Given class used for validation the user details
 *
 * @version 1.0
 * @author vasanth
 */
public class UserValidation {

    /**
     * Sets the validation for the name
     *
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateName(final String name) {
        return name.matches("^[a-zA-Z_]+\\.?");
    }

    /**
     * Sets the validation for the mobile number
     *
     * @param mobileNumber the mobile number has to validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("^[6-9][0-9]{9}$");
    }

    /**
     * Sets the validation for the email
     *
     * @param emailId the email id has to validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateEmail(final String emailId) {
        return emailId.matches("^[a-z0-9._]+@[a-z0-9]+\\.[a-z-]{2,3}");
    }

    /**
     * Sets the validation for the password
     *
     * @param password the email id has to validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidatePassword(final String password) {
        return password.matches("^(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    /**
     * Sets the validation for the date of birth
     *
     * @param dateOfBirth the date of birth has to validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateDateOfBirth(final String dateOfBirth) {
        return dateOfBirth.matches("^(?:(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[-/.](18|19|20)[0-9]{2})$");
    }

    /**
     * Given the user validate choice details
     *
     * @param choice the choice has to validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateChoice(final String choice ) {
        return choice.matches("\\d{1,2}");
    }

    /**
     * Sets the validation for the check
     *
     * @param access the access to be validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateCheck(final String access) {
        return access.equalsIgnoreCase("no") || access.equalsIgnoreCase("n");
    }

    /**
     * Sets the validation for the user id
     *
     * @param userId the user id to be validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidateUserId(final String userId) {
        return userId.matches("[\\d]");
    }

    /**
     * Sets the validation for the post id
     *
     * @param postId the post id to be validate
     * @return true, if matches the regular expression otherwise false
     */
    public boolean isValidatePostId(final String postId) {
        return postId.matches("[\\d]");
    }

    public boolean validateYear(final String year ) {
        return year.matches("\\d {4}");
    }

    public boolean validateMonth(final String month) {
        return month.matches("(0[1-9]|1[012])");
    }

    public boolean validateDate(final String date) {
        return  date.matches("(0[1-9]|[12][0-9]|3[01])");
    }
}

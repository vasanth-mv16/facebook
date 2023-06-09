package com.facebook.view.validation;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Given class used for validation the user details
 *
 * @version 1.0
 * @author vasanth
 */
public class UserValidation {

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
        //return dateOfBirth.matches("^(?:(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[-/.](18|19|20)[0-9]{2})$");
        //return dateOfBirth.matches("^(?:(?:(?:0?[1-9]|1\\d|2[0-8])-(?:0?[1-9]|1[0-2]))|(?:(?:29|30)-(?:0?[13-9]|1[0-2]))|(?:(?:0?1|0?[3-9]|1[0-9]|2[0-8])-02))-(?:(?!0000)\\d{4})$");
        try {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            final LocalDate date = LocalDate.parse(dateOfBirth, dateFormatter);
            final int currentYear = LocalDate.now().getYear();

            if (date.getYear() < 1940 && date.getYear() > currentYear) {
                if (date.getDayOfMonth() > date.getMonth().maxLength() ||
                        (date.getMonth() == Month.FEBRUARY && date.getDayOfMonth() > 29 && !date.isLeapYear())) {
                    return false;
                }
            }

        } catch (final DateTimeParseException exception) {
            return false;
        }
        return true;
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
     * @param access The access to be validated
     * @return boolean - True if the check is valid, false otherwise.
     */
    public boolean isValidateCheck(final String access) {
        return access.equalsIgnoreCase("no") || access.equalsIgnoreCase("n");
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

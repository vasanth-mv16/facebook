package com.facebook.view.validation;

import com.facebook.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * <p>
 *     Given class used for validation the user details
 * </p>
 *
 * @version 1.0
 * @author vasanth
 */
public class UserValidation {

    private static UserValidation userValidation;

    private UserValidation() {}

    public static UserValidation getUserValidation() {
        return (null == userValidation) ? userValidation = new UserValidation() : userValidation;
    }
    /**
     * <p>
     *     Validates a name string using a regular expression pattern.
     * </p>
     *
     * @param name - The name string to be validated.
     * @return boolean - True if the name is valid, false otherwise.
     */
    public boolean validateName(final String name) {
        return name.matches("^[a-zA-Z_]+\\.?");
    }

    /**
     * <p>
     *     Validates a mobile number string using a regular expression pattern.
     * </p>
     *
     * @param mobileNumber - The mobile Number string to be validated.
     * @return boolean - True if the mobileNumber is valid, false otherwise.
     */
    public boolean validateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("(^\\+(91){1,2}[6-9][0-9]{9}$)");
    }

    /**
     * <p>
     *     Validates a email string using a regular expression pattern.
     * </p>
     *
     * @param email The email string to be validated.
     * @return boolean - True if the email is valid, false otherwise.
     */
    public boolean validateEmail(final String email) {
        return email.matches("^[a-z0-9._]+@[a-z]+\\.[a-z-]{2,3}");
    }

    /**
     * <p>
     *     Validates a password string using a regular expression pattern.
     * </p>
     *
     * @param password The password string to be validated.
     * @return boolean - True if the password is valid, false otherwise.
     */
    public boolean validatePassword(final String password) {
        return password.matches("^(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    /**
     * <p>
     *     Validates a date of birth string using a regular expression pattern.
     * </p>
     *
     * @param dateOfBirth The date of birth has to validated
     * @return boolean - True if the date of birth is valid, false otherwise.
     */
    public boolean validateDateOfBirth(final String dateOfBirth) {
        try {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
            final LocalDate date = LocalDate.parse(dateOfBirth, dateFormatter);

            final YearMonth currentDate = YearMonth.now();
            final YearMonth dateToCheck = YearMonth.of(date.getYear(), date.getMonth());

            if (dateToCheck.isBefore(YearMonth.of(1900, 1)) || dateToCheck.isAfter(currentDate)) {
                return false;
            }
            
            if (date.getDayOfMonth() > date.getMonth().maxLength() ||
                    (date.getMonth() == Month.FEBRUARY && date.getDayOfMonth() > 29 && !date.isLeapYear())) {
                return false;
            }

            return true;
        } catch (final DateTimeParseException exception) {
            return false;
        }
    }

    /**
     * <p>
     *     Validates a choice string using a regular expression pattern.
     * </p>
     *
     * @param choice The choice has to validated
     * @return boolean - True if the choice is valid, false otherwise.
     */
    public boolean validateChoice(final String choice ) {
        return choice.matches("\\d{1,2}");
    }

    /**
     * <p>
     *     Validates a check string using a regular expression pattern.
     * </p>
     *
     * @param accessForYes The access to be validated
     * @return boolean - True if the check is valid, false otherwise.
     */
    public boolean validateForYes(final String accessForYes) {
        return accessForYes.equalsIgnoreCase("yes") || accessForYes.equalsIgnoreCase("y");
    }
    /**
     * <p>
     *     Validates a userId string using a regular expression pattern.
     * </p>
     *
     * @param userId The user id to be validated
     * @return boolean - True if the user id is valid, false otherwise.
     */
    public boolean validateUserId(final String userId) {
        return userId.matches("[\\d]");
    }

    /**
     * <p>
     *     Validates a post id string using a regular expression pattern
     * </p>
     *
     * @param postId The post id to be validated
     * @return boolean - True if the user id is valid, false otherwise.
     */
    public boolean validatePostId(final String postId) {
        return postId.matches("[\\d]");
    }

    /**
     * <p>
     *     Validates the gender of the user
     * </p>
     *
     * @param gender Represents the gender of the user
     * @return {@link User.Gender}
     */
    public User.Gender validateGender(final String gender) {
        return User.Gender.valueOf(gender);
    }
}

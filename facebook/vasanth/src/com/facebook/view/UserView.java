package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.util.Scanner;

/**
 * <p>
 * Manages the view for Users, including creating, retrieving, updating, and printing post details.
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserController USER_CONTROLLER = UserController.getInstance();
    private static final UserValidation USER_VALIDATION = UserValidation.getInstance();
    private static final PostView POST_VIEW = PostView.getInstance();
    private static UserView userView;
    private static Long id = 1L;

    public static void main(String[] args) {
        final UserView userView = getInstance();

        userView.displayMenu();
    }

    /**
     * <p>
     * Default constructor for userView
     * </p>
     */
    private UserView() {
    }

    /**
     * <p>
     * Gets the instance of the user view
     * </p>
     *
     * @return Returns the singleton instance of the UserView class
     */
    public static UserView getInstance() {
        if (null == userView) {
            userView = new UserView();
        }

        return userView;
    }

    /**
     * <p>
     * Displays the menu details
     * </p>
     */
    private void displayMenu() {
        System.out.println("\tFACEBOOK\nCLICK 1 TO SIGN UP\nCLICK 2 TO SIGN IN");

        switch (getChoice()) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            default:
                System.out.println("INVALID CHOICE");
                displayMenu();
        }
    }

    /**
     * <p>
     * Handles the sign-up process for a new user, collecting and validating user information
     * </p>
     */
    private void signUp() {
        final User user = new User();

        user.setId(id++);
        user.setName(getName());
        user.setMobileNumber(getMobileNumber());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setGender(getGender());
        user.setDateOfBirth(getDateOfBirth());

        if (USER_CONTROLLER.create(user)) {
            System.out.println("ACCOUNT SUCCESSFULLY SIGN UP");
        } else {
            System.out.println("ACCOUNT ALREADY EXIST");
            displayMenu();
        }
        System.out.println("PRESS YES FOR EDIT USER DETAILS AND PRESS ANY KEY FOR MENU ");

        if (USER_VALIDATION.validateAccess(SCANNER.nextLine())) {
            System.out.println(user.getId());
            displaysUserOptions(user.getId());
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     * Deletes a user based on the provided userID
     * </p>
     *
     * @param id Refer user id to delete
     */
    private void delete(final Long id) {
        if (USER_CONTROLLER.delete(id)) {
            System.out.println("SUCCESSFULLY DELETED");
            displayMenu();
        } else {
            System.out.println("NOT DELETED");
        }
    }

    /**
     * <p>
     * Updates the user's account information based on the provided id
     * </p>
     *
     * @param id The user id to update
     */
    private void update(final Long id) {
        final User user = new User();
        final User existingUser = get(id);

        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setName(USER_VALIDATION.validateAccess(SCANNER.nextLine()) ? getName() : existingUser.getName());
        System.out.println("DO YOU WANT TO EDIT EMAIL, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setEmail(USER_VALIDATION.validateAccess(SCANNER.nextLine()) ? getEmail() : existingUser.getEmail());
        System.out.println("DO YOU WANT TO EDIT PASSWORD, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setPassword(USER_VALIDATION.validateAccess(SCANNER.nextLine()) ? getPassword() : existingUser.getPassword());
        System.out.println("DO YOU WANT TO EDIT MOBILE NUMBER, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS ANY KEY ");
        user.setMobileNumber(USER_VALIDATION.validateAccess(SCANNER.nextLine()) ? getMobileNumber() : existingUser.getMobileNumber());
        System.out.println((USER_CONTROLLER.update(user, id)) ? "DETAILS SUCCESSFULLY UPDATED" : "NOT UPDATED");
    }

    /**
     * <p>
     * Handles the sign-in process for a user, collecting and validating user information, providing options for
     * user edit details.
     * </p>
     */
    private void signIn() {
        final User user = new User();

        SignInChoice(user);
        user.setPassword(getPassword());
        System.out.println((USER_CONTROLLER.signIn(user)) ? "ACCOUNT SIGN IN" : "INCORRECT EMAIL OR MOBILE NUMBER AND PASSWORD");
        System.out.println(String.join("", "DO YOU WANT TO EDIT,GET,DELETE THE USER DETAILS,PRESS 'YES' ",
                "FOR PRINT OPTION AND PRESS ANY KEY FOR MAIN MENU"));

        if (USER_VALIDATION.validateAccess(SCANNER.nextLine())) {
            displaysUserOptions((USER_CONTROLLER.getUserId(user)));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     * Retrieves and returns a user object based on the provided user id
     * </p>
     *
     * @param id Refer the user id to retrieve
     * @return Returns {@link User} details
     */
    private User get(final Long id) {
        System.out.println(USER_CONTROLLER.get(id));

        return (USER_CONTROLLER.get(id));
    }

    /**
     * <p>
     * Displays the user options and performs the action based on the user's choice.
     * </p>
     *
     * @param id The user id to edit, delete, retrieves
     */
    public void displaysUserOptions(final Long id) {
        System.out.println(String.join("\n", "CLICK 1 TO UPDATE", "CLICK 2 TO DELETE",
                "CLICK 3 TO GET USERS", "CLICK 4 TO DISPLAY POST DETAILS", "CLICK 5 TO LOGOUT", "CLICK 6 TO EXIT"));

        switch (getChoice()) {
            case 1:
                update(id);
                break;
            case 2:
                delete(id);
                break;
            case 3:
                get(id);
                break;
            case 4:
                POST_VIEW.displayPostDetails(id);
                break;
            case 5:
                logout();
                break;
            case 6:
                System.out.println("EXITING");
                SCANNER.close();
                System.exit(0);
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                displaysUserOptions(id);
        }
        displaysUserOptions(id);
    }

    /**
     * <p>
     * Handles the user logout process, displaying a confirmation message, and log out the user if confirmed
     * </p>
     */
    private void logout() {
        System.out.println("DO YOU WANT TO LOGOUT?,CLICK YES AND OTHERWISE CLICK ANY KEY FOR MENU OPTIONS");

        if (USER_VALIDATION.validateAccess(SCANNER.nextLine())) {
            displayMenu();
        }
    }

    /**
     * <p>
     * Collects and validates the user's email address
     * </p>
     *
     * @return Returns validated email.
     */
    private String getEmail() {
        System.out.println("ENTER YOUR EMAIL ID : \nDO YOU WANT GO MENU, PRESS($)");
        final String email = SCANNER.nextLine().trim();

        if (email.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validateEmail(email)) {
            return email;
        } else {
            System.out.println(String.join("", "EMAIL MUST CONTAINS ONLY LETTERS 'A-Z', 'a-z', " +
                    "NUMBERS '0-9', DOT '.' AND '@', AFTER 'a-z' AND DOT '.' THEN 2 OR MORE 'COM','ORG', 'EDU', 'IN'"));

            return getEmail();
        }
    }

    /**
     * <p>
     * Collects and validates the user's mobile number
     * </p>
     *
     * @return Returns validated mobile number
     */
    private String getMobileNumber() {
        System.out.println("ENTER YOUR MOBILE NUMBER : \nDO YOU WANT GO MENU, PRESS($)");
        final String mobileNumber = SCANNER.nextLine().trim();

        if (mobileNumber.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validateMobileNumber(mobileNumber)) {
            return mobileNumber;
        } else {
            System.out.println(String.join("", "MOBILE NUMBER MUST CONTAINS '+91' FOLLOWED BY ",
                    "10 DIGITS AND STARTS, WITH RANGE (6-9)"));

            return getMobileNumber();
        }
    }

    /**
     * <p>
     * Collects and validates the user's name
     * </p>
     *
     * @return Returns validated name
     */
    private String getName() {
        System.out.println("ENTER YOUR USERNAME :\nDO YOU WANT TO GO MENU, PRESS '$'");
        final String name = SCANNER.nextLine().trim();

        if (name.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validateName(name)) {
            return name;
        } else {
            System.out.println("USERNAME MUST CONTAIN 'a-z' AND 'A-Z'");

            return getName();
        }
    }

    /**
     * <p>
     * Collects and validates the user's password
     * </p>
     *
     * @return Returns validated password
     */
    private String getPassword() {
        System.out.println("ENTER PASSWORD : \nDO YOU WANT GO MENU, PRESS($)");
        final String password = SCANNER.nextLine().trim();

        if (password.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validatePassword(password)) {
            return password;
        } else {
            System.out.println(String.join("", "PASSWORD MUST CONTAINS AT LEAST ONE CAPITAL AND SMALL ",
                    "LETTER, NUMBER, SPECIAL CHARACTER"));

            return getPassword();
        }
    }

    /**
     * <p>
     * Collects and validates the user's date of birth
     * </p>
     *
     * @return Returns validated date of birth
     */
    private String getDateOfBirth() {
        System.out.println("ENTER DATE OF BIRTH : \nDO YOU WANT GO MENU, PRESS($)");
        final String dateOfBirth = SCANNER.nextLine().trim();

        if (dateOfBirth.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validateDateOfBirth(dateOfBirth)) {
            return dateOfBirth;
        } else {
            System.out.println("ENTER THE CORRECT DATE!!!");

            return getDateOfBirth();
        }
    }

    /**
     * <p>
     * Collects and validates the user's gender
     * </p>
     *
     * @return Returns {@link User.Gender} of the user
     */
    private User.Gender getGender() {
        System.out.println("ENTER GENDER (MALE,FEMALE,OTHERS)");
        final String gender = SCANNER.nextLine().toUpperCase();

        try {
            return (USER_VALIDATION.validateGender(gender));
        } catch (final IllegalArgumentException illegalArgumentException) {
            System.out.println("ENTER AN VALID GENDER");
        }

        return getGender();
    }

    /**
     * <p>
     * Collects and validates the user's choice as an integer value
     * </p>
     *
     * @return Returns validated choice
     */
    public int getChoice() {
        try {
            System.out.println("ENTER YOUR CHOICE :");
            final int choice = Integer.parseInt(SCANNER.nextLine());

            if (USER_VALIDATION.validateChoice(String.valueOf(choice))) {
                return choice;
            }
        } catch (final NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getChoice();
    }

    /**
     * <p>
     * Collects the user's sign-in choice
     * </p>
     *
     * @param user Refer {@link User} to sign in
     */
    private void SignInChoice(final User user) {
        System.out.println("CLICK 1 TO MOBILE NUMBER\nCLICK 2 TO EMAIL ID");

        switch (getChoice()) {
            case 1:
                user.setMobileNumber(getMobileNumber());
                break;
            case 2:
                user.setEmail(getEmail());
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT 1 OR 2");
                SignInChoice(user);
        }
    }
}
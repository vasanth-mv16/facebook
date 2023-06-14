package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.util.Collection;
import java.util.Scanner;

/**
 * <p>
 *     Manages the view for Users, including creating, retrieving, updating, and printing post details.
 * </p>
 *
 * @version 1.0
 * @author vasanth
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

    private UserView() {}

    public static UserView getInstance() {
        return (null == userView) ? userView = new UserView() : userView;
    }

    /**
     * <p>
     *     Displays the menu details
     * </p>
     */
    public void displayMenu() {
        System.out.println("\tFACEBOOK\nCLICK 1 TO SIGN UP\nCLICK 2 TO SIGN IN");

        switch (getChoice()) {
            case 1:
                System.out.println("PRESS YES FOR SIGN UP AND PRESS NO FOR MENU");

                if (USER_VALIDATION.validateForYes(SCANNER.nextLine())) {
                    signUp();
                } else {
                    displayMenu();
                }
                break;
            case 2:
                signIn();
                break;
            default :
                System.out.println("INVALID CHOICE, SELECT 1 OR 2");
                displayMenu();
                break;
        }
    }

    /**
     * <p>
     *     Handles the sign-up process for a new user, collecting and validating user information, creating a new user
     *     account, and providing options for actions.
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
        if ((USER_CONTROLLER.create(user))) {
            System.out.println("ACCOUNT SUCCESSFULLY SIGN UP");
            System.out.println(USER_CONTROLLER.getUserId(user));
        } else {
            System.out.println("ACCOUNT ALREADY EXIST");
        }
        System.out.println("PRESS YES FOR EDIT USER DETAILS AND PRESS NO FOR MENU ");

        if (USER_VALIDATION.validateForYes(SCANNER.nextLine())) {
            displaysUserOptions((USER_CONTROLLER.getUserId(user)));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     *     Deletes a user based on the provided userID
     * </p>
     */
    private void delete(final Long id) {
        try {
            if (USER_CONTROLLER.isDelete(id)) {
                System.out.println("SUCCESSFULLY DELETED");
            } else {
                System.out.println("NOT DELETED");
            }
        } catch (final Exception exception) {
            System.out.println("ENTER AN CORRECT USER ID");
            delete(id);
        }
    }

    /**
     * <p>
     *     Retrieves and prints the details of the user
     * </p>
     */
    private Collection<User> get() {
       final Collection<User> getUser = USER_CONTROLLER.getUserDetails();

       System.out.println(getUser);
       return getUser;
    }

    /**
     * <p>
     *     Updates the user's account information based on the provided ID
     * </p>
     */
    private void update(final Long id) {
        final User user = new User();
        final User existingUser = getById(id);

        user.setId(existingUser.getId());
        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        user.setName(USER_VALIDATION.validateForYes(SCANNER.nextLine()) ? getName() : existingUser.getName());
        System.out.println("DO YOU WANT TO EDIT EMAIL, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        user.setEmail(USER_VALIDATION.validateForYes(SCANNER.nextLine()) ? getEmail() : existingUser.getEmail());
        System.out.println("DO YOU WANT TO EDIT PASSWORD, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        user.setPassword(USER_VALIDATION.validateForYes(SCANNER.nextLine()) ? getPassword() : existingUser.getPassword());
        System.out.println((USER_CONTROLLER.isUpdate(user)) ? "DETAILS SUCCESSFULLY UPDATED" : "NOT UPDATED");
    }

    /**
     * <p>
     *     Handles the sign-in process for a user, collecting and validating user information, providing options for
     *     further actions.
     * </p>
     */
    private void signIn() {
        final User user = new User();

        getSignInChoice(user);
        user.setPassword(getPassword());
        System.out.println((USER_CONTROLLER.isSignIn(user)) ? "ACCOUNT SIGN IN" : "INCORRECT EMAIL OR MOBILE NUMBER AND PASSWORD");
        System.out.println(String.join("","DO YOU WANT TO EDIT,GET,DELETE THE USER DETAILS,PRESS 'YES' ",
                "FOR PRINT OPTION AND PRESS 'NO' FOR MANI MENU"));

        if (USER_VALIDATION.validateForYes(SCANNER.nextLine())) {
            displaysUserOptions((USER_CONTROLLER.getUserId(user)));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     *     Retrieves and returns a User object based on the provided user ID
     * </p>
     * 
     * @return {@link User}
     */
    public User getById(final Long id) {
        System.out.println(USER_CONTROLLER.getUser(id));
        return (USER_CONTROLLER.getUser(id));
    }

    /**
     * <p>
     *     Exits the program by printing an exit message, closing the Scanner, and close the program.
     * </p>
     */
    private void exits() {
        System.out.println("EXITING");
        SCANNER.close();
        System.exit(0);
    }

    /**
     * <p>
     *     Displays the user options and performs the action based on the user's choice.
     * </p>
     */
    public void displaysUserOptions(final Long id) {
        System.out.println(String.join("\n","CLICK 1 TO GET","CLICK 2 TO UPDATE","CLICK 3 TO DELETE",
                "CLICK 4 TO GET USERS","CLICK 5 TO DISPLAY POST DETAILS","CLICK 6 TO LOGOUT","CLICK 7 TO EXIT"));

        switch (getChoice()) {
            case 1:
                get();
                break;
            case 2:
                update(id);
                break;
            case 3:
                delete(id);
                break;
            case 4:
                getById(id);
                break;
            case 5:
                POST_VIEW.displayPostDetails(id);
                break;
            case 6:
                logout();
                break;
            case 7:
                exits();
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                displaysUserOptions(id);
                break;
        }
        displaysUserOptions(id);
    }

    /**
     * <p>
     *     Handles the user logout process, displaying a confirmation message, and log out the user if confirmed
     * </p>
     */
    private void logout() {
        System.out.println("DO YOU WANT TO LOGOUT?,CLICK YES");

        if (USER_VALIDATION.validateForYes(SCANNER.nextLine())) {
            displayMenu();
        } else {
            logout();
        }
    }

    /**
     * <p>
     *     Collects and validates the user's email address
     * </p>
     *
     * @return the validated email address.
     */
    public String getEmail() {
        System.out.println("ENTER YOUR EMAIL ID : \nDO YOU WANT GO MENU, PRESS($)");
        final String email = SCANNER.nextLine().trim();

        if (email.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validateEmail(email)) {
            return email;
        } else {
            System.out.println(String.join("", "EMAIL MUST CONTAINS 'a-z' AT FIRST, THEN CONTAINS",
                    " 'a-z' OR '0-9' AND '@', AFTER 'a-z' AND '.' THEN 2 OR MORE 'a-z'"));

            return getEmail();
        }
    }

    /**
     * <p>
     *     Collects and validates the user's mobile number
     * </p>
     *
     * @return the validated mobile number
     */
    public String getMobileNumber() {
        System.out.println("ENTER YOUR MOBILE NUMBER : \nDO YOU WANT GO MENU, PRESS($)");
        final String mobileNumber = SCANNER.nextLine().trim();

        if (mobileNumber.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validateMobileNumber(mobileNumber)) {
            return mobileNumber;
        } else {
            System.out.println(String.join("","MOBILE NUMBER MUST CONTAINS '+91' FOLLOWED BY ",
                    "10 DIGITS AND STARTS, WITH RANGE (6-9)"));

            return getMobileNumber();
        }
    }

    /**
     * <p>
     *     Collects and validates the user's name
     * </p>
     *
     * @return the validated name
     */
    public String getName() {
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
     *     Collects and validates the user's password
     * </p>
     *
     * @return the validated password
     */
    public String getPassword() {
        System.out.println("ENTER PASSWORD : \nDO YOU WANT GO MENU, PRESS($)");
        final String password = SCANNER.nextLine().trim();

        if (password.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.validatePassword(password)) {
            return password;
        } else {
            System.out.println(String.join("","PASSWORD MUST CONTAINS AT LEAST ONE CAPITAL AND SMALL ",
                    "LETTER, NUMBER, SPECIAL CHARACTER")) ;

            return getPassword();
        }
    }

    /**
     * <p>
     *     Collects and validates the user's date of birth
     * </p>
     *
     * @return the validated date of birth
     */
    public String getDateOfBirth() {
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
     *     Collects and validates the user's gender
     * </p>
     *
     * @return {@link User.Gender}
     */
    public User.Gender getGender() {
        System.out.println("ENTER GENDER (MALE,FEMALE,OTHERS)");
        final String gender = SCANNER.nextLine().toUpperCase();

        try {
            return (USER_VALIDATION.validateGender(gender));
        } catch (final Exception exception) {
            System.out.println("ENTER AN VALID GENDER");
        }
        return getGender();
    }

    /**
     * <p>
     *     Collects and validates the user's choice as an integer value
     * </p>
     *
     * @return the validated choice
     */
    public int getChoice()  {
        try {
            System.out.println("ENTER YOUR CHOICE :");
            final int choice = Integer.parseInt(SCANNER.nextLine());

            if (USER_VALIDATION.validateChoice(String.valueOf(choice))) {
                return choice;
            }
        } catch (final Exception exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getChoice();
    }

    /**
     * Collects the user's sign-in choice
     *
     * @param user Represents the user for sign in
     */
    public User getSignInChoice(final User user) {
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
                getSignInChoice(user);
                break;
        }
        return user;
    }
}
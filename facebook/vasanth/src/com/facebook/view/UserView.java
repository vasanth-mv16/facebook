package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.util.Scanner;

/**
 * Manages the view for Users, including creating, retrieving, updating, and printing post details.
 *
 * @version 1.0
 * @author vasanth
 */
public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserController USER_CONTROLLER = UserController.getUserController();
    private static final UserValidation USER_VALIDATION = UserValidation.getUserValidation();
    private static final PostView POST_VIEW = PostView.getPostView();
    private static UserView userView;
    private static Long id = 1L;

    public static void main(String[] args) {
        final UserView userView = getInstance();

        userView.displayMenu();
    }

    UserView() {}

    public static UserView getInstance() {
        if (userView == null) {
            userView = new UserView();
        }
        return userView;
    }


    /**
     * Prints the menu details
     */
    public void displayMenu() {
        System.out.println("\tFACEBOOK\nCLICK 1 TO SIGN UP\nCLICK 2 TO SIGN IN");

        switch (getChoice()) {
            case 1:
                System.out.println("PRESS YES FOR SIGN UP AND PRESS NO FOR MENU");

                if (USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine())) {
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
     * Handles the sign-up process for a new user, collecting and validating user information, creating a new user
     * account, and providing options for actions.
     */
    private void signUp() {
        final User user = new User();

        user.setId(id++);
        user.setName(getName());
        user.setMobileNumber(getMobileNumber());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        getGender(user);
        user.setDateOfBirth(getDateOfBirth());
        System.out.println((USER_CONTROLLER.create(user)) ? "ACCOUNT SUCCESSFULLY SIGN UP" : "ACCOUNT ALREADY EXIST" );
        System.out.println("PRESS YES FOR EDIT USER DETAILS AND PRESS NO FOR MENU ");

        if (USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine())) {
            displaysUserOptions(user);
        } else {
            displayMenu();
        }
    }

    /**
     * Deletes a user based on the provided userID
     */
    private void deleteUser() {
        System.out.println("ENTER USERID");
        System.out.println(USER_CONTROLLER.isDelete(Long.valueOf(SCANNER.nextLine())));
    }

    /**
     * Retrieves and prints the details of the user
     */
    private void getUserDetail() {
        System.out.println(USER_CONTROLLER.getUserDetails());
    }

    /**
     * Updates the user's account information based on the provided ID
     */
    private void updateUser() {
        final User user = new User();

        System.out.println("ENTER ID TO UPDATE:");
        user.setId(Long.valueOf(SCANNER.nextLine()));
        final User get = getUser();

        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        user.setName(USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine()) ? getName() : get.getName());
        System.out.println("DO YOU WANT TO EDIT EMAIL, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        user.setEmail(USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine()) ? getEmail() : get.getEmail());
        System.out.println("DO YOU WANT TO EDIT PASSWORD, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        user.setPassword(USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine()) ? getPassword() : get.getPassword());
        System.out.println("DO YOU WANT TO EDIT NAME, PRESS 'YES' OR 'Y' AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");
        System.out.println((USER_CONTROLLER.isUpdate(user)) ? "DETAILS SUCCESSFULLY UPDATED" : "NOT UPDATED");
    }

    /**
     * Handles the sign-in process for a user, collecting and validating user information, providing options for
     * further actions.
     */
    private void signIn() {
        final User user = new User();

        System.out.println("ENTER USERID");
        System.out.println((USER_CONTROLLER.isSignIn(Long.valueOf(SCANNER.nextLine()))) ? "ACCOUNT SIGN IN" : "INCORRECT EMAIL OR PASSWORD");
        System.out.println(String.join("","DO YOU WANT TO EDIT,GET,DELETE THE USER DETAILS,PRESS 'YES' ",
                "FOR PRINT OPTION AND PRESS 'NO' FOR MANI MENU"));

        if (USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine())) {
            displaysUserOptions(user );
        } else {
            displayMenu();
        }
    }

    /**
     * Retrieves and returns a User object based on the provided user ID
     */
    public User getUser() {
        System.out.println("ENTER THE USER ID");
        return (USER_CONTROLLER.getUser(Long.valueOf(SCANNER.nextLine())));
    }

    /**
     * Exits the program by printing an exit message, closing the Scanner, and close the program.
     */
    private void exits() {
        System.out.println("EXITING");
        SCANNER.close();
        System.exit(0);
    }

    /**
     * Prints the user options and performs the action based on the user's choice.
     * @param user - The User object associated with the current option
     */
    public void displaysUserOptions(final User user) {
        System.out.println(String.join("\n","CLICK 1 TO GET","CLICK 2 TO UPDATE","CLICK 3 TO DELETE",
                "CLICK 4 TO GET USERS","CLICK 5 TO PRINT POST DETAILS","CLICK 6 TO LOGOUT","CLICK 7 TO EXIT",
                "CLICK 8 TO PRINT MENU"));

        switch (getChoice()) {
            case 1:
                getUserDetail();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                getUser();
                break;
            case 5:
                POST_VIEW.displayPostDetails(user);
                break;
            case 6:
                logout();
                break;
            case 7:
                exits();
                break;
            case 8:
                displayMenu();
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                displaysUserOptions(user );
                break;
        }
        displaysUserOptions(user );
    }

    /**
     * Handles the user logout process, displaying a confirmation message, and log out the user if confirmed.
     */
    private void logout() {
        System.out.println("DO YOU WANT TO LOGOUT?,CLICK YES");

        if (USER_VALIDATION.isValidateCheckForYes(SCANNER.nextLine())) {
            displayMenu();
        } else {
            logout();
        }
    }

    /**
     * Collects and validates the user's email address
     *
     * @return The validated email address.
     */
    public String getEmail() {
        System.out.println("ENTER YOUR EMAIL ID : \nDO YOU WANT GO MENU, PRESS($)");
        final String email = SCANNER.nextLine().trim();

        if (email.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.isValidateEmail(email)) {
            return email;
        } else {
            System.out.println(String.join("", "EMAIL MUST CONTAINS 'a-z' AT FIRST, THEN CONTAINS",
                    " 'a-z' OR '0-9' AND '@', AFTER 'a-z' AND '.' THEN 2 OR MORE 'a-z'"));
            return getEmail();
        }
    }

    /**
     * Collects and validates the user's mobile number
     *
     * @return The validated mobile number
     */
    public String getMobileNumber() {
        System.out.println("ENTER YOUR MOBILE NUMBER : \nDO YOU WANT GO MENU, PRESS($)");
        final String mobileNumber = SCANNER.nextLine().trim();

        if (mobileNumber.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.isValidateMobileNumber(mobileNumber)) {
            return mobileNumber;
        } else {
            System.out.println(String.join("","INDIA MOBILE NUMBER MUST CONTAINS '+91' FOLLOWED BY ",
                    "10 DIGITS AND STARTS, WITH RANGE (6-9)"));
            return getMobileNumber();
        }
    }

    /**
     * Collects and validates the user's name
     *
     * @return The validated name
     */
    public String getName() {
        System.out.println("ENTER YOUR USERNAME :\nDO YOU WANT TO GO MENU, PRESS '$'");
        final String name = SCANNER.nextLine().trim();

        if (name.contains("$")) {
            displayMenu();
        }
        if (USER_VALIDATION.isValidateName(name)) {
            return name;
        } else {
            System.out.println("USERNAME MUST CONTAIN 'a-z' AND 'A-Z'");

            return getName();
        }
    }

    /**
     * Collects and validates the user's password
     *
     * @return The validated password
     */
    public String getPassword() {
        System.out.println("ENTER PASSWORD : \nDO YOU WANT GO MENU, PRESS($)");
        final String password = SCANNER.nextLine().trim();

        if (password.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.isValidatePassword(password)) {
            return password;
        } else {
            System.out.println(String.join("","PASSWORD MUST CONTAINS AT LEAST ONE CAPITAL AND SMALL ",
                    "LETTER, NUMBER, SPECIAL CHARACTER")) ;

            return getPassword();
        }
    }

    /**
     * Collects and validates the user's date of birth
     *
     * @return The validated date of birth
     */
    public String getDateOfBirth() {
        System.out.println("ENTER DATE OF BIRTH : \nDO YOU WANT GO MENU, PRESS($)");
        final String dateOfBirth = SCANNER.nextLine().trim();

        if (dateOfBirth.contains("$")) {
            displayMenu();
        }

        if (USER_VALIDATION.isValidateDateOfBirth(dateOfBirth)) {
            return dateOfBirth;
        } else {
            System.out.println("ENTER THE CORRECT DATE!!!");
            return getDateOfBirth();
        }
    }

    /**
     * Collects the user's gender choice and sets the gender value in the User object.
     *
     * @param user - The User object to set the gender value
     */
    public User getGender(final User user) {
        System.out.println("ENTER GENDER\nCLICK 1 TO MALE\nCLICK 2 TO FEMALE\nCLICK 3 TO OTHERS");

        switch (getChoice()) {
            case 1:
                user.setGender(User.Gender.MALE);
                break;
            case 2:
                user.setGender(User.Gender.FEMALE);
                break;
            case 3:
                user.setGender(User.Gender.OTHERS);
                break;
            default :
                System.out.println("INVALID, SELECT ABOVE OPTION");
                return getGender(user);
        }
        return user;
    }

    /**
     * Collects and validates the user's choice as an integer value
     *
     * @return The validated choice
     */
    public int getChoice()  {
        try {
            System.out.println("ENTER YOUR CHOICE :");
            final int choice = Integer.parseInt(SCANNER.nextLine());

            if (USER_VALIDATION.isValidateChoice(String.valueOf(choice))) {
                return choice;
            }
        } catch (final Exception exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getChoice();
    }
}
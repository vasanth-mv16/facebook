package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.view.validation.UserValidation;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Menu option for sign up, sign in, post and edit
 *
 * @version 1.0
 * @author vasanth
 */
public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserController USER_CONTROLLER = new UserController();
    private static final UserValidation USER_VALIDATION = new UserValidation();
    private static final PostView POST_VIEW = new PostView();
    private static Long id = 0L;

    public static void main(String[] args) {
        final UserView userView = new UserView();

        userView.printMenu();
    }

    /**
     * Prints the menu details
     */
    public void printMenu() {
        System.out.println("CLICK 1 TO SIGN UP\nCLICK 2 TO SIGN IN");

        switch (getChoice()) {
            case 1:
                System.out.println("PRESS ANY WORD FOR SIGN UP AND PRESS NO FOR MENU");

                if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
                    printMenu();
                } else {
                    signUp();
                }
                break;
            case 2:
                signIn();
                break;
            default :
                System.out.println("INVALID CHOICE, SELECT 1 OR 2");
                printMenu();
                break;
        }
    }

    /**
     * Shows the user sign up details
     */
    private void signUp() {
        final User user1 = new User();
        user1.setId(++id);
        user1.setName(getName());
        user1.setMobileNumber(getMobileNumber());
        user1.setEmail(getEmail());
        user1.setPassword(getPassword());
        getGender(user1);
        System.out.println(USER_CONTROLLER.create(user1));
        System.out.println("ACCOUNT SUCCESSFULLY SIGN UP\nPRESS ANY WORD FOR EDIT AND PRESS NO FOR MENU ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            printMenu();
        } else {
            printUserOptions(user1);
        }
    }

    /**
     * Shows the user delete details
     */
    private void deleteUser() {
        System.out.println("ENTER USERID");
        System.out.println(USER_CONTROLLER.isDelete(SCANNER.nextLong()));
//        printUserOptions();
    }

    /**
     * Gets the user get details
     */
    private void getUserDetail() {
        System.out.println(USER_CONTROLLER.getUserDetails());
//        printUserOptions();
    }

    /**
     * Shows the user update details
     */
    private void updateUser() {
        final User user = new User();

        System.out.println("ENTER ID TO UPDATE:");
        user.setId(Long.valueOf(SCANNER.nextLine()));
        getUpdateChoice(user);

        if (USER_CONTROLLER.isUpdate(user)) {
            System.out.println("ACCOUNT SUCCESSFULLY UPDATED");
//            printUserOptions();
        } else {
            System.out.println("NOT UPDATED");
//            printUserOptions();
        }
    }

    /**
     * Shows the user sign in details
     */
    private void signIn() {
        final User user = new User();

        getSignInChoice(user);
        user.setPassword(getPassword());
        System.out.println((USER_CONTROLLER.isSignIn(user)) ? "ACCOUNT SIGN IN" : "NOT SIGN IN");
        System.out.println("DO YOU WANT TO EDIT,GET,DELETE, CLICK 'y' AND PRESS ANY KEY FOR MANI MENU");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            printUserOptions(user);
        } else {
            printMenu();
        }
    }

    /**
     * Gets the user detail
     */
    public User getUser() {
        System.out.println("ENTER THE USER ID");
        return (USER_CONTROLLER.getUser(Long.valueOf(SCANNER.nextLine())));
    }

    /**
     * Given the user exit
     */
    private void exits() {
        System.out.println("EXITING");
        SCANNER.close();
        System.exit(0);
    }

    /**
     * Prints the options for user
     */
    public void printUserOptions(final User user) {
        System.out.println("CLICK 1 TO GET\nCLICK 2 TO UPDATE\nCLICK 3 TO DELETE\nCLICK 4 TO GET USERS\nCLICK 5 TO " +
                "PRINT POST DETAILS\nCLICK 6 TO LOGOUT\nCLICK 7 TO EXIT\nCLICK 8 TO PRINT MENU");

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
                POST_VIEW.printPostDetails(user);
                break;
            case 6:
                logout();
                break;
            case 7:
                exits();
                break;
            case 8:
                printMenu();
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT THE ABOVE CHOICE");
                printUserOptions(user);
                break;
        }
        printUserOptions(user);
    }

    /**
     * Gets the user logout
     */
    private void logout() {
        System.out.println("DO YOU WANT TO LOGOUT?,CLICK YES");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            System.out.println("ACCOUNT SUCCESSFULLY LOGOUT");
            printMenu();
        } else {
            logout();
        }
    }

    /**
     * Gets the user email detail
     *
     * @return emailId of the user
     */
    public String getEmail() {
        System.out.println("EMAIL ID MUST CONTAINS 'a-z' AT FIRST, THEN CONTAINS 'A-Z, 0-9' AND '@' AFTER 'a-z A-Z' " +
                           "AND '.' THEN 2 OR MORE ALPHABETIC CHARACTER\nENTER YOUR EMAIL ID :");
        final String email = SCANNER.nextLine().trim();

        return (USER_VALIDATION.isValidateEmail(email)) ? email : getEmail();
    }

    /**
     * Gets the user mobile number
     *
     * @return mobile number of the user
     */
    public String getMobileNumber() {
        System.out.println("MOBILE NUMBER MUST CONTAINS 10 DIGIT AND STARTS WITH RANGE (6-9)\nENTER YOUR MOBILE " +
                           "NUMBER :");
        final String mobileNumber = SCANNER.nextLine().trim();

        return (USER_VALIDATION.isValidateMobileNumber(mobileNumber)) ? mobileNumber : getMobileNumber();
    }

    /**
     * Gets the user name detail
     *
     * @return username of the user
     */
    public String getName() {
        System.out.println("USERNAME MUST CONTAIN 'a-z' AND '_' AND '0-9'\nENTER YOUR USERNAME :");
        final String name = SCANNER.nextLine().trim();

        return (USER_VALIDATION.isValidateName(name)) ? name : getName();
    }

    /**
     * Gets the user password detail
     *
     * @return password of the user
     */
    public String getPassword() {
        System.out.println("PASSWORD MUST CONTAINS AT LEAST ONE CAPITAL AND SMALL LETTER, NUMBER, SPECIAL CHARACTER " +
                           "AND NO WHITESPACE\nENTER PASSWORD :\n");
        final String password = SCANNER.nextLine().trim();

        return (USER_VALIDATION.isValidatePassword(password)) ?  password : getPassword();
    }

    /**
     * Gets the user date of birth detail
     *
     * @return date of birth of the user
     */
    public String getDateOfBirth() {
        System.out.println("ENTER DATE OF BIRTH :");
        final String dateOfBirth = SCANNER.nextLine().trim();

        return (USER_VALIDATION.isValidateDateOfBirth(dateOfBirth)) ? dateOfBirth : getDateOfBirth();
    }

    /**
     * Gets the user gender detail
     *
     * @param user to identify the user gender
     */
    public void getGender(final User user) {
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
                getGender(user);
                break;
        }
    }

    /**
     * Gets the user choice detail
     *
     * @return choice of the user
     */
    public int getChoice() {
        try {
            System.out.println("ENTER YOUR CHOICE :");
            final int choice = Integer.parseInt(SCANNER.nextLine());

            if (USER_VALIDATION.isValidateChoice(String.valueOf(choice))) {
                return choice;
            }
        } catch (final InputMismatchException | NumberFormatException exception) {
            System.out.println("PLEASE ENTER AN INTEGER");
        }

        return getChoice();
    }

    /**
     * Gets the user sign in choice details
     *
     * @param user the user has to passed for sign in
     */
    public void getSignInChoice(final User user) {
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
    }

    /**
     * Gets the user update choice details
     *
     * @param user the user to be passed for update
     */
    public void getUpdateChoice(final User user) {
        final User get = getUser();

        System.out.println("DO YOU WANT TO EDIT NAME, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            user.setName(get.getName());
        } else {
            user.setName(getName());
        }
        System.out.println("DO YOU WANT TO EDIT EMAIL, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            user.setEmail(get.getEmail());
        } else {
            user.setEmail(getEmail());
        }
        System.out.println("DO YOU WANT TO EDIT PASSWORD, PRESS ANY KEY AND DON'T WANT TO EDIT PRESS 'NO' OR 'N' ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            user.setPassword(get.getPassword());
        } else {
            user.setPassword(getPassword());
        }
    }

}

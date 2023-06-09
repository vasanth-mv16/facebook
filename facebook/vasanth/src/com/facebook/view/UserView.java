package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.model.Post;
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
    private static Long id = 1L;

    public static void main(String[] args) {
        final UserView userView = new UserView();

        userView.printMenu();
    }

    /**
     * Prints the menu details
     */
    public void printMenu() {
        System.out.println("\tFACEBOOK\t");
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
        final User user = new User();

        user.setId(id++);
        user.setName(getName());
        user.setMobileNumber(getMobileNumber());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        getGender(user);
        user.setDateOfBirth(getDateOfBirth());
        System.out.println((USER_CONTROLLER.create(user)) ? "ACCOUNT SUCCESSFULLY SIGN UP" : "ACCOUNT ALREADY EXIST" );
        System.out.println("PRESS ANY WORD FOR EDIT USER DETAILS AND PRESS NO FOR MENU ");

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            printMenu();
        } else {
            printUserOptions(user);
        }
    }

    /**
     * Shows the user delete details
     */
    private void deleteUser() {
        System.out.println("ENTER USERID");
        System.out.println(USER_CONTROLLER.isDelete(Long.valueOf(SCANNER.nextLine())));
    }

    /**
     * Gets the user get details
     */
    private void getUserDetail() {
        System.out.println(USER_CONTROLLER.getUserDetails());
    }

    /**
     * Shows the user update details
     */
    private void updateUser() {
        final User user = new User();

        System.out.println("ENTER ID TO UPDATE:");
        user.setId(Long.valueOf(SCANNER.nextLine()));
        getUpdateChoice(user);
        System.out.println((USER_CONTROLLER.isUpdate(user)) ? "ACCOUNT SUCCESSFULLY UPDATED" : "NOT UPDATED");
    }

    /**
     * Shows the user sign in details
     */
    private void signIn() {
        final User user = new User();

        getSignInChoice(user);
        user.setPassword(getPassword());
        System.out.println((USER_CONTROLLER.isSignIn(user)) ? "ACCOUNT SIGN IN" : "INCORRECT EMAIL OR PASSWORD");
        System.out.println(String.join("","DO YOU WANT TO EDIT,GET,DELETE THE USER DETAILS,PRESS ANY ",
                "KEY FOR PRINT OPTION AND CLICK 'N' FOR MANI MENU"));

        if (USER_VALIDATION.isValidateCheck(SCANNER.nextLine())) {
            printMenu();
        } else {
            printUserOptions(user );
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
                printUserOptions(user );
                break;
        }
        printUserOptions(user );
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
        System.out.println("ENTER YOUR EMAIL ID : \nDO YOU WANT GO MENU, PRESS($)");
        final String email = SCANNER.nextLine().trim();

        if (email.contains("$")) {
            printMenu();
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
     * Gets the user mobile number
     *
     * @return mobile number of the user
     */
    public String getMobileNumber() {
        System.out.println("ENTER YOUR MOBILE NUMBER : \nDO YOU WANT GO MENU, PRESS($)");
        final String mobileNumber = SCANNER.nextLine().trim();

        if (mobileNumber.contains("$")) {
            printMenu();
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
     * Gets the user name detail
     *
     * @return username of the user
     */
    public String getName() {
        System.out.println("ENTER YOUR USERNAME :\nDO YOU WANT TO GO MENU, PRESS '$'");
        final String name = SCANNER.nextLine().trim();

        if (name.contains("$")) {
            printMenu();
        }
        if (USER_VALIDATION.isValidateName(name)) {
            return name;
        } else {
            System.out.println("USERNAME MUST CONTAIN 'a-z' AND 'A-Z'");

            return getName();
        }
    }

    /**
     * Gets the user password detail
     *
     * @return password of the user
     */
    public String getPassword() {
        System.out.println("ENTER PASSWORD : \nDO YOU WANT GO MENU, PRESS($)");
        final String password = SCANNER.nextLine().trim();

        if (password.contains("$")) {
            printMenu();
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
     * Gets the user date of birth detail
     *
     * @return date of birth of the user
     */
    public String getDateOfBirth() {
        System.out.println("ENTER DATE OF BIRTH : \nDO YOU WANT GO MENU, PRESS($)");
        final String dateOfBirth = SCANNER.nextLine().trim();

        if (dateOfBirth.contains("$")) {
            printMenu();
        }

        if (USER_VALIDATION.isValidateDateOfBirth(dateOfBirth)) {
            return dateOfBirth;
        } else {
            System.out.println("ENTER THE CORRECT DATE!!!");
            return getDateOfBirth();
        }
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
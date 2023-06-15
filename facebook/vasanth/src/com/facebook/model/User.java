package com.facebook.model;

/**
 * Represents the given user for storing information
 *
 * @version 1.0
 * @author vasanth
 */
public class User {

    private Gender gender;
    private Long id;
    private String name;
    private String mobileNumber;
    private String password;
    private String email;
    private String dateOfBirth;

    /**
     * An enum with values MALE, FEMALE
     */
    public enum Gender {
        
        MALE,
        FEMALE,
        OTHERS
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void  setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String toString() {
        return String.format("%d  Name = %s Email = %s Phone Number = %s Gender = %s", id, name, email, mobileNumber, gender);
    }
}


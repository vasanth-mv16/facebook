package com.facebook.model;

import java.util.List;

/**
 * Represents the given user
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
    private List<Post> posts;

    /**
     * An enum with values MALE, FEMALE
     */
    public enum Gender {
        MALE, FEMALE, OTHERS
    }

    public void setPosts (final List<Post> posts ) {
        this.posts = posts;
    }

    public List<Post> getPosts () {
        return posts;
    }
    public Gender getGender() {
        return gender;
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
        return String.format("%d %s %s %s", id, name, email, gender);
    }
}


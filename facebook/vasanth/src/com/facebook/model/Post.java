package com.facebook.model;

import java.time.LocalDateTime;

/**
 * Represents the user post
 *
 * @version 1.0
 * @author vasanth
 */
public class Post {

    private User user;
    private Long id;
    private String caption;
    private String location;
    private LocalDateTime dateTime;

    public void setUser(final User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String toString() {
        return String.format("%d Caption=%s  Location=%s  %s User=%s", id, caption, location, dateTime, user);
    }

}

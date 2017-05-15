package com.lee.nairobidevelopers.model;

/**
 * Created by Lee on 15/5/2017.
 */

public class Developer {

    private String username;
    private String id;
    private String profileURL;

    //default constructor
    public Developer() {
    }

    //constructor
    public  Developer(String username, String id, String profileURL ){
        this.username = username;
        this.id = id;
        this.profileURL = profileURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}

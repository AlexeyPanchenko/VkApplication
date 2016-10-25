package com.example.infovk.model;


import java.util.ArrayList;

public class User {
    private Profile profile;
    private ArrayList<Profile> friends;

    public User(ArrayList<Profile> friends, Profile profile) {
        this.friends = friends;
        this.profile = profile;
    }

    public User() {

    }

    public Profile getProfile(){
        return profile;
    }
    public ArrayList<Profile> getFriends(){
        return friends;
    }

}

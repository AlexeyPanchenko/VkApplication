package com.example.infovk.model;


import java.util.ArrayList;

public class User {
    private int id;
    private Profile profile;
    private ArrayList<Friend> friends;

    public User(int userID, ArrayList<Friend> userFriends, Profile profile) {
        id = userID;
        friends = userFriends;
        this.profile = profile;
    }

    public User() {

    }

    public Profile getProfile(){
        return profile;
    }
    public ArrayList<Friend> getFriends(){
        return friends;
    }
    public int getID(){
        return id;
    }

}

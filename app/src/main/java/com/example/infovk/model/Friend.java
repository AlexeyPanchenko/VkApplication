package com.example.infovk.model;


public class Friend {
    private int id;
    private int friendsCount;
    private Profile profile;
    private boolean online;

    public Friend(int friendID, int friendsCount, boolean online, Profile profile){
        this.online = online;
        id = friendID;
        this.friendsCount = friendsCount;
        this.profile = profile;
    }

    public int getID(){
        return id;
    }
    public int getFriendsCount(){
        return friendsCount;
    }
    public Profile getProfile(){
        return profile;
    }
    public boolean getOnline(){
        return online;
    }
}

package com.example.infovk.presenter;

import com.example.infovk.data.AskVk;
import com.example.infovk.model.Friend;
import com.example.infovk.model.Profile;
import com.example.infovk.view.ProfileOfFriendsView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

public class ProfileOfFriendsPresenter extends MvpBasePresenter<ProfileOfFriendsView> {
    AskVk askVk;

    public void showProfileOfFriends(){
        ArrayList<Friend> friends = askVk.askFriends();
        ArrayList<Profile> profiles = new ArrayList<>();
        for(Friend friend: friends) {
            profiles.add(friend.getProfile());
        }
        getView().showProfileOfFriends(profiles);
    }
}

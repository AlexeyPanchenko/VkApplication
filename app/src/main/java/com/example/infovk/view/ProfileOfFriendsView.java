package com.example.infovk.view;


import com.example.infovk.model.Profile;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.ArrayList;

public interface ProfileOfFriendsView extends MvpView {

    void showProfileOfFriends(ArrayList<Profile> profiles);

}

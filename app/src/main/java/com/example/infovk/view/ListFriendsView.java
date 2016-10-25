package com.example.infovk.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.ArrayList;

public interface ListFriendsView extends MvpView {

    void showListFriends(ArrayList<String> friendsNames, ArrayList<String> photos);

}

package com.example.infovk.presenter;


import com.example.infovk.data.AskVk;
import com.example.infovk.model.Friend;
import com.example.infovk.view.MyFriendsView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

public class MyFriendsPresenter extends MvpBasePresenter<MyFriendsView> {

    AskVk askVk;

    public void showMyFriends(ArrayList<Friend> friends){
        friends = askVk.askFriends();
        getView().showMyFriendsList(friends);

    }
}

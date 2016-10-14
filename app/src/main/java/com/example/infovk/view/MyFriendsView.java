package com.example.infovk.view;


import com.example.infovk.model.Friend;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.Collection;

public interface MyFriendsView extends MvpView {

    void showMyFriendsList (Collection<Friend> friends);

}

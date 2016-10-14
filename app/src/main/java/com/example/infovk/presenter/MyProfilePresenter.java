package com.example.infovk.presenter;


import com.example.infovk.data.AskVk;
import com.example.infovk.model.Profile;
import com.example.infovk.model.User;
import com.example.infovk.view.MyProfileView;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;


public class MyProfilePresenter extends MvpBasePresenter<MyProfileView> {
    AskVk askVk;

     public void showMyProfile(User user){
         user = askVk.askUser();
         Profile profile = user.getProfile();
         getView().showMyProfile(profile);
    }
}

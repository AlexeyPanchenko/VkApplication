package com.example.infovk.view;


import com.example.infovk.model.Profile;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MyProfileView extends MvpView {

    void showMyProfile (Profile profile);

}

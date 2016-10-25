package com.example.infovk.presenter;

import com.example.infovk.data.Agera;
import com.example.infovk.model.Profile;
import com.example.infovk.view.MyProfileView;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;


public class MyProfilePresenter extends MvpBasePresenter<MyProfileView> implements Updatable {

     private Agera agera = new Agera();
     private Repository<Result<Profile>> repository;

     public void showProfile(){
         repository = agera.getProfileRepository();
         repository.addUpdatable(this);
     }

    @Override
    public void update() {
        Result<Profile> profileResult = repository.get();
        if(profileResult.succeeded()){
            Profile profileMe = profileResult.get();
            getView().showMyProfile(profileMe);
        }else {
            return;
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        repository.removeUpdatable(this);
    }
}

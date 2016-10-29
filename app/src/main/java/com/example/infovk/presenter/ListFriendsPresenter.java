package com.example.infovk.presenter;

import com.example.infovk.data.Agera;
import com.example.infovk.model.Profile;
import com.example.infovk.view.ListFriendsView;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.ArrayList;

public class ListFriendsPresenter extends MvpBasePresenter<ListFriendsView> implements Updatable{

    private Agera agera = new Agera();
    private Repository<Result<ArrayList<Profile>>> repository;

    public void showMyFriends(){
        repository = agera.getFriendsRepository();
        repository.addUpdatable(this);
    }

    @Override
    public void update() {
        Result<ArrayList<Profile>> friendsResult = repository.get();
        if(friendsResult.succeeded()){
            ArrayList<Profile> friends = friendsResult.get();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> photos = new ArrayList<>();
            for (int i = 0; i < friends.size(); i++) {
                names.add(friends.get(i).getFirst_name() + " " + friends.get(i).getLast_name());
                photos.add(friends.get(i).getPhoto_max_orig());
            }
            getView().showListFriends(names, photos);
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

package com.example.infovk.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infovk.R;
import com.example.infovk.presenter.ListFriendsPresenter;
import com.example.infovk.view.ListFriendsView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SampleFragment extends MvpFragment<ListFriendsView, ListFriendsPresenter> implements ListFriendsView{

    TextView textFragmentSample;
    ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, null);
        textFragmentSample = (TextView) view.findViewById(R.id.textFragmentSample);
        image = (ImageView) view.findViewById(R.id.image_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.showMyFriends();
    }

    @Override
    public ListFriendsPresenter createPresenter() {
        return new ListFriendsPresenter();
    }


    @Override
    public void showListFriends(ArrayList<String> friendsNames, ArrayList<String> photos) {

        textFragmentSample.setText(friendsNames.get(25));
        Picasso.with(getContext())
                .load(photos.get(25))
                .into(image);
        //image.setImageURI(Uri.parse(photos.get(0)));
    }

}

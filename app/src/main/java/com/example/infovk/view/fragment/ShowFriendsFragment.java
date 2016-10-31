package com.example.infovk.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infovk.R;
import com.example.infovk.model.Profile;
import com.example.infovk.presenter.ListFriendsPresenter;
import com.example.infovk.presenter.MyFriendsProfilePresenter;
import com.example.infovk.view.ListFriendsView;
import com.example.infovk.view.MyFriendsProfilesView;
import com.example.infovk.view.activity.ShowProfileFriendsActivity;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ShowFriendsFragment extends MvpFragment<MyFriendsProfilesView, MyFriendsProfilePresenter> implements MyFriendsProfilesView{

    private RecyclerView recyclerView;
    private FriendsAdapter fAdapter;



    @Override
    public MyFriendsProfilePresenter createPresenter() {
        return new MyFriendsProfilePresenter();
    }
    @Override
    public void showProfilesFriends(ArrayList<Profile> friends) {
        fAdapter = new FriendsAdapter(friends);
        recyclerView.setAdapter(fAdapter);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_friends, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_show_friends_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.showMyFriends();
    }

    private class FriendsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Profile fProfile;
        private ImageView friendPhoto;
        private TextView friendName;

        public FriendsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            friendName = (TextView) itemView.findViewById(R.id.list_item_name);
            friendPhoto = (ImageView) itemView.findViewById(R.id.list_item_photo);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ShowProfileFriendsActivity.newIntent(getActivity(), fProfile.getId());
            startActivity(intent);
        }

        public void bind(Profile friend) {
            fProfile = friend;
            friendName.setText(fProfile.getFirst_name() + " " + fProfile.getLast_name());
            Picasso.with(getActivity()).load(fProfile.getPhoto_max_orig()).into(friendPhoto);
        }
    }

    private class FriendsAdapter extends RecyclerView.Adapter<FriendsHolder> {

        private ArrayList<Profile> friendsProfiles;
        public FriendsAdapter(ArrayList<Profile> fProfile){
            friendsProfiles = fProfile;
        }

        @Override
        public FriendsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_friend, parent, false);
            return new FriendsHolder(view);
        }

        @Override
        public void onBindViewHolder(FriendsHolder holder, int position) {
            Profile friend = friendsProfiles.get(position);
            holder.bind(friend);
        }

        @Override
        public int getItemCount() {
            return friendsProfiles.size();
        }
    }
}

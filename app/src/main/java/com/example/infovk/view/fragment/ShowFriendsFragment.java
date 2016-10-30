package com.example.infovk.view.fragment;

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
import com.example.infovk.presenter.ListFriendsPresenter;
import com.example.infovk.view.ListFriendsView;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ShowFriendsFragment extends MvpFragment<ListFriendsView, ListFriendsPresenter> implements ListFriendsView{

    private RecyclerView recyclerView;
    private FriendsAdapter fAdapter;

    @Override
    public void showListFriends(ArrayList<String> friendsNames, ArrayList<String> photos) {
        fAdapter = new FriendsAdapter(friendsNames, photos);
        recyclerView.setAdapter(fAdapter);
    }

    @Override
    public ListFriendsPresenter createPresenter() {
        return new ListFriendsPresenter();
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

        ImageView friendPhoto;
        TextView friendName;

        public FriendsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            friendName = (TextView) itemView.findViewById(R.id.list_item_name);
            friendPhoto = (ImageView) itemView.findViewById(R.id.list_item_photo);
        }

        @Override
        public void onClick(View v) {

        }

        public void bind(String name, String photo) {
            friendName.setText(name);
            Picasso.with(getActivity()).load(photo).into(friendPhoto);
        }
    }

    private class FriendsAdapter extends RecyclerView.Adapter<FriendsHolder>{

        private ArrayList<String> fNames, fPhotos;
        public FriendsAdapter(ArrayList<String> names, ArrayList<String> photos){
            fNames = names;
            fPhotos = photos;
        }

        @Override
        public FriendsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_friend, parent, false);
            return new FriendsHolder(view);
        }

        @Override
        public void onBindViewHolder(FriendsHolder holder, int position) {
            String name = fNames.get(position);
            String photo = fPhotos.get(position);
            holder.bind(name, photo);
        }

        @Override
        public int getItemCount() {
            return fNames.size();
        }
    }
}

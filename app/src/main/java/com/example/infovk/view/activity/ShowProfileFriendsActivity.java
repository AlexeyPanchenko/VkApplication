package com.example.infovk.view.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.infovk.R;
import com.example.infovk.model.Profile;
import com.example.infovk.presenter.MyFriendsProfilePresenter;
import com.example.infovk.view.MyFriendsProfilesView;
import com.example.infovk.view.fragment.ShowProfileFriendsFragment;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;

public class ShowProfileFriendsActivity extends MvpActivity<MyFriendsProfilesView, MyFriendsProfilePresenter>
        implements MyFriendsProfilesView{

    private static final String EXTRA_FRIEND_ID = "com.example.infovk.view.activity.friend_id";

    private ViewPager viewPager;
    private MyFriendsPagerAdapter fPageAdapter;
    private FragmentManager fragmentManager;
    private int id;


    public static Intent newIntent(Context context, int friendId){
        Intent intent = new Intent(context, ShowProfileFriendsActivity.class);
        intent.putExtra(EXTRA_FRIEND_ID, friendId);
        return intent;
    }

    @NonNull
    @Override
    public MyFriendsProfilePresenter createPresenter() {
        return new MyFriendsProfilePresenter();
    }

    @Override
    public void showProfilesFriends(ArrayList<Profile> friends) {
        fragmentManager = getSupportFragmentManager();
        fPageAdapter = new MyFriendsPagerAdapter(fragmentManager, friends);
        viewPager.setAdapter(fPageAdapter);
        for (int i = 0; i < friends.size(); i++) {
            if(friends.get(i).getId() == id){
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile_friends);

        id = getIntent().getIntExtra(EXTRA_FRIEND_ID, 0);
        viewPager = (ViewPager) findViewById(R.id.activity_show_my_profile_and_friends_view_pager);
        presenter.showMyFriends();

    }

    private class MyFriendsPagerAdapter extends FragmentStatePagerAdapter{

        private ArrayList<Profile> fProfiles;

        public MyFriendsPagerAdapter(FragmentManager fm, ArrayList<Profile> profiles) {
            super(fm);
            fProfiles = profiles;
        }

        @Override
        public Fragment getItem(int position) {
            Profile profile = fProfiles.get(position);
            return ShowProfileFriendsFragment.newInstance(profile.getId());
        }

        @Override
        public int getCount() {
            return fProfiles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fProfiles.get(position).getFirst_name();
        }
    }



}

package com.example.infovk.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.infovk.R;
import com.example.infovk.view.fragment.ShowFriendsFragment;
import com.example.infovk.view.fragment.ShowProfileFragment;

public class ShowProfileActivity extends AppCompatActivity{

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_profile_and_friends_pager);

        viewPager = (ViewPager) findViewById(R.id.activity_show_my_profile_and_friends_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return new ShowProfileFragment();
                }else if(position == 1){
                    return new ShowFriendsFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}

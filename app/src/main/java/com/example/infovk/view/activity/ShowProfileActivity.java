package com.example.infovk.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.infovk.R;
import com.example.infovk.view.fragment.ShowProfileFragment;

public class ShowProfileActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_main_container);
        if(fragment == null){
            fragment = new ShowProfileFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_main_container, fragment)
                    .commit();
        }
    }
}

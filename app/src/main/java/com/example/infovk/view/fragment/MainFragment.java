package com.example.infovk.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.infovk.R;


public class MainFragment extends Fragment {


    public interface onSomeEventListener {

        void someEvent();
    }

    Button buttonWelcome;
    onSomeEventListener eventListenerForMainFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            eventListenerForMainFragment = (onSomeEventListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        buttonWelcome = (Button) view.findViewById(R.id.fragment_main_button);
        buttonWelcome.setOnClickListener(v -> {
            eventListenerForMainFragment.someEvent();
        });
        return view;
    }
}

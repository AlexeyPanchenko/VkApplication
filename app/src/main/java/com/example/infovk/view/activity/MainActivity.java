package com.example.infovk.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;

import com.example.infovk.R;
import com.example.infovk.data.Agera;
import com.example.infovk.data.AskVk;

import com.example.infovk.presenter.MyFriendsPresenter;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Receiver;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;

import com.vk.sdk.VKSdk;

import com.vk.sdk.api.VKError;

import com.vk.sdk.api.model.VKScopes;




public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();

    public static VKAccessToken token;
    TextView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKSdk.login(this, VKScopes.FRIENDS,VKScopes.WALL, VKScopes.STATUS, VKScopes.OFFERS);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>(){
            @Override
            public void onResult(VKAccessToken res) {
                token = res;
                Log.d(TAG, "AccessToken: "+token.accessToken);
                Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();
                listView = (TextView) findViewById(R.id.list_view);

            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                token = null;
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }


}

package com.example.infovk.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;

import com.example.infovk.R;
import com.example.infovk.data.Agera;

import com.example.infovk.model.Profile;
import com.google.android.agera.Receiver;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;

import com.vk.sdk.VKSdk;

import com.vk.sdk.api.VKError;

import com.vk.sdk.api.model.VKScopes;



public class MainActivity extends AppCompatActivity implements Receiver<Profile>, Updatable {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static VKAccessToken token;
    private TextView listView;
    private Repository<Result<Profile>> mResultRepository = new Agera().getProfileRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKSdk.login(this, VKScopes.FRIENDS,VKScopes.WALL, VKScopes.STATUS, VKScopes.OFFERS);
        listView = (TextView) findViewById(R.id.list_view);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>(){
            @Override
            public void onResult(VKAccessToken res) {
                token = res;
                Log.d(TAG, "AccessToken: "+token.accessToken);
                Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();
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
    @Override
    protected void onResume() {
        super.onResume();
        // Start listening to the repository, triggering the flow
        mResultRepository.addUpdatable(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop listening to the repository, deactivating it
        mResultRepository.removeUpdatable(this);
    }

    @Override
    public void update() {
        // Called as the repository is updated
        // If containing a valid bitmap, send to accept below
        mResultRepository.get().ifSucceededSendTo(this);
    }

    @Override
    public void accept(@NonNull Profile background) {
        // Set the background bitmap to the background view

        listView.setText(background.getCity().getCityName());
    }
}

package com.example.infovk.view.activity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.example.infovk.R;
import com.example.infovk.view.fragment.MainFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;




public class MainActivity extends AppCompatActivity implements MainFragment.onSomeEventListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static VKAccessToken token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VKSdk.login(this, VKScopes.FRIENDS,VKScopes.WALL, VKScopes.STATUS, VKScopes.OFFERS);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_main_container);
        if(fragment == null){
            fragment = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_main_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>(){
            @Override
            public void onResult(VKAccessToken res) {
                token = res;
                Log.d(TAG, "AccessToken: "+ token.accessToken);
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
    public void someEvent() {
        Intent intent = new Intent(this, ShowProfileActivity.class);
        startActivity(intent);
    }


    /*@Override
    protected void onResume() {
        super.onResume();
        // Start listening to the repository, triggering the flow
        friends.addUpdatable(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop listening to the repository, deactivating it
        friends.removeUpdatable(this);
    }

    @Override
    public void update() {
        // Called as the repository is updated
        // If containing a valid bitmap, send to accept below
        friends.get().ifSucceededSendTo(this);
    }

    @Override
    public void accept(@NonNull ArrayList<Profile> background) {
        // Set the background bitmap to the background view

        Toast.makeText(MainActivity.this, background.get(0).getFirst_name(), Toast.LENGTH_SHORT).show();
        //listView.setText(String.valueOf(background.getOnline()));
    }*/
}

package com.example.infovk.data;

import android.util.Log;

import com.example.infovk.model.Profile;
import com.example.infovk.model.Relatives;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.util.concurrent.Executors.newSingleThreadExecutor;


public class Agera{
    private static final String TAG = Agera.class.getSimpleName();

    OkHttpClient client = new OkHttpClient();
    private Repository<Result<Profile>> repository;

    public String findUrlUserProfile() {
        try {
            VKRequest vkRequest = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, bdate, city , relation, relatives, education, contacts, interests, sex, photo_100"));
            String url1 = "https://api.vk.com/method/users.get?";
            String url2 = vkRequest.getPreparedRequest().getQuery();
            String url = url1 + url2;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Repository<Result<Profile>> getProfileRepository(){

        repository = Repositories.repositoryWithInitialValue(Result.<Profile>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(newSingleThreadExecutor())
                .getFrom((Supplier<Response>) () -> {
                    Request request = new Request.Builder()
                            .url(findUrlUserProfile())
                            .build();
                    try {
                        Response response = client.newCall(request).execute();
                        return response;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .thenTransform(response -> {
                    try {

                        ObjectMapper mapper = new ObjectMapper();
                        String stringResponse = response.body().string();
                        Log.d(TAG, "stringResponse = " + stringResponse);
                        // выделяю из строки JSON объект
                        JSONObject responseObject = new JSONObject(stringResponse);
                        Log.d(TAG, "responseObject = " + responseObject);
                        // выделяю из JSON объекта JSON массив
                        JSONArray arrayProfile = responseObject.getJSONArray("response");
                        Log.d(TAG, "arrayProfile = " + arrayProfile);

                        JSONObject objectMe = arrayProfile.getJSONObject(0);
                        Log.d(TAG, "objectMe = " + objectMe);

                        Profile profile = mapper.readValue(objectMe.toString(), Profile.class);
                        Log.d(TAG, "cityName = " + profile.getCity().getCityName());
                        return Result.absentIfNull(profile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .compile();
        return repository;
    }

    /*Подписаться на обновления addUpdatable!  определить метод update желательно в презенторе добавил для коммита*/


}

package com.example.infovk.data;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.infovk.model.Friend;
import com.example.infovk.model.Profile;
import com.example.infovk.model.Relatives;
import com.example.infovk.model.User;
import com.example.infovk.view.activity.MainActivity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.agera.Function;
import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.httpClient.VKHttpClient;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

class Name {
    private String first_name;
    Name(){}
    Name(String first_name){
        this.first_name = first_name;
    }
    public String getFirst_name(){
        return first_name;
    }
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

}

public class AskVk{
    User user;
    ArrayList<Friend> friends;


    OkHttpClient client = new OkHttpClient();
    private Repository<Result<String>> repository;
    Updatable updatable;


    public Repository getRepository(){

        repository = Repositories.repositoryWithInitialValue(Result.<String>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(newSingleThreadExecutor())
                .getFrom(new Supplier<VKHttpClient.VKHTTPRequest>() {
                    @NonNull
                    @Override
                    public VKHttpClient.VKHTTPRequest get() {
                        return new VKHttpClient.VKHTTPRequest(findUrl());
                    }
                })
                .thenTransform(new Function<VKHttpClient.VKHTTPRequest, Result<String>>() {
                    @NonNull
                    @Override
                    public Result<String> apply(@NonNull VKHttpClient.VKHTTPRequest input) {

                        ObjectMapper mapper = new ObjectMapper();
                        Name name = new Name();
                        try {
                            name = mapper.readValue(findUrl(), Name.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return Result.absentIfNull(name.getFirst_name());
                    }
                })
                .compile();

        return repository;
    }


    private String findUrl() {
        VKRequest vkRequest = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name"));
        String url1 = "http://api.vk.com/method/users.get?";
        String url2;
        String url;
        String stringResponse = "";
        try {
            url2 = vkRequest.getPreparedRequest().getQuery();
            url = url1 + url2;
            Log.d("UUURRRRLLLL", "UUURRRRLLLL = " + url);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            stringResponse = response.body().string();
            Log.d("Response", "stringResponse = " + stringResponse);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringResponse;
    }

    public User askUser(){
        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_ID, "id", VKApiConst.FIELDS, "first_name, last_name, bdate, city , relation, relatives, education, contacts, interests, sex, photo_100"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                JSONObject json = response.json;
                try {
                    JSONArray arrayRelatives = json.getJSONArray("relatives");
                    ArrayList<Relatives> relatives = new ArrayList<>();
                    for (int i = 0; i < arrayRelatives.length(); i++) {
                        JSONObject arraI = (JSONObject) arrayRelatives.get(i);
                        relatives.add(new Relatives(arraI.getInt("id"), arraI.getString("name"), arraI.getString("type")));
                    }
                    Profile profile = new Profile();
                    profile.setRelatives(relatives);
                    profile.setFirstName(json.getString("first_name"));
                    profile.setLastName(json.getString("last_name"));
                    profile.setDate(json.getString("bdate"));
                    profile.setRelation(json.getInt("relation"));
                    profile.setCity(json.getJSONObject("city").getString("title"));
                    profile.setEducation(json.getJSONObject("education").getString("university_name"));
                    profile.setPhone(json.getJSONObject("contacts").getString("mobile_phone"));
                    profile.setHobbies(json.getString("interests"));
                    profile.setPhoto(json.getString("photo_100"));
                    profile.setSex(json.getInt("sex"));

                    user = new User(json.getInt("id"), askFriends(), profile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(VKError error) {
            }
            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
            }
        });
        return user;
    }

    public ArrayList<Friend> askFriends(){
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.COUNT, "count", VKApiConst.FIELDS, "id, online, first_name, last_name, bdate, city , relation, relatives, education, contacts, interests, sex, photo_100"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {

                JSONObject json = response.json;
                int friendsCount = ((VKList<VKApiUserFull>) response.parsedModel).getCount();

                try{
                    for (int i = 0; i < friendsCount; i++) {
                        JSONArray arrayRelatives = json.getJSONArray("relatives");
                        ArrayList<Relatives> relatives = new ArrayList<>();
                        for (int j = 0; j < arrayRelatives.length(); j++) {
                            JSONObject arraI = (JSONObject) arrayRelatives.get(i);
                            relatives.add(new Relatives(arraI.getInt("id"), arraI.getString("name"), arraI.getString("type")));
                        }

                        Profile profile = new Profile();
                        profile.setRelatives(relatives);
                        profile.setFirstName(json.getString("first_name"));
                        profile.setLastName(json.getString("last_name"));
                        profile.setDate(json.getString("bdate"));
                        profile.setRelation(json.getInt("relation"));
                        profile.setCity(json.getJSONObject("city").getString("title"));
                        profile.setEducation(json.getJSONObject("education").getString("university_name"));
                        profile.setPhone(json.getJSONObject("contacts").getString("mobile_phone"));
                        profile.setHobbies(json.getString("interests"));
                        profile.setPhoto(json.getString("photo_100"));
                        profile.setSex(json.getInt("sex"));

                        friends.add(new Friend(json.getInt("id"), json.getInt("count"), json.getBoolean("online"), profile));
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }
        });
        return friends;
    }



}



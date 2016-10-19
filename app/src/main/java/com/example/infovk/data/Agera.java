package com.example.infovk.data;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.util.concurrent.Executors.newSingleThreadExecutor;


public class Agera{
    private static final String TAG = Agera.class.getSimpleName();

    OkHttpClient client = new OkHttpClient();
    private Repository<Result<SimpleUser>> repository;

    public String findUrl() {
        VKRequest vkRequest = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name"));
        String url1 = "https://api.vk.com/method/users.get?";
        String url2;
        String url ="";
        try {
            url2 = vkRequest.getPreparedRequest().getQuery();
            url = url1 + url2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public Repository<Result<SimpleUser>> getRepository(){

        repository = Repositories.repositoryWithInitialValue(Result.<SimpleUser>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(newSingleThreadExecutor())
                .getFrom((Supplier<Response>) () -> {
                    Request request = new Request.Builder()
                            .url(findUrl())
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
                        // выделяю из строки JSON объект
                        JSONObject responseObject = new JSONObject(stringResponse);
                        Log.d(TAG, "responseObject = " + responseObject);
                        // выделяю из JSON объекта JSON массив
                        JSONArray arraySimleUser = responseObject.getJSONArray("response");
                        Log.d(TAG, "arraySimleUser = " + arraySimleUser);
                        JSONObject objectMe = arraySimleUser.getJSONObject(0);
                        Log.d(TAG, "objectMe = " + objectMe);
                        SimpleUser user = mapper.readValue(objectMe.toString(), SimpleUser.class);
                        Log.d(TAG, "first_name = " + user.getFirst_name());
                        return Result.absentIfNull(user);
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

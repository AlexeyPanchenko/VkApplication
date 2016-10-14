package com.example.infovk.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.agera.Function;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.httpClient.VKHttpClient;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.util.concurrent.Executors.newSingleThreadExecutor;


public class Agera{

    OkHttpClient client = new OkHttpClient();
    private Repository<Result<String>> repository;
    Updatable updatable;

    private String findUrl() {
        VKRequest vkRequest = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "first_name"));
        String url1 = "http://api.vk.com/method/users.get?";
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

    public Repository<Result<String>> getRepository(){

        repository = Repositories.repositoryWithInitialValue(Result.<String>absent())
                .observe()
                .onUpdatesPerLoop()
                .goTo(newSingleThreadExecutor())
                .getFrom((Supplier<Response>) () -> {
                    Request request = new Request.Builder()
                            .url(findUrl())
                            .build();
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return response;
                })
                .thenTransform(response -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Name name = new Name();
                    try {
                        String stringResponse = response.body().string();
                        name = mapper.readValue(stringResponse, Name.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Result.absentIfNull(name.getFirst_name());
                })
                .compile();

        return repository;
    }
    /*Подписаться на обновления addUpdatable!  определить метод update желательно в презенторе добавил для коммита*/


}

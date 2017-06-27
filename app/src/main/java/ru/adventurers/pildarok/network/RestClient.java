package ru.adventurers.pildarok.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Дмитрий on 26.11.2015.
 */
public enum RestClient {
    INSTANSE;
    private RestAdapter.Builder restAdapterBuilder;
    private RestAdapter.Builder fileAdapterBuilder;
    private RestAdapter.Builder socketAdapterBuilder;

    private RestAPI restApi;
    private RestAPI fileApi;
    private RestAPI socketApi;


    RestClient() {
        init();
    }

    public void init() {
        OkClient okHttpClient = new OkClient(new OkHttpClient());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        restAdapterBuilder = new RestAdapter.Builder()
                .setClient(okHttpClient)
                .setEndpoint(RestAPI.ENDPOINT_URL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("Retrofit", message);
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("content-type", "application/json");
                      //  request.addHeader("update-key", PrefUtils.getDeviceId(TravelTool.getAppContext()));

                    }
                })
               // .setConverter(new StringConverter())
                .setConverter(new GsonConverter(gson));



    }

    public RestAPI getRestApi() {
        return restApi;
    }




}

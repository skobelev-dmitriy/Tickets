package ru.adventurers.pildarok.network;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import ru.adventurers.pildarok.models.Login;

/**
 * Created by Дмитрий on 26.11.2015.
 */
public interface RestAPI {


        String ENDPOINT_URL = "http://wh.home-nadym.ru/api";


    /**
     * Login
     */
    @POST("WH_Users/login")
    void login(@Body Login login, Callback<Login> profile);






}

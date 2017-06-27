package ru.adventurers.pildarok.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Дмитрий on 26.11.2015.
 */
public class Login {
    @Expose
    private String username;
    @Expose
    private String deviceID;
    @Expose
    private String password;


    public Login() {}
    public Login(String username, String password, String deviceID) {
        this.username = username;
        this.deviceID = deviceID;
        this.password=password;
    }


}

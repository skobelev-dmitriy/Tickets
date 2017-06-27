package ru.adventurers.pildarok.models;
import com.google.gson.annotations.Expose;

/**
 * Created by Дмитрий on 26.11.2015.
 */

public class User {
    @Expose
   // @Column(name = "userId")
    private String userId;

    @Expose
   // @Column(name = "username")
    private String username;
    @Expose
   // @Column(name = "name")
    private String name;



    private String password;

    public User(){
       super();
    }

    public User(String userId, String username, String name){
        this.name=name;
        this.username=username;
        this.userId=userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}

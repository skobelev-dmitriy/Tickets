package ru.adventurers.pildarok.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.adventurers.pildarok.R;

/**
 * Created by Дмитрий on 09.02.2016.
 */
public class Ticket extends RealmObject {

    @Expose
    private String sha256;
    @Expose
    private String name;
    @Expose
    private String phone;
    @Expose
    private String email;
    @Expose
    private String date;
    @Expose
    private int sum;
    @Expose
    private int category;

    private boolean enter=false;

    public Ticket() {

    }


    public String getSha256() {
        return sha256;
    }

    public String getName() {
        return name;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public boolean isEnter() {
        return enter;
    }

    public void setEnter(boolean enter) {
        this.enter = enter;
    }

    public int getCategory() {
        return category;
    }
    public int getCategoryImage(){
        int resource=0;
        switch (category){
            case 2:
                resource= R.mipmap.ic_percent_black_48dp;
                break;
            case 0:
                resource= R.mipmap.ic_motorbike_black_48dp;
                break;
            case 1:
                resource= R.mipmap.ic_human_black_48dp;
                break;
        }

        return resource;
    }
}

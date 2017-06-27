package ru.adventurers.pildarok.data;


import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import com.google.gson.GsonBuilder;

import java.util.List;

import javax.inject.Inject;

import ru.adventurers.pildarok.Utils.FileToJsonUtil;
import ru.adventurers.pildarok.models.Ticket;
import ru.adventurers.pildarok.models.TicketResponse;
import rx.Observable;


public class DataManager {
    private final Realm realm;

    private final PreferencesHelper mPreferencesHelper;
    @Inject
    public DataManager(PreferencesHelper preferencesHelper) {

        mPreferencesHelper = preferencesHelper;
        realm=Realm.getDefaultInstance();
    }

    public Observable<TicketResponse> getTicketsFromJSon(Context context){
        return  Observable.just(new GsonBuilder().create().fromJson(FileToJsonUtil.loadJSONFromAsset(context, "tickets.json"), TicketResponse.class));
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Ticket getTicketInfo(String id){
        return realm.where(Ticket.class).equalTo("sha256",id).findFirst();

    }
    public void setTicketInfo(Ticket ticket){
        realm.beginTransaction();
        ticket.setEnter(true);
        realm.commitTransaction();
        realm.close();

    }
}

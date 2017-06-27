package ru.adventurers.pildarok.data;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import ru.adventurers.pildarok.injection.ApplicationContext;


public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android__pref_file";
    private static final String PREF_UPDATE_DATE = "PREF_UPDATE_DATE";



    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }


    public  void setAtmUpdateDate(String date){
        mPref.edit().putString(PREF_UPDATE_DATE, date).apply();
    }
    public String getAtmUpdateDate(){
        return mPref.getString(PREF_UPDATE_DATE, null);
    }


}

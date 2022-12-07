package com.example.bank_sampah_app;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager{

    private SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME="login";
    public static final String USER_TOKEN = "user_token";
    private  SharedPreferences.Editor editor;

    public SessionManager(Context context){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveAuthToken (String token){
        editor=sharedPreferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    public String fetchAuthToken(){
        return sharedPreferences.getString(USER_TOKEN, null);
    }

    public void deleteAuthToken(){
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}

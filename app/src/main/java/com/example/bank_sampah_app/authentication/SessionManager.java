package com.example.bank_sampah_app.authentication;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bank_sampah_app.User;

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
        editor.putBoolean("isUserLogin", true);
        editor.apply();
    }

    public String fetchAuthToken(){
        return sharedPreferences.getString(USER_TOKEN, null);
    }


    public void deleteAuthToken(){
        editor=sharedPreferences.edit();
        editor.clear();
        editor.putBoolean("isUserLogin", false);
        editor.apply();
    }

    public void saveUser(User user) {
        editor=sharedPreferences.edit();
        editor.putInt("id_user", user.getId_user());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putInt(("saldo"), user.getSaldo());
        editor.putString("foto", user.getFoto());
        editor.putString("alamat", user.getAlamat());
        editor.putString("jenis_kelamin", user.getJenis_kelamin());
        editor.putString("no_hp", user.getNo_hp());
        editor.putString("tgl_lahir", user.getTgl_lahir());
        editor.putBoolean("isSuccess",true);
        editor.apply();
    }

    public User fetchUser(){
        int id_user = sharedPreferences.getInt("id_user",-1);
        String name = sharedPreferences.getString("name",null);
        String email = sharedPreferences.getString("email",null);
        int saldo = sharedPreferences.getInt("saldo",-1);
        String foto = sharedPreferences.getString("foto",null);
        String alamat = sharedPreferences.getString("alamat",null);
        String jenis_kelamin = sharedPreferences.getString("jenis_kelamin",null);
        String no_hp = sharedPreferences.getString("no_hp",null);
        String tgl_lahir = sharedPreferences.getString("tgl_lahir",null);

        return new User(id_user,name, email, saldo, foto,alamat,jenis_kelamin,no_hp,tgl_lahir);
    }

}

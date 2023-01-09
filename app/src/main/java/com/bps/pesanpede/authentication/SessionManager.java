package com.bps.pesanpede.authentication;

import android.content.Context;
import android.content.SharedPreferences;

import com.bps.pesanpede.User;

public class SessionManager{

    private final SharedPreferences sharedPreferences;
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
        editor.apply();
    }

    public void saveUser(User user) {
        editor=sharedPreferences.edit();
        editor.putInt("id", user.getId_user());
        editor.putString("name", user.getName());
        editor.putString("no_buku", user.getNo_buku());
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
        int id = sharedPreferences.getInt("id",0);
        String no_buku = sharedPreferences.getString("no_buku",null);
        String name = sharedPreferences.getString("name",null);
        String email = sharedPreferences.getString("email",null);
        int saldo = sharedPreferences.getInt("saldo",0);
        String foto = sharedPreferences.getString("foto",null);
        String alamat = sharedPreferences.getString("alamat",null);
        String jenis_kelamin = sharedPreferences.getString("jenis_kelamin",null);
        String no_hp = sharedPreferences.getString("no_hp",null);
        String tgl_lahir = sharedPreferences.getString("tgl_lahir",null);

        return new User(id,name,no_buku,email,saldo,foto,alamat,jenis_kelamin,no_hp,tgl_lahir);
    }

}

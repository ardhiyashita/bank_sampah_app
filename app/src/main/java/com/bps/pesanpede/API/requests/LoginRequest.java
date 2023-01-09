package com.bps.pesanpede.API.requests;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("no_buku")
    private String username;

    @SerializedName("password")
    private String password;

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

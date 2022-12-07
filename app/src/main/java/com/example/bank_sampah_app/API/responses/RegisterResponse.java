package com.example.bank_sampah_app.API.responses;

import com.example.bank_sampah_app.User;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    private Boolean success;

    @SerializedName("message")
    private String message;

    private String token;

    private User user;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

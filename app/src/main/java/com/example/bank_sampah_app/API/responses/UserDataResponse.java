package com.example.bank_sampah_app.API.responses;

import com.example.bank_sampah_app.User;
import com.google.gson.annotations.SerializedName;

public class UserDataResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("user")
    private User user;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

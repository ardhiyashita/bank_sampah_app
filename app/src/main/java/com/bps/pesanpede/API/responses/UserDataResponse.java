package com.bps.pesanpede.API.responses;

import com.bps.pesanpede.User;
import com.google.gson.annotations.SerializedName;

public class UserDataResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("data")
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

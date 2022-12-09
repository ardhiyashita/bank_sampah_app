package com.example.bank_sampah_app.API.responses;

import com.google.gson.annotations.SerializedName;

public class PengajuanResponse {

    private Boolean success;

    @SerializedName("message")
    private String message;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
}

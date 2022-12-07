package com.example.bank_sampah_app.API.responses;

import com.example.bank_sampah_app.User;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RegisterResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private Message message;

    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private User user;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
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

    public class Message{
        public ArrayList<Object> getErrorInfo() {
            return errorInfo;
        }

        public void setErrorInfo(ArrayList<Object> errorInfo) {
            this.errorInfo = errorInfo;
        }

        public ArrayList<Object> errorInfo;
    }




}

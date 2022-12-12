package com.example.bank_sampah_app.API.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PengajuanResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("message")
    private Message message;

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

package com.bps.pesanpede.API.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class TarikResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private Message message;
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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

    public class Data{
        private int user_id;
        private int uang;
        private String catatan;
        private String tipe;
        private String updated_at;
        private String created_at;
        public int id;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getUang() {
            return uang;
        }

        public void setUang(int uang) {
            this.uang = uang;
        }

        public String getCatatan() {
            return catatan;
        }

        public void setCatatan(String catatan) {
            this.catatan = catatan;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
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

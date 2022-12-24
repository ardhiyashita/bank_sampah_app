package com.example.bank_sampah_app.API.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PengumumanResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DataPengumuman[] data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataPengumuman[] getData() {
        return data;
    }

    public void setData(DataPengumuman[] data) {
        this.data = data;
    }
}

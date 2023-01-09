package com.bps.pesanpede.API.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransaksiResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DataTransaksi[] data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataTransaksi[] getData() {
        return data;
    }

    public void setData(DataTransaksi[] data) {
        this.data = data;
    }

}

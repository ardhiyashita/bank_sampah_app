package com.bps.pesanpede.API.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PenarikanResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DataPenarikan[] data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public DataPenarikan[] getData() {
        return data;
    }

    public void setData(DataPenarikan[] data) {
        this.data = data;
    }
}

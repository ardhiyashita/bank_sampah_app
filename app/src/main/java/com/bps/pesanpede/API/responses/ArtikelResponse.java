package com.bps.pesanpede.API.responses;

import com.bps.pesanpede.artikel.Artikel;
import com.google.gson.annotations.SerializedName;

public class ArtikelResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("data")
    private Artikel[] data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Artikel[] getData() {
        return data;
    }

    public void setData(Artikel[] data) {
        this.data = data;
    }
}

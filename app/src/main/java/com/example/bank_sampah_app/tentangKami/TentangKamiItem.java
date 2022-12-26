package com.example.bank_sampah_app.tentangKami;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.bank_sampah_app.panduan.PanduanItem;

public class TentangKamiItem implements Parcelable{
    String tentang;
    String content;

    public TentangKamiItem(){

    }

    protected TentangKamiItem(Parcel in) {
        tentang = in.readString();
        content = in.readString();
    }

    public static final Parcelable.Creator<TentangKamiItem> CREATOR = new Parcelable.Creator<TentangKamiItem>() {
        @Override
        public TentangKamiItem createFromParcel(Parcel in) {
            return new TentangKamiItem(in);
        }

        @Override
        public TentangKamiItem[] newArray(int size) {
            return new TentangKamiItem[size];
        }
    };

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tentang);
        dest.writeString(content);
    }
}

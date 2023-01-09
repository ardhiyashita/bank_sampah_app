package com.bps.pesanpede.panduan;

import android.os.Parcel;
import android.os.Parcelable;

public class PanduanItem implements Parcelable {
    String panduan;
    String content;
    int gambar;

    public PanduanItem(){

    }

    protected PanduanItem(Parcel in) {
        panduan = in.readString();
        content = in.readString();
        gambar = in.readInt();
    }

    public static final Creator<PanduanItem> CREATOR = new Creator<PanduanItem>() {
        @Override
        public PanduanItem createFromParcel(Parcel in) {
            return new PanduanItem(in);
        }

        @Override
        public PanduanItem[] newArray(int size) {
            return new PanduanItem[size];
        }
    };

    public String getPanduan() {
        return panduan;
    }

    public void setPanduan(String panduan) {
        this.panduan = panduan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(panduan);
        dest.writeString(content);
        dest.writeInt(gambar);
    }
}

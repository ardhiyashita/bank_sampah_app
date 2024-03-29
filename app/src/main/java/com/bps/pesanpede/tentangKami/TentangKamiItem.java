package com.bps.pesanpede.tentangKami;

import android.os.Parcel;
import android.os.Parcelable;

public class TentangKamiItem implements Parcelable{
    String tentang;
    String content;

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    int logo;

    public TentangKamiItem(){

    }

    protected TentangKamiItem(Parcel in) {
        tentang = in.readString();
        content = in.readString();
        logo = in.readInt();
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
        dest.writeInt(logo);
    }
}

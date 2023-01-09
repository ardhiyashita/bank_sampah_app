package com.bps.pesanpede.help;

import android.os.Parcel;
import android.os.Parcelable;

public class HelpItem implements Parcelable{

    String question;

    public HelpItem(){

    }

    protected HelpItem(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    public static final Creator<HelpItem> CREATOR = new Creator<HelpItem>() {
        @Override
        public HelpItem createFromParcel(Parcel in) {
            return new HelpItem(in);
        }

        @Override
        public HelpItem[] newArray(int size) {
            return new HelpItem[size];
        }
    };

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
    }
}

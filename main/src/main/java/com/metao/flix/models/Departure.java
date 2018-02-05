package com.metao.flix.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by matio on 01/02/18.
 */

public class Departure implements Parcelable {

    public String line_code;

    public String direction;

    public DateTime datetime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.line_code);
        dest.writeString(this.direction);
        dest.writeParcelable(this.datetime, flags);
    }

    public Departure() {
    }

    private Departure(Parcel in) {
        this.line_code = in.readString();
        this.direction = in.readString();
        this.datetime = in.readParcelable(DateTime.class.getClassLoader());
    }

    public static final Parcelable.Creator<Departure> CREATOR = new Parcelable.Creator<Departure>() {
        @Override
        public Departure createFromParcel(Parcel source) {
            return new Departure(source);
        }

        @Override
        public Departure[] newArray(int size) {
            return new Departure[size];
        }
    };
}



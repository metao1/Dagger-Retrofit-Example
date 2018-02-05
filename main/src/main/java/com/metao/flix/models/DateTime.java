package com.metao.flix.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by matio on 01/02/18.
 */

public class DateTime implements Parcelable {

    public Long timestamp;

    public String tz;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.timestamp);
        dest.writeString(this.tz);
    }

    protected DateTime(Parcel in) {
        this.timestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.tz = in.readString();
    }

    public static final Creator<DateTime> CREATOR = new Creator<DateTime>() {
        @Override
        public DateTime createFromParcel(Parcel source) {
            return new DateTime(source);
        }

        @Override
        public DateTime[] newArray(int size) {
            return new DateTime[size];
        }
    };
}

package io.github.aoemerson.riapidevchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ribot implements Parcelable {

    public static final Parcelable.Creator<Ribot> CREATOR = new Parcelable.Creator<Ribot>() {
        @Override
        public Ribot createFromParcel(Parcel source) {
            return new Ribot(source);
        }

        @Override
        public Ribot[] newArray(int size) {
            return new Ribot[size];
        }
    };
    Profile profile;

    public Ribot() {

    }

    public Ribot(Profile profile) {
        this.profile = profile;
    }

    protected Ribot(Parcel in) {
        this.profile = in.readParcelable(Profile.class.getClassLoader());
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.profile, flags);
    }
}

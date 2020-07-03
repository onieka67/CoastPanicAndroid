package com.example.ta;

import android.os.Parcel;
import android.os.Parcelable;

public class DataUser implements Parcelable {
    private String iduser;
    private String idpass;

    public DataUser(){

    }

    public DataUser(String iduser, String idpass) {
        this.iduser = iduser;
        this.idpass = idpass;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdpass() {
        return idpass;
    }

    public void setIdpass(String idpass) {
        this.idpass = idpass;
    }

    protected DataUser(Parcel in) {
        iduser = in.readString();
        idpass = in.readString();
    }

    public static final Creator<DataUser> CREATOR = new Creator<DataUser>() {
        @Override
        public DataUser createFromParcel(Parcel in) {
            return new DataUser(in);
        }

        @Override
        public DataUser[] newArray(int size) {
            return new DataUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iduser);
        dest.writeString(idpass);
    }
}

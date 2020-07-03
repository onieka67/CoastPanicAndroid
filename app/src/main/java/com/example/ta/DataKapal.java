package com.example.ta;

import android.os.Parcel;
import android.os.Parcelable;

public class DataKapal implements Parcelable {
    String idkapal,idtipekapal,idpanjang,idlebar;
    String idimg;

    public DataKapal(){
    }

    public DataKapal(String idkapal, String idtipekapal, String idpanjang, String idlebar,String idimg) {
        this.idkapal = idkapal;
        this.idtipekapal = idtipekapal;
        this.idpanjang = idpanjang;
        this.idlebar = idlebar;
        this.idimg = idimg;
    }

    protected DataKapal(Parcel in) {
        idkapal = in.readString();
        idtipekapal = in.readString();
        idpanjang = in.readString();
        idlebar = in.readString();
        idimg = in.readString();
    }

    public static final Creator<DataKapal> CREATOR = new Creator<DataKapal>() {
        @Override
        public DataKapal createFromParcel(Parcel in) {
            return new DataKapal(in);
        }

        @Override
        public DataKapal[] newArray(int size) {
            return new DataKapal[size];
        }
    };

    public String getIdkapal() {
        return idkapal;
    }

    public void setIdkapal(String idkapal) {
        this.idkapal = idkapal;
    }

    public String getIdtipekapal() {
        return idtipekapal;
    }

    public void setIdtipekapal(String idtipekapal) {
        this.idtipekapal = idtipekapal;
    }

    public String getIdpanjang() {
        return idpanjang;
    }

    public void setIdpanjang(String idpanjang) {
        this.idpanjang = idpanjang;
    }

    public String getIdlebar() {
        return idlebar;
    }

    public void setIdlebar(String idlebar) {
        this.idlebar = idlebar;
    }

    public String getIdimg() {
        return idimg;
    }

    public void setIdimg(String idimg) {
        this.idimg = idimg;
    }

    public static Creator<DataKapal> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idkapal);
        dest.writeString(idtipekapal);
        dest.writeString(idpanjang);
        dest.writeString(idlebar);
        dest.writeString(idimg);
    }
}

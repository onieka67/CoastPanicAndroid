package com.example.ta;

import android.os.Parcel;
import android.os.Parcelable;

public class DataNotif implements Parcelable {
    private String idkapal;
    private String idlatitude;
    private String idlongitude;
    private String idstatus;
    private String idtime;

    public DataNotif() {

    }

    public DataNotif(String idkapal, String idlatitude, String idlongitude, String idstatus, String idtime) {
        this.idkapal = idkapal;
        this.idlatitude = idlatitude;
        this.idlongitude = idlongitude;
        this.idstatus = idstatus;
        this.idtime = idtime;
    }

    public String getIdkapal() {
        return idkapal;
    }

    public void setIdkapal(String idkapal) {
        this.idkapal = idkapal;
    }

    public String getIdlatitude() {
        return idlatitude;
    }

    public void setIdlatitude(String idlatitude) {
        this.idlatitude = idlatitude;
    }

    public String getIdlongitude() {
        return idlongitude;
    }

    public void setIdlongitude(String idlongitude) {
        this.idlongitude = idlongitude;
    }

    public String getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(String idstatus) {
        this.idstatus = idstatus;
    }

    public String getIdtime() {
        return idtime;
    }

    public void setIdtime(String idtime) {
        this.idtime = idtime;
    }

    public static Creator<DataMonitoring> getCREATOR() {
        return CREATOR;
    }

    protected DataNotif(Parcel in) {
        idkapal = in.readString();
        idlatitude = in.readString();
        idlongitude = in.readString();
        idstatus = in.readString();
        idtime = in.readString();
    }

    public static final Creator<DataMonitoring> CREATOR = new Creator<DataMonitoring>() {
        @Override
        public DataMonitoring createFromParcel(Parcel in) {
            return new DataMonitoring(in);
        }

        @Override
        public DataMonitoring[] newArray(int size) {
            return new DataMonitoring[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idkapal);
        dest.writeString(idlatitude);
        dest.writeString(idlongitude);
        dest.writeString(idstatus);
        dest.writeString(idtime);
    }
}
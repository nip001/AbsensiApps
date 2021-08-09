package com.batch9.absensiapps.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Absensi implements Parcelable {
    @SerializedName("id")
    private long id;
    @SerializedName("username")
    private String username;
    @SerializedName("tanggalMasuk")
    private String tanggalMasuk;
    @SerializedName("tanggalKeluar")
    private String tanggalKeluar;
    @SerializedName("dataFoto")
    private String dataFoto;
    @SerializedName("lokasiGps")
    private String lokasiGps;

    public Absensi(long id, String username, String tanggalMasuk, String tanggalKeluar, String dataFoto, String lokasiGps) {
        this.id = id;
        this.username = username;
        this.tanggalMasuk = tanggalMasuk;
        this.tanggalKeluar = tanggalKeluar;
        this.dataFoto = dataFoto;
        this.lokasiGps = lokasiGps;
    }
    public Absensi(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getDataFoto() {
        return dataFoto;
    }

    public void setDataFoto(String dataFoto) {
        this.dataFoto = dataFoto;
    }

    public String getLokasiGps() {
        return lokasiGps;
    }

    public void setLokasiGps(String lokasiGps) {
        this.lokasiGps = lokasiGps;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.username);
        dest.writeString(this.tanggalMasuk);
        dest.writeString(this.tanggalKeluar);
        dest.writeString(this.dataFoto);
        dest.writeString(this.lokasiGps);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.username = source.readString();
        this.tanggalMasuk = source.readString();
        this.tanggalKeluar = source.readString();
        this.dataFoto = source.readString();
        this.lokasiGps = source.readString();
    }

    protected Absensi(Parcel in) {
        this.id = in.readLong();
        this.username = in.readString();
        this.tanggalMasuk = in.readString();
        this.tanggalKeluar = in.readString();
        this.dataFoto = in.readString();
        this.lokasiGps = in.readString();
    }

    public static final Parcelable.Creator<Absensi> CREATOR = new Parcelable.Creator<Absensi>() {
        @Override
        public Absensi createFromParcel(Parcel source) {
            return new Absensi(source);
        }

        @Override
        public Absensi[] newArray(int size) {
            return new Absensi[size];
        }
    };
}

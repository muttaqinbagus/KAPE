package com.example.labinformatika;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelThread  {

    @SerializedName("idThread")
    @Expose
    private int idThread;

    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("namaMahasiswa")
    @Expose
    private String namaMahasiswa;

//    public ModelThread(int idThread, String judul, String keterangan, String namaMahasiswa ){
//        this.idThread = idThread;
//        this.judul = judul;
//        this.keterangan = keterangan;
//        this.namaMahasiswa = namaMahasiswa;
//    }

    public ModelThread(int idThread, String judul, String Keterangan, String namaMahasiswa) {
        this.idThread = idThread;
        this.judul = judul;
        this.keterangan = Keterangan;
        this.namaMahasiswa = namaMahasiswa;
    }


    public void setIdThread(int idThread){
        this.idThread = idThread;
    }

    public int getIdThread(){
        return idThread;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }
}

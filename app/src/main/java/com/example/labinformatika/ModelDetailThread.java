package com.example.labinformatika;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDetailThread {

    @SerializedName("idThread")
    @Expose
    private int idThread;

    @SerializedName("idMateri")
    @Expose
    private int idMateri;

    @SerializedName("idModulDosen")
    @Expose
    private int idModulDosen;

    @SerializedName("idPraktikum")
    @Expose
    private int idPraktikum;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("judulThread")
    @Expose
    private String judulThread;

    @SerializedName("ket")
    @Expose
    private String ket;

    @SerializedName("posted")
    @Expose
    private String posted;

    @SerializedName("namaMateri")
    @Expose
    private String namaMateri;

    @SerializedName("namaModulDosen")
    @Expose
    private String namaModulDosen;

    @SerializedName("namaPraktikum")
    @Expose
    private String namaPraktikum;

    @SerializedName("namaMahasiswa")
    @Expose
    private String namaMahasiswa;

    @SerializedName("username")
    @Expose
    private String username;

    public ModelDetailThread(String judulThread,  String namaMahasiswa, String username, String posted, String ket) {
        this.judulThread = judulThread;
        this.namaMahasiswa = namaMahasiswa;
        this.username = username;
        this.posted = posted;
        this.ket = ket;
    }

    public void setIdThread(int idThread) {
        this.idThread = idThread;
    }

    public int getIdThread() {
        return idThread;
    }

    public void setIdMateri(int idMateri) {
        this.idMateri = idMateri;
    }

    public int getIdMateri() {
        return idMateri;
    }

    public void setIdModulDosen(int idModulDosen) {
        this.idModulDosen = idModulDosen;
    }

    public int getIdModulDosen() {
        return idModulDosen;
    }

    public void setIdPraktikum(int idPraktikum) {
        this.idPraktikum = idPraktikum;
    }

    public int getIdPraktikum() {
        return idPraktikum;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setJudulThread(String judulThread) {
        this.judulThread = judulThread;
    }

    public String getJudulThread() {
        return judulThread;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getKet() {
        return ket;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getPosted() {
        return posted;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaModulDosen(String namaModulDosen) {
        this.namaModulDosen = namaModulDosen;
    }

    public String getNamaModulDosen() {
        return namaModulDosen;
    }

    public void setNamaPraktikum(String namaPraktikum) {
        this.namaPraktikum = namaPraktikum;
    }

    public String getNamaPraktikum() {
        return namaPraktikum;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

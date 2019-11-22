package com.example.labinformatika;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMateri {
    @SerializedName("idma")
    @Expose
    private int idMa;

    @SerializedName("idm")
    @Expose
    private int idM;

    @SerializedName("namaModul")
    @Expose
    private String namaModul;

    @SerializedName("namaMateri")
    @Expose
    private String namaMateri;

    @SerializedName("namaPraktikum")
    @Expose
    private String namaPraktikum;

    @SerializedName("tahun")
    @Expose
    private int tahun;

    @SerializedName("idMat")
    @Expose
    private String idMat;

    @SerializedName("id")
    @Expose
    private String id;



//    public ModelMateri (String namaMateri, int idMa, int idM, String namaModul, String namaPraktikum, int tahun, String idMat, String id) {
//        this.namaMateri = namaMateri;
//        this.idMa = idMa;
////        this.id = id;
////        this.idM = idM;
////        this.idMat = idMat;
////        this.namaModul = namaModul;
////        this.namaPraktikum = namaPraktikum;
////        this.tahun = tahun;
//    }

    public ModelMateri(String namaMateri, int idM) {
        this.namaMateri = namaMateri;
        this.idM = idM;
    }

    public void setIdMa(int idMa){ this.idMa = idMa; }

    public int getIdMa(){
        return idMa;
    }

    public void setIdM(int idM){ this.idM = idM; }

    public int getIdM(){
        return idM;
    }


    public void setNamaMateri(String namaMateri){
        this.namaMateri = namaMateri;
    }

    public String getNamaMateri(){
        return namaMateri;
    }
}

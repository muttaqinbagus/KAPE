package com.example.labinformatika;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelPraktikum {

    @SerializedName("namaPraktikum")
    @Expose
    private String namaPraktikum;

    @SerializedName("idPraktikum")
    @Expose
    private int idPraktikum;

    public ModelPraktikum( String namaPraktikum, int idPraktikum){
        this.namaPraktikum = namaPraktikum;
        this.idPraktikum = idPraktikum;
    }


    public String getNamaPraktikum() {
        return namaPraktikum;
    }

    public void setNamaPraktikum(String namaPraktikum) {
        this.namaPraktikum = namaPraktikum;
    }

    public Integer getidPraktikum() {
        return idPraktikum;
    }

    public void setIdPraktikum(Integer idPraktikum) {
        this.idPraktikum = idPraktikum;
    }
}

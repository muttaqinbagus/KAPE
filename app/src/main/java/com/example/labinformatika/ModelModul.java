package com.example.labinformatika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelModul {

    @SerializedName("idModul")
    @Expose
    private int idModul;

    @SerializedName("namaModul")
    @Expose
    private String namaModul;

    public ModelModul( String namaModul, int idModul){
        this.namaModul = namaModul;
        this.idModul = idModul;
    }

    public void setIdModul(int idModul){
        this.idModul = idModul;
    }

    public int getIdModul(){
        return idModul;
    }

    public void setNamaModul(String namaModul){
        this.namaModul = namaModul;
    }

    public String getNamaModul(){
        return namaModul;
    }

}

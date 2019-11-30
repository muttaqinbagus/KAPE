package com.example.labinformatika;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelKomen  {
    @SerializedName("idComment")
    @Expose
    private int idComment;

    @SerializedName("idThread")
    @Expose
    private int idThread;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("nama")
    @Expose
    private String nama;

    public ModelKomen (String nama, String comment, String created_at, String username){
        this.nama = nama;
        this.comment = comment;
        this.created_at = created_at;
        this.username = username;
//        this.user_id = user_id;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdThread(int idThread) {
        this.idThread = idThread;
    }

    public int getIdThread() {
        return idThread;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}

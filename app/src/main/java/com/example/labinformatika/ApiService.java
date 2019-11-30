package com.example.labinformatika;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("login-dosen")
    Call<ModelUser> getLoginData(@Field("username") String ussername, @Field("password") String password);

    @FormUrlEncoded
    @POST("dosen/thread")
    Call<List<ModelPraktikum>> getThreadPraktikum(@Field("username") String ussername);


    @GET("dosen/thread/{praktikum}")
    Call<List<ModelModul>> getThreadModul(@Path("praktikum") String praktikum);

    @GET("dosen/thread/modul/{modul}")
    Call<List<ModelMateri>> getThreadMateri(@Path("modul") String modul);

    @GET("dosen/thread/materi/{materi}")
    Call<List<ModelThread>> getThreadView(@Path("materi") String materi);

    @GET("dosen/thread/detail/{thread}")
    Call<ModelDetailThread> getThreadDetail(@Path("thread") String thread);

    @GET("dosen/thread/comment/dosen/{thread}")
    Call<List<ModelKomen>> getKomen(@Path("thread") String thread);

    @FormUrlEncoded
    @POST("dosen/comment/create/{thread}")
    Call<ModelKomen> addComent (@Field("comment") String comment, @Field("user_id") String user_id, @Path("thread") String thread);


}

package com.batch9.absensiapps.service;

import com.batch9.absensiapps.entity.Absensi;
import com.batch9.absensiapps.entity.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/user/")
    Call<User> getAll();

    @GET("/user/login")
    Call<Integer> login(@Query("username") String username,@Query("password") String password);

    @Multipart
    @POST("/absensi/absen")
    Call<Absensi> checkin(@Part MultipartBody.Part file, @Part("data") RequestBody data);

}

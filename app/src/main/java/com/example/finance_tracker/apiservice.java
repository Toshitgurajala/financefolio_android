package com.example.finance_tracker;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface apiservice {

    @POST("user/login")
    Call<ResponseBody> login(@Body loginbody loginRequest);

    @POST("user/register")
    Call<ResponseBody> register(@Body loginbody lb);

    @GET("user/{userid}")
    Call<ResponseBody> getusername(@Path("userid") String userid);

    @GET("user/fetchtransactions/{userid}")
    Call<transactionresponse> fetchtransactions(@Path("userid") String userid);

    @PUT("user/update/{userid}")
    Call<ResponseBody> update(@Path("userid") String userid, @Body updatebody body);

}

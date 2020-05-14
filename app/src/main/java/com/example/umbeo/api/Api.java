package com.example.umbeo.api;

import com.example.umbeo.response_data.LoginResponse;
import com.example.umbeo.response_data.forgetpassword_response;
import com.example.umbeo.response_data.user;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> Signup(
            @Field("name") String name,
            @Field("phone") String number,
            @Field("email") String mail,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("forgotPassword")
    Call<forgetpassword_response> forgetPassword(
            @Field("email") String email
    );

    @FormUrlEncoded
    @GET("resetPassword")
    Call<forgetpassword_response> resetPassword(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("logout")
    Call<forgetpassword_response> logOut(
    );

    @FormUrlEncoded
    @GET("me")
    Call<user> Me(

    );

}

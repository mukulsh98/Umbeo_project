package com.example.umbeo.api;

import com.example.umbeo.response_data.LoginResponse;
import com.example.umbeo.response_data.UpdateResponse;
import com.example.umbeo.response_data.forgetpassword_response;
import com.example.umbeo.response_data.loginrequest_response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

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
    Call<loginrequest_response> userLogin(
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

    @GET("me")
    Call<LoginResponse> getProfile(
            @Header("Authorization") String header
    );

    @FormUrlEncoded
    @POST("logout")
    Call<forgetpassword_response> logOut(
    );



    @PATCH("me")
    Call<UpdateResponse> updateName(
            @Header("Authorization") String header,
            @Field("name") String name
   );



    @POST("myShops")
    Call<forgetpassword_response> addShop(
            @Header("Authorization") String header,
            @Field("name") String name,
            @Field("address") String address,
            @Field("district") String district
    );


}

package com.example.umbeo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRetrofit {
    private static final String BASE_URL="https://umbeo-delivery-app.herokuapp.com/api/v1/shops/myShops";
    private static ProductRetrofit mInstance;
    private Retrofit retrofit;


    private ProductRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public   static synchronized ProductRetrofit getmInstance(){

        if(mInstance==null){
            mInstance=new ProductRetrofit();
        }
        return mInstance;
    }

    public Api getApi(){
        return  retrofit.create(Api.class);
    }


}

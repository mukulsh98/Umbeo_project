package com.example.umbeo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopretrofitClient {

    private static final String BASE_URL="https://umbeo-delivery-app.herokuapp.com/api/v1/shops/";
    private static ShopretrofitClient mInstance;
    private Retrofit retrofit;


    private ShopretrofitClient(){



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    }



    public static synchronized ShopretrofitClient getmInstance(){
        if(mInstance==null){
            mInstance=new ShopretrofitClient();
        }
        return mInstance;
    }


    public Api getApi(){
        return retrofit.create(Api.class);
    }
}


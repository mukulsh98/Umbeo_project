package com.example.umbeo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductretrofitClient {

    private static final String BASE_URL="https://umbeo-delivery-app.herokuapp.com/api/v1/";
    private static ProductretrofitClient mInstance;
    private Retrofit retrofit;


    private ProductretrofitClient(){



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



    }



    public static synchronized ProductretrofitClient getmInstance(){
        if(mInstance==null){
            mInstance=new ProductretrofitClient();
        }
        return mInstance;
    }


    public Api getApi(){
        return retrofit.create(Api.class);
    }
}


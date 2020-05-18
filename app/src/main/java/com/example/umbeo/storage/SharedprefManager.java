package com.example.umbeo.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.umbeo.response_data.shopKeeper;

public class SharedprefManager {

    private static final String SHARED_PREF_NAME="my_shared_preference";

    private static  SharedprefManager mInstance;
    private Context mCtx;

    private SharedprefManager(Context mCtx){
        this.mCtx=mCtx;
    }

    public static synchronized SharedprefManager getInstance(Context mCtx){
        if(mInstance == null){
            mInstance = new SharedprefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUser(shopKeeper user){


            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("id", user.getId());
            editor.putString("name", user.getName());
            editor.putString("number", user.getPhone());
            editor.putString("email", user.getEmail());


            editor.apply();

    }

    public void saveToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", token);

        editor.apply();
    }



    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (!(sharedPreferences.getString("id","nouser").matches("nouser"))){
            return true;
        }
        return false;
    }

    public shopKeeper getUser(){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        shopKeeper muser= new shopKeeper(
                sharedPreferences.getString("id","nouser"),
                sharedPreferences.getString("name",null),
                sharedPreferences.getString("number",null),
                sharedPreferences.getString("email",null)

        );

        return muser;
    }

    public String getToken(){

        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        String token= sharedPreferences.getString("token",null);

        return token;
    }

    public void clear(){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}

package com.example.umbeo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.umbeo.response_data.user;

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

    public void saveUser(user muser){

        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("id",muser.getId());
        editor.putString("name",muser.getName());
        editor.putString("number",muser.getPhone());
        editor.putString("email",muser.getEmail());

        editor.apply();

    }

    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (!(sharedPreferences.getString("id","nouser").matches("nouser"))){
            return true;
        }
        return false;
    }

    public user getUser(){
        SharedPreferences sharedPreferences= mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        
    }


}


package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.umbeo.R;
import com.example.umbeo.api.ShopretrofitClient;
import com.example.umbeo.response_data.shopKeeperDetailsFromdb;
import com.example.umbeo.storage.SharedprefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class shopShop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_shop);

        String id= SharedprefManager.getInstance(getApplicationContext()).getToken();
        String userid="Bearer "+id;

        Call<List<shopKeeperDetailsFromdb>> call = ShopretrofitClient
                .getmInstance()
                .getApi()
                .getAllshop(userid);

        call.enqueue(new Callback<List<shopKeeperDetailsFromdb>>() {
            @Override
            public void onResponse(Call<List<shopKeeperDetailsFromdb>> call, Response<List<shopKeeperDetailsFromdb>> response) {

                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<shopKeeperDetailsFromdb>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
            }
        });
    }
}

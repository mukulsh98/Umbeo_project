package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umbeo.R;
import com.example.umbeo.api.RetrofitClient;
import com.example.umbeo.response_data.LoginResponse;
import com.example.umbeo.response_data.shopKeeper;
import com.example.umbeo.storage.SharedprefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class userProfile extends AppCompatActivity {

    TextView name, no, mail,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name=(TextView)findViewById(R.id.name);
        no=(TextView)findViewById(R.id.email);
        mail=(TextView)findViewById(R.id.phone);
        id=(TextView)findViewById(R.id.profile);

        String token1= SharedprefManager.getInstance(getApplicationContext()).getToken();


        Call<LoginResponse> call = RetrofitClient
                .getmInstance()
                .getApi()
                .getProfile(token1);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if(response.code()==200){

                                LoginResponse loginResponse = response.body();
                                if(loginResponse.getStatus().toString().matches("success")){

                                    shopKeeper shop= loginResponse.getShopKeeper();

                                    String naam= shop.getName();
                                    String number= shop.getPhone();
                                    String email= shop.getEmail();
                                    String id1= shop.getId();

                                    name.setText(naam);
                                     no.setText(number);
                                     mail.setText(email);
                                     id.setText(id1);

                                }
                            }
                            else{

                                String s = response.errorBody().string();
                                JSONObject temp=new JSONObject(s);
                                Toast.makeText(getApplicationContext(),"Error: "+temp.get("message"),Toast.LENGTH_LONG).show();
                            }

                        }  catch (IOException | JSONException e) {
                            Toast.makeText(getApplicationContext(),"Error: "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}

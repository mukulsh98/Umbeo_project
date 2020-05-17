package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.umbeo.R;
import com.example.umbeo.api.Api;
import com.example.umbeo.api.ProductRetrofit;
import com.example.umbeo.response_data.forgetpassword_response;
import com.example.umbeo.storage.SharedprefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addShop extends AppCompatActivity {

    private String token;

    EditText sname,sadd,sdistrict;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://umbeo-delivery-app.herokuapp.com/api/v1/shops/myShops")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        Api userClient= retrofit.create(Api.class);


        send=(Button)findViewById(R.id.senddata);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.setEnabled(false);
                send_data();
                send.setEnabled(true);
            }
        });
    }

    private void send_data(){

        sname=(EditText)findViewById(R.id.productname);
        sadd=(EditText)findViewById(R.id.address);
        sdistrict=(EditText)findViewById(R.id.district);

        String name= sname.getText().toString();
        String add=sadd.getText().toString();
        String district= sdistrict.getText().toString();

        String id= SharedprefManager.getInstance(getApplicationContext()).getToken();
        String userid="Bearer "+id;

        if(name.isEmpty()){

            Toast.makeText(getApplicationContext(),"Please Enter a shop name",Toast.LENGTH_LONG).show();
            return;
        }
        if(add.isEmpty()){

            Toast.makeText(getApplicationContext(),"Please Enter Shop Address",Toast.LENGTH_LONG).show();
            return;
        }if(district.isEmpty()){

            Toast.makeText(getApplicationContext(),"Please Enter Shop District",Toast.LENGTH_LONG).show();
            return;
        }

        Call<forgetpassword_response> call= ProductRetrofit
                .getmInstance()
                .getApi()
                .addShop(userid,name,add,district);

        call.enqueue(new Callback<forgetpassword_response>() {
            @Override
            public void onResponse(Call<forgetpassword_response> call, final Response<forgetpassword_response> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            if(response.code()==201){
                                forgetpassword_response forget= response.body();
                                if(forget.getStatus().toString().matches("success")) {

                                    Toast.makeText(getApplicationContext(),"Shop Created",Toast.LENGTH_LONG).show();

                                    Intent i= new Intent(addShop.this, homescreen.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);



                                }
                            }

                            else {
                                String s = response.errorBody().string();
                                JSONObject temp=new JSONObject(s);
                                Toast.makeText(getApplicationContext(),"Error: "+temp.get("message"),Toast.LENGTH_LONG).show();

                            }
                        }  catch (IOException | JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<forgetpassword_response> call, Throwable t) {

            }
        });

    }
}

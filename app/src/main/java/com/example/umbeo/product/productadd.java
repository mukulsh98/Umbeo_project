package com.example.umbeo.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.umbeo.R;
import com.example.umbeo.activity.addShop;
import com.example.umbeo.activity.homescreen;
import com.example.umbeo.api.ProductretrofitClient;
import com.example.umbeo.response_data.forgetpassword_response;
import com.example.umbeo.storage.SharedprefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class productadd extends AppCompatActivity {


    EditText name,category,quan,price,shopName,desc;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productadd);

        send=(Button)findViewById(R.id.senddata);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dowork();
            }
        });


    }

    private void dowork(){

        name=(EditText)findViewById(R.id.productname);
        category=(EditText)findViewById(R.id.productcategory);
        quan=(EditText)findViewById(R.id.district);
        price=(EditText)findViewById(R.id.price);
        shopName=(EditText)findViewById(R.id.shopname);
        desc=(EditText)findViewById(R.id.description);

        String naam=name.getText().toString();
        String cat=category.getText().toString();
        String quantity=quan.getText().toString();
        String pric=price.getText().toString();
        String sName=shopName.getText().toString();
        String description= desc.getText().toString();
        String id= SharedprefManager.getInstance(getApplicationContext()).getToken();
        String userid="Bearer "+id;


        if(naam.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Product Name",Toast.LENGTH_LONG).show();
        }
        if(cat.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Product Category",Toast.LENGTH_LONG).show();
        }
        if(quantity.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Product Quantity",Toast.LENGTH_LONG).show();
        }
        if(pric.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Product Price",Toast.LENGTH_LONG).show();
        }
        if(sName.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Shop Name",Toast.LENGTH_LONG).show();
        }

        if(description.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Enter Product Description",Toast.LENGTH_LONG).show();
        }


        Call<forgetpassword_response> call = ProductretrofitClient
                .getmInstance()
                .getApi()
                .addProduct(userid,naam,cat,quantity,pric,description,sName);

        call.enqueue(new Callback<forgetpassword_response>() {
            @Override
            public void onResponse(Call<forgetpassword_response> call, Response<forgetpassword_response> response) {
                try {
                    if(response.isSuccessful()){

                        forgetpassword_response ans= response.body();

                        if(ans.getStatus().toString().matches("success")){

                            Toast.makeText(getApplicationContext(),"Product Created",Toast.LENGTH_LONG).show();

                            Intent i= new Intent(productadd.this, homescreen.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }

                        else{

                            Toast.makeText(getApplicationContext(),"Could not create product",Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        String s = response.errorBody().string();
                        JSONObject temp=new JSONObject(s);
                        Toast.makeText(getApplicationContext(),"Error: "+temp.get("message"),Toast.LENGTH_LONG).show();

                    }
                }
                catch (IOException | JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<forgetpassword_response> call, Throwable t) {

            }
        });



    }
}

package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umbeo.R;
import com.example.umbeo.api.RetrofitClient;
import com.example.umbeo.response_data.LoginResponse;
import com.example.umbeo.response_data.loginrequest_response;
import com.example.umbeo.storage.SharedprefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button signup,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=(Button)findViewById(R.id.button2);
        login=(Button)findViewById(R.id.button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, com.example.umbeo.activity.signup.class));
            }
        });


        final TextView forgotpassword=(TextView)findViewById(R.id.forget);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,forget_password.class));
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userlogin();

            }
        });
    }

    @Override
    protected void onStart(){

        super.onStart();

        // shopKeeper is already logged in...
        if (SharedprefManager.getInstance(this).isLoggedIn()){
            Intent i= new Intent(com.example.umbeo.activity.login.this,homescreen.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    private void userlogin(){
        EditText uname=(EditText)findViewById(R.id.username);
        EditText pass=(EditText)findViewById(R.id.pass);
        TextView err= (TextView) findViewById(R.id.status);

        login.setEnabled(false);
        String email = uname.getText().toString();
        String password= pass.getText().toString();

        if(email.isEmpty() && password.isEmpty()){
            err.setBackgroundColor(Color.parseColor("#f0f8ff"));
            err.setText("Please Enter Email and password");
            login.setEnabled(true);
            return;
        }

        if(email.isEmpty()){
            err.setBackgroundColor(Color.parseColor("#f0f8ff"));
            err.setText("Please Enter Email");
            login.setEnabled(true);
            return;
        }
        if(!(email.matches(emailPattern))){
            err.setBackgroundColor(Color.parseColor("#f0f8ff"));
            err.setText("Please Enter valid Email");
            login.setEnabled(true);
            return;
        }
        if(password.isEmpty()){
            err.setBackgroundColor(Color.parseColor("#f0f8ff"));
            login.setEnabled(true);
            err.setText("Please Enter a Password");
            return;
        }


        Call<loginrequest_response>call= RetrofitClient
                .getmInstance()
                .getApi()
                .userLogin(email,password);
        call.enqueue(new Callback<loginrequest_response>() {
            @Override
            public void onResponse(Call<loginrequest_response> call, final Response<loginrequest_response> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            if (response.code() == 200) {

                                loginrequest_response loginResponse= response.body();
                                if(loginResponse.getStatus().toString().matches("success")) {
                                    login.setEnabled(true);

                                    SharedprefManager.getInstance(login.this)
                                            .saveToken(loginResponse.getToken());

                                    Intent i= new Intent(com.example.umbeo.activity.login.this,com.example.umbeo.activity.homescreen.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);



                                }
                            }
                            else {
                                String s = response.errorBody().string();
                                JSONObject temp=new JSONObject(s);
                                Toast.makeText(getApplicationContext(),"Error: "+temp.get("message"),Toast.LENGTH_LONG).show();
                                login.setEnabled(true);
                            }
                        }
                        catch (IOException | JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                            login.setEnabled(true);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<loginrequest_response> call, Throwable t) {

            }
        });
    }
}

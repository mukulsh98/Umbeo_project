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
import com.example.umbeo.response_data.forgetpassword_response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resetpassword extends AppCompatActivity {

    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.setEnabled(false);
                dowork();
                send.setEnabled(true);
            }
        });
    }

    private void dowork(){
        EditText pass1=(EditText)findViewById(R.id.pass1);
        EditText pass2=(EditText)findViewById(R.id.pass2);
        TextView str= (TextView)findViewById(R.id.status);
        String password1=pass1.getText().toString();
        String password2=pass2.getText().toString();
        Intent i= getIntent();
        String email= i.getStringExtra("mail");

        if(password1.isEmpty() || password2.isEmpty()){
            str.setBackgroundColor(Color.parseColor("#f0f8ff"));
            str.setText("Please enter a password");
            send.setEnabled(true);
            return;
        }
        if(!(password1.matches(password2))){
            str.setBackgroundColor(Color.parseColor("#f0f8ff"));
            str.setText("Password does not match");
            send.setEnabled(true);
            return;
        }

        if(password1.matches(password2)){
            Call<forgetpassword_response> call= RetrofitClient
                    .getmInstance()
                    .getApi()
                    .resetPassword(email,password1);
            call.enqueue(new Callback<forgetpassword_response>() {
                @Override
                public void onResponse(Call<forgetpassword_response> call, final Response<forgetpassword_response> response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                if(response.code()==200){
                                    forgetpassword_response rep=response.body();
                                    if (rep.getStatus().matches("success")){
                                        Toast.makeText(resetpassword.this,"Password Reset Successfully",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(resetpassword.this,login.class));

                                    }
                                }
                                else{
                                    String s=response.errorBody().string();
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
        else{
            Toast.makeText(getApplicationContext(),"Password does not match",Toast.LENGTH_LONG).show();
        }

    }
}

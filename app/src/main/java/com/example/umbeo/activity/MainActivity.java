package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.umbeo.R;

public class MainActivity extends AppCompatActivity {

    private int SPLASH_TIME_OUT=2899;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}

package com.example.umbeo.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.umbeo.R;
import com.example.umbeo.api.RetrofitClient;
import com.example.umbeo.fragments.MyAccountfragment;
import com.example.umbeo.fragments.MyProductfragment;
import com.example.umbeo.fragments.Termsfragment;
import com.example.umbeo.response_data.forgetpassword_response;
import com.example.umbeo.storage.SharedprefManager;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homescreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(homescreen.this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(homescreen.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MyAccountfragment()).commit();
            navigationView.setCheckedItem(R.id.nav_myaccount);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_myaccount:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyAccountfragment()).commit();
                break;
            case R.id.nav_myproduct:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyProductfragment()).commit();
                break;

            case R.id.nav_terms:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Termsfragment()).commit();
                break;
            case R.id.signout:
                // write method here...
                dosignout();
                dosignout();

                break;
            case R.id.nav_share:
                Toast.makeText(getApplicationContext(),"Thank You for sharing",Toast.LENGTH_LONG).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void dosignout(){


        Call<forgetpassword_response> call= RetrofitClient
                .getmInstance()
                .getApi()
                .logOut();
        call.enqueue(new Callback<forgetpassword_response>() {
            @Override
            public void onResponse(Call<forgetpassword_response> call, final Response<forgetpassword_response> response) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try{
                            if (response.code()==200){

                                forgetpassword_response rep=response.body();
                                if(rep.getStatus().toString().matches("success")){

                                    SharedprefManager.getInstance(homescreen.this).clear();

                                    Intent intent= new Intent(homescreen.this,login.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }
                            else{
                                String s = response.errorBody().string();
                                JSONObject temp=new JSONObject(s);
                                Toast.makeText(homescreen.this,"Signout Failed",Toast.LENGTH_LONG).show();
                            }

                        }
                        catch (IOException | JSONException e) {
                            Toast.makeText(homescreen.this,"Error: "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
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

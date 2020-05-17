package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.umbeo.R;
import com.example.umbeo.api.RetrofitClient;
import com.example.umbeo.response_data.UpdateResponse;
import com.example.umbeo.response_data.shopKeeper;
import com.example.umbeo.storage.SharedprefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateName extends AppCompatActivity {

    EditText newname;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);



        submit=(Button)findViewById(R.id.update);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dowork();
            }
        });




    }

    private void dowork(){
        submit.setEnabled(false);
        newname=(EditText)findViewById(R.id.newname);
        final String name= newname.getText().toString();

        String id= SharedprefManager.getInstance(getApplicationContext()).getToken();
        String userid="Bearer "+id;

        Call<UpdateResponse> call= RetrofitClient
                .getmInstance()
                .getApi()
                .updateName(userid,name);

        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, final Response<UpdateResponse> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if(response.code() == 200){
                                UpdateResponse ans= response.body();
                                if(ans.getStatus().toString().matches("success")){

                                    Toast.makeText(getApplicationContext(),"User Name Updated",Toast.LENGTH_LONG).show();

                                    shopKeeper muser= SharedprefManager.getInstance(updateName.this).getUser();

                                    muser.setName(name);

                                    Intent intent =new Intent(updateName.this,homescreen.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);


                                }
                               else
                                   Toast.makeText(getApplicationContext(),"Couldn't update Name",Toast.LENGTH_LONG).show();
                            }
                            else{
                                String s = response.errorBody().string().toString();
                                JSONObject temp=new JSONObject(s);
                                Toast.makeText(getApplicationContext(),"Error: "+temp.get("message"),Toast.LENGTH_LONG).show();
                                submit.setEnabled(true);
                            }

                        }
                        catch (IOException | JSONException e) {
                            Toast.makeText(getApplicationContext(),"Error: "+e.getMessage().toString(),Toast.LENGTH_LONG).show();
                            submit.setEnabled(true);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                submit.setEnabled(true);
            }
        });
    }


}

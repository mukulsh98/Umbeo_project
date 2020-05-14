package com.example.umbeo.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.umbeo.R;
import com.example.umbeo.api.RetrofitClient;
import com.example.umbeo.response_data.user;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MyAccountfragment extends Fragment {

    TextView id,name,no,mail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_myaccount,container,false);

        id=(TextView) v.findViewById(R.id.id);
        name=(TextView) v.findViewById(R.id.name);
        no=(TextView) v.findViewById(R.id.phone);
        mail=(TextView) v.findViewById(R.id.email);



        Call<user> call= RetrofitClient
                .getmInstance()
                .getApi()
                .Me();

        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, final Response<user> response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            if (response.code() == 200) {

                                user user = response.body();
                                String nam = user.getName();
                                String i = user.getId();
                                String em = user.getEmail();
                                String numb = user.getPhone();

                                id.setText(i);
                                name.setText(nam);
                                no.setText(numb);
                                mail.setText(em);

                            } else {
                                String s = response.errorBody().string();
                                JSONObject temp = new JSONObject(s);
                                Toast.makeText(getActivity(), "Error: " + temp.get("message"), Toast.LENGTH_LONG).show();
                            }
                        }

                        catch (IOException | JSONException e) {
                            Toast.makeText(getActivity(), "Error: " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }

                    }

                });
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {

            }
        });

        return v;
    }

}

package com.example.umbeo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.umbeo.R;
import com.example.umbeo.activity.addShop;
import com.example.umbeo.activity.shopShop;
import com.example.umbeo.activity.updateName;
import com.example.umbeo.activity.userProfile;
import com.example.umbeo.response_data.shopKeeper;
import com.example.umbeo.storage.SharedprefManager;

public class MyAccountfragment extends Fragment {

    TextView id,name,no,mail,token;
    Button update,addshop,show,showshop;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myaccount, container, false);

       // id = (TextView) v.findViewById(R.id.id);

       // token=(TextView)v.findViewById(R.id.token);
       // shopKeeper muser= SharedprefManager.getInstance(getContext()).getUser();

       // String i= muser.getId();
       // String nam=muser.getName();
       // String phone= muser.getPhone();
       // String em= muser.getEmail();
       // String token1= SharedprefManager.getInstance(getContext()).getToken();
       // id.setText(i);
       // name.setText(nam);
       // no.setText(phone);
       // mail.setText(em);
       // token.setText(token1);

        update= (Button) v.findViewById(R.id.updatename);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), updateName.class));
            }
        });

        addshop=(Button) v.findViewById(R.id.addshop);
        addshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), addShop.class));
            }
        });

        show=(Button)v.findViewById(R.id.showprofile);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), userProfile.class));
            }
        });

        showshop=(Button)v.findViewById(R.id.showshop);
        showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), shopShop.class));
            }
        });
        return v;
    }

}

package com.example.umbeo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.umbeo.R;
import com.example.umbeo.activity.addProduct;

public class MyProductfragment extends Fragment {

    Button addpro, listpro;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_myproduct,container,false);

       addpro=(Button) v.findViewById(R.id.addproduct);
       listpro=(Button) v.findViewById(R.id.listproduct);

       addpro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getActivity(), addProduct.class));
           }
       });

       listpro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       return v;
    }
}

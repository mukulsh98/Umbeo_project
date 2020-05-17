package com.example.umbeo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.umbeo.R;

public class addProduct extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE_PERMISSION=1;
    private static final int REQUEST_CODE_SELECT_IMAGE=2;
    Button selectimage,send_data;
    EditText pname,pcategory,pquantity,pprice,pdescription;
    TextView setpath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        selectimage=(Button)findViewById(R.id.selectimage);
        selectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }
        });

        send_data=(Button)findViewById(R.id.addproduct);
        send_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });
    }

    private void uploadimage(){
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager())!=null){

            Uri selectedImage = intent.getData();
            setpath=(TextView)findViewById(R.id.setimagepath);

            setpath.setText(selectedImage.getEncodedPath());
        }
    }

    private void uploadData(){

        pname=(EditText)findViewById(R.id.productname);
        pcategory=(EditText)findViewById(R.id.productcategory);
        pquantity=(EditText)findViewById(R.id.quantity);
        pprice=(EditText)findViewById(R.id.price);
        pdescription=(EditText)findViewById(R.id.description);

        String name=pname.getText().toString();
        String category=pcategory.getText().toString();
        String quantity=pquantity.getText().toString();
        String price= pprice.getText().toString();
        String description=pdescription.getText().toString();



    }
}

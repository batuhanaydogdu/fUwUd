package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.impostors.fuwud.R;

public class OtherAddressesActivity extends AppCompatActivity {
    Button buttonSaveAddress;
    TextView textViewLongitude,textViewLatitude;
    Double currentLatitude=0.0,currentLongitude=0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_addresses);

        init();

        /*Query queryForLongtitude =databaseReference.child("restaurants").orderByKey();
        Query queryForLatitude=databaseReference.child("users").child(currentUser.getUid());*/
    }

    private void init(){
        textViewLongitude=findViewById(R.id.textViewLongitude);
        textViewLatitude=findViewById(R.id.textViewLatitude);
        buttonSaveAddress=findViewById(R.id.buttonSaveAddress);
    }






}
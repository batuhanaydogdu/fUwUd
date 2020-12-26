package com.impostors.fuwud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef=mDatabase.getReference();

        myRef.child("users").push().setValue(new User("mahmut","email@gmail.com"));
    }

}
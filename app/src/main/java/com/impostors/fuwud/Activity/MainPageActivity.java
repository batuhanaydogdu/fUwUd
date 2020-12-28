package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.impostors.fuwud.R;

import java.util.concurrent.TimeUnit;

public class MainPageActivity extends AppCompatActivity {
    private FirebaseAuth auth=FirebaseAuth.getInstance();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);





    }
}
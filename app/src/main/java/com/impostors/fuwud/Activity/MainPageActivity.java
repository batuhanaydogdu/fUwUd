package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.impostors.fuwud.R;

public class MainPageActivity extends AppCompatActivity {

    Button buttonSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        init();

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutClicked();
            }
        });
    }

    public void init(){
        buttonSignOut=findViewById(R.id.buttonSignOut);
    }

    public void signOutClicked(){
        FirebaseAuth.getInstance().signOut();
        Intent to_login_intent = new Intent(MainPageActivity.this, LoginActivity.class);
        startActivity(to_login_intent);
        finish();
    }
}
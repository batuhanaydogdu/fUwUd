package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.R;

public class OtherProfileActivity extends AppCompatActivity {

    private EditText editTextProfileName, editTextProfileSurname, editTextProfileBirth, editTextProfileEmail;
    private Button buttonChangePassword, buttonProfileUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        init();

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChangePasswordPage();
            }
        });

        buttonProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserInformation();
            }
        });
    }

    public void init() {
        editTextProfileName= findViewById(R.id.editTextProfileName);
        editTextProfileSurname= findViewById(R.id.editTextProfileSurname);
        editTextProfileBirth= findViewById(R.id.editTextProfileBirth);
        editTextProfileEmail= findViewById(R.id.editTextProfileEmail);
        buttonChangePassword= findViewById(R.id.buttonChangePassword);
        buttonProfileUpdate= findViewById(R.id.buttonProfileUpdate);
    }

    public void goToChangePasswordPage() {
        Intent intent = new Intent(OtherProfileActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateUserInformation(){
        //databaseden bilgi çekilecek
    }
}
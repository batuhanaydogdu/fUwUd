package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Model.User;
import com.impostors.fuwud.R;

public class LoginActivity extends AppCompatActivity {


    private EditText editTextEmail,editTextPassword;
    private Button buttonLogin;
    private Switch switchRemember;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();



    }

    public void init() {
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        buttonLogin =  findViewById(R.id.buttonLogin);
        switchRemember =  findViewById(R.id.switchRemember);
    }



}
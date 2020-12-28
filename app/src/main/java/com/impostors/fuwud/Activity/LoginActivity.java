package com.impostors.fuwud.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.impostors.fuwud.R;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private ProgressDialog progressDialog;
    private Switch emailRemember;
    private String rememberEmailKey = "switchEmail";


    private TextView register_link, txtForgotPassword;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    @Override
    public void onStart() {
        super.onStart();

        // Check auth on Activity start
        if (auth.getCurrentUser() != null) {
            onAuthSuccess(auth.getCurrentUser());
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClicked();
            }
        });

        /*register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotpasswordClicked();
            }
        });*/
    }

    public void init() {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
       /* register_link = findViewById(R.id.main_signup_button_text);
        txtForgotPassword = findViewById(R.id.main_text_forgot_password);*/



    }

    public void signInClicked() {
        final String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();


        /*if (emailRemember.isChecked()){ // Add Email To Storage
            addToSharedPreferences(email);//Remember Email
        }else{  // Clear Storage
            clearStorage(getSharedPreferences("EmailPref", MODE_PRIVATE), "", false);
        }*/

        //Password Combination Controls
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Email field cannot be empty!");
            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Password field cannot be empty!");
            }
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Password field cannot be empty!");
            return;
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    onAuthSuccess(task.getResult().getUser());
                } else {
                    Toast.makeText(LoginActivity.this, "Email or password is wrong!", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });
    }

    private void onAuthSuccess(FirebaseUser user){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging In...");
        progressDialog.setCancelable(false);
        Intent main_intent = new Intent(LoginActivity.this, MainPageActivity.class);
        progressDialog.show();
        startActivity(main_intent);
        finish(); // to stop Login Activity

    }






    private void clearStorage(SharedPreferences emailPref, String s, boolean b) {
        SharedPreferences sharedPreferences = emailPref;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", s);
        editor.putBoolean(rememberEmailKey, b);
        editor.apply();
    }

    public void addToSharedPreferences(String email) {
        clearStorage(getApplicationContext().getSharedPreferences("EmailPref", MODE_PRIVATE), email, emailRemember.isChecked());
    }


    /*public void signUpClicked(){
        Intent register_page_intent= new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(register_page_intent);
        finish();

    }

    public void forgotpasswordClicked(){
        Intent register_page_intent= new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(register_page_intent);
        finish();
    }*/
}
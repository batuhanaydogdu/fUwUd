package com.impostors.fuwud.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.impostors.fuwud.R;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonRegister, buttonRegisterRestaurant;
    private ProgressDialog progressDialog;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    @Override
    public void onStart() {
        super.onStart();

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
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });
        buttonRegisterRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent register_restaurant_intent = new Intent(LoginActivity.this, RestaurantRegistrationActivity.class);
                startActivity(register_restaurant_intent);*/
            }
        });
    }

    public void init() {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegisterRestaurant = findViewById(R.id.buttonRegisterRestaurant);
    }

    public void signInClicked() {
        final String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

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
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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

    private void onAuthSuccess(FirebaseUser user) {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging In...");
        progressDialog.setCancelable(false);
        Intent to_main_intent = new Intent(LoginActivity.this, MainPageActivity.class);
        progressDialog.show();
        startActivity(to_main_intent);
        finish();
    }

    public void signUpClicked() {
        Intent register_page_intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(register_page_intent);
    }
}
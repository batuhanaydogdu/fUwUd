package com.impostors.fuwud.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Model.User;
import com.impostors.fuwud.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextSurname, editTextName, editTextEmail, editTextPassword, editTextPhoneNumber;
    Button buttonSignUp;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });
    }

    public void init() {
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        editTextSurname = findViewById(R.id.editTextSurname);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonSignUp = findViewById(R.id.buttonSignUp);
    }


    public void signUpClicked() {
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String name = editTextName.getText().toString();
        final String surname = editTextSurname.getText().toString();
        final String phoneNumber = editTextPhoneNumber.getText().toString();

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        if (TextUtils.isEmpty(surname) || TextUtils.isEmpty(name)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
        } else if (password.length() < 6) {
            Toast.makeText(RegistrationActivity.this, "Password must be at least 6 character!", Toast.LENGTH_LONG).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(RegistrationActivity.this, "Enter a valid email!", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseUser = auth.getCurrentUser();
                                String user_id = firebaseUser.getUid();

                                databaseReference = FirebaseDatabase.getInstance().getReference();

                                User user = new User(email,name,surname,phoneNumber,user_id);
                                databaseReference.child("users").child(user_id).setValue(user);

                                progressDialog.dismiss();
                                Intent to_main_intent = new Intent(RegistrationActivity.this, MainPageActivity.class);
                                startActivity(to_main_intent);
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this, "You cannot register with this email or password.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }
}
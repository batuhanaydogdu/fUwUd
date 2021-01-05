package com.impostors.fuwud.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.impostors.fuwud.R;

public class MainPageActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment tempFragment;
    /*Button buttonSignOut;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        init();

       /* buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutClicked();
            }
        });*/
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentHolder,new FragmentOrder()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.actionOrder){
                    tempFragment = new FragmentOrder();
                }

                if(menuItem.getItemId() == R.id.actionSearch){
                    tempFragment = new FragmentSearch();
                }

                if(menuItem.getItemId() == R.id.actionBasket){
                    tempFragment = new FragmentBasket();
                }

                if(menuItem.getItemId() == R.id.actionOther){
                    tempFragment = new FragmentOther();
                    FirebaseAuth.getInstance().signOut();
                    Intent to_login_intent = new Intent(MainPageActivity.this, LoginActivity.class);
                    startActivity(to_login_intent);
                    finish();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder,tempFragment).commit();

                return true;
            }
        });
    }

    public void init() {
        /*buttonSignOut = findViewById(R.id.buttonSignOut);*/
        bottomNavigationView = findViewById(R.id.bottom_navigation);

    }

    /*public void signOutClicked() {
        FirebaseAuth.getInstance().signOut();
        Intent to_login_intent = new Intent(MainPageActivity.this, LoginActivity.class);
        startActivity(to_login_intent);
        finish();
    }*/
}
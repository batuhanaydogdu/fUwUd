package com.impostors.fuwud.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.impostors.fuwud.R;

public class RestaurantPanelActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restauran_panel);

        init();

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentHolder,new FragmentRestaurantMenu()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.actionEditMenu){
                    tempFragment = new FragmentRestaurantMenu();
                }

                if(menuItem.getItemId() == R.id.actionOrders){
                    tempFragment = new FragmentRestaurantOrders();
                }

                if(menuItem.getItemId() == R.id.actionComments){
                    tempFragment = new FragmentRestaurantComments();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder,tempFragment).commit();

                return true;
            }
        });
    }

    public void init() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
}
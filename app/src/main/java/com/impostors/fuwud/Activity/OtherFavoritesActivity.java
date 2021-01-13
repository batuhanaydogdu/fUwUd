package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Adapter.RVFavoriteAdapter;
import com.impostors.fuwud.Model.Restaurant;
import com.impostors.fuwud.R;

public class OtherFavoritesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RVFavoriteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_favorites);
        FirebaseAuth auth;
        final FirebaseUser currentUser;
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        recyclerView = findViewById(R.id.RVOtherFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Restaurant> options = new FirebaseRecyclerOptions.Builder<Restaurant>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid()).child("favorites"), Restaurant.class)
                .build();

        adapter = new RVFavoriteAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }
}
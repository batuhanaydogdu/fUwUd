package com.impostors.fuwud.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Adapter.RVSearchAdapter;
import com.impostors.fuwud.Model.Restaurant;
import com.impostors.fuwud.R;

public class FragmentSearch extends Fragment {
    RecyclerView recyclerView;
    RVSearchAdapter searchAdapter;
    ImageButton imageButtonPizza,imageButtonBurger,imageButtonDessert,imageButtonFish,imageButtonKebab;
    TextView textViewPizza,textViewBurger,textViewDessert,textViewFish,textViewKebab;




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);

        FirebaseDatabase firebaseDatabase;
        final DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("restaurants");

        recyclerView=view.findViewById(R.id.recyclersearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        imageButtonBurger=view.findViewById(R.id.imageButtonBurger);
        imageButtonDessert=view.findViewById(R.id.imageButtonDessert);
        imageButtonFish=view.findViewById(R.id.imageButtonFish);
        imageButtonKebab=view.findViewById(R.id.imageButtonKebab);
        imageButtonPizza=view.findViewById(R.id.imageButtonPizza);

        textViewBurger=view.findViewById(R.id.textViewBurger);

        imageButtonBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseRecyclerOptions<Restaurant> options = new FirebaseRecyclerOptions.Builder<Restaurant>()
                        .setQuery(databaseReference.orderByChild("cuisine").equalTo(textViewBurger.getText().toString()), Restaurant.class)
                        .build();
                searchAdapter=new RVSearchAdapter(options);
                recyclerView.setAdapter(searchAdapter);

            }
        });

        FirebaseRecyclerOptions<Restaurant> options = new FirebaseRecyclerOptions.Builder<Restaurant>()
                .setQuery(FirebaseDatabase.getInstance().getReference(), Restaurant.class)
                .build();
        searchAdapter=new RVSearchAdapter(options);
        recyclerView.setAdapter(searchAdapter);
        return view;
    }
    public void onStart() {
        super.onStart();
        searchAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        searchAdapter.stopListening();

    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item=menu.findItem(R.id.action_search);
        android.widget.SearchView searchView=(android.widget.SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRestaurant( query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchRestaurant( newText);
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);

    }



    private void searchRestaurant(String search) {
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        FirebaseRecyclerOptions<Restaurant> options = new FirebaseRecyclerOptions.Builder<Restaurant>()
                .setQuery(databaseReference.child("restaurants").orderByChild("restaurantName").startAt(search).endAt(search + "\uf8ff"), Restaurant.class)
                .build();
        searchAdapter = new RVSearchAdapter(options);
        searchAdapter.startListening();
        recyclerView.setAdapter(searchAdapter);
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }








}
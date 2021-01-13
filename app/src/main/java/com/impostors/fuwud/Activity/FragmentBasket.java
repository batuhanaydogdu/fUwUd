package com.impostors.fuwud.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.impostors.fuwud.Adapter.RVBasketAdapter;
import com.impostors.fuwud.Adapter.RVRDMenuAdapter;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

public class FragmentBasket extends Fragment {

    RecyclerView recyclerViewBasket;
    private RVBasketAdapter rvBasketAdapter;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_basket, container, false);
        init(view);

        Query queryForR=databaseReference.child("users").child(currentUser.getUid()).child("currentBasket");

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(queryForR, Product.class)
                .build();

        rvBasketAdapter = new RVBasketAdapter(options);
        recyclerViewBasket.setAdapter(rvBasketAdapter);

        return view;
    }

    private void init(View view){
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        recyclerViewBasket=view.findViewById(R.id.RecyclerViewBasket);
        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    public void onStart() {
        super.onStart();
        rvBasketAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        rvBasketAdapter.stopListening();
    }
}

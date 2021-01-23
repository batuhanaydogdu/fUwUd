package com.impostors.fuwud.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.impostors.fuwud.Adapter.RVRDMenuAdapter;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

public class FragmentRDMenu extends Fragment {

    RecyclerView recyclerViewRDMenu;
    private RVRDMenuAdapter rvRdAdapter;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_r_d_menu, container, false);

        init(view);

        String restaurantId=getActivity().getIntent().getStringExtra("restaurant_id");

        Query queryForR=databaseReference.child("restaurants").child(restaurantId).child("products");
        //adapter bağlantısı, FirebaseRecyclerOptions özel yapısı
        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(queryForR, Product.class)
                .build();

        rvRdAdapter = new RVRDMenuAdapter(options,restaurantId,getContext());
        recyclerViewRDMenu.setAdapter(rvRdAdapter);

        return view;
    }

    private void init(View view){
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        recyclerViewRDMenu=view.findViewById(R.id.recyclerViewRDMenu);
        recyclerViewRDMenu.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    //adapter için start, stop
    public void onStart() {
        super.onStart();
        rvRdAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        rvRdAdapter.stopListening();

    }
}
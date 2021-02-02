package com.impostors.fuwud.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
    TextView textViewTotalPrice;
    Button buttonBasketComplete;
    double totalPricee;
    String restaurantId;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_basket, container, false);
        init(view);
        Query queryForR=databaseReference.child("users").child(currentUser.getUid()).child("currentBasket");

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(queryForR, Product.class)
                .build();
        updateTotalPrice(queryForR);




        rvBasketAdapter = new RVBasketAdapter(options);



        recyclerViewBasket.setAdapter(rvBasketAdapter);


        buttonBasketComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalPricee==0){
                    Toast.makeText(getContext(),"krdşm 0 TL lik sepeti alamazsın",Toast.LENGTH_LONG);
                }
                else{
                    if(restaurantId!=null){

                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                intent.putExtra("price",totalPricee);
                intent.putExtra("restaurantId",restaurantId);
                startActivity(intent);
                }
                    else{
                        Toast.makeText(getContext(),"krdşm Restaurant bulunamadı",Toast.LENGTH_LONG);

                    }

                }
            }
        });

        return view;

    }

    private void updateTotalPrice(Query queryForR){
        queryForR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double totalPrice=0;
                for(DataSnapshot d:snapshot.getChildren()){
                    Product product=d.getValue(Product.class);
                    totalPrice=product.getCount()*product.getBuyPrice()+totalPrice;
                    restaurantId=product.getRestaurant_id();


                }
                textViewTotalPrice.setText(totalPrice+ "");
                totalPricee=totalPrice;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.delete_all_menu, menu);
        MenuItem item = menu.findItem(R.id.action_delete);
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void init(View view){
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        recyclerViewBasket=view.findViewById(R.id.RecyclerViewBasket);
        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(getContext()));
        textViewTotalPrice=view.findViewById(R.id.textViewTotalPrice);
        buttonBasketComplete=view.findViewById(R.id.buttonBasketComplete);


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

package com.impostors.fuwud.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;


public class RVBasketAdapter extends FirebaseRecyclerAdapter<Product,RVBasketAdapter.RVBasketHolder> {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public RVBasketAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {

            super(options);
            }

    @Override
    protected void onBindViewHolder(@NonNull RVBasketHolder rvBasketHolder, int i, @NonNull Product product) {
        rvBasketHolder.textViewProductNameB.setText(product.getName());
        rvBasketHolder.textViewProductPriceB.setText(product.getBuyPrice()+"");


    }

    @NonNull
    @Override
    public RVBasketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_basket, parent, false);
        return new RVBasketAdapter.RVBasketHolder(itemView);

    }


    public class RVBasketHolder extends RecyclerView.ViewHolder {

        public TextView textViewProductNameB;
        public TextView textViewProductPriceB;
        public ImageButton imageButtonDeleteFromBasket;


        //Constructor
        public RVBasketHolder(final View view) {
            super(view);

            auth = FirebaseAuth.getInstance();
            currentUser = auth.getCurrentUser();
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference();


            textViewProductPriceB = view.findViewById(R.id.textViewProductPriceB);
            textViewProductNameB = view.findViewById(R.id.textViewProductNameB);
            imageButtonDeleteFromBasket =  view.findViewById(R.id.imageButtonDeleteFromBasket);
            imageButtonDeleteFromBasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getRef(getAdapterPosition()).removeValue();




                }
            });



        }
    }
}


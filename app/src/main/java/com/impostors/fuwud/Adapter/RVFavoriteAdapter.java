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
import com.impostors.fuwud.Model.Restaurant;
import com.impostors.fuwud.R;

public class RVFavoriteAdapter extends FirebaseRecyclerAdapter<Restaurant,RVFavoriteAdapter.myviewHolder> {


    public RVFavoriteAdapter(@NonNull FirebaseRecyclerOptions<Restaurant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder myviewHolder, int i, @NonNull Restaurant restaurant) {
        myviewHolder.restaurantName.setText(restaurant.getRestaurantName());
        myviewHolder.restaurantCuisine.setText(restaurant.getCuisine());
        myviewHolder.restaurantPhone.setText(restaurant.getPhoneNumber());


    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_favorite,parent,false);
        return  new myviewHolder(view);
    }

    class myviewHolder extends RecyclerView.ViewHolder{
            TextView restaurantName,restaurantPhone,restaurantCuisine;
            ImageButton deleteFavorite;
            FirebaseDatabase firebaseDatabase;
            DatabaseReference databaseReference;


        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            deleteFavorite =itemView.findViewById(R.id.buttonDeleteFavorite);
            restaurantCuisine=itemView.findViewById(R.id.textViewSearchedCuisine);
            restaurantName=itemView.findViewById(R.id.textViewSearchedName);
            restaurantPhone=itemView.findViewById(R.id.textViewSearchedPhoneNumber);

            deleteFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getRef(getAdapterPosition()).removeValue();


                }
            });
        }


    }




}

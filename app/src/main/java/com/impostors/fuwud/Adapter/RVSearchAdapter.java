package com.impostors.fuwud.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.impostors.fuwud.Model.Restaurant;
import com.impostors.fuwud.R;

public class RVSearchAdapter extends FirebaseRecyclerAdapter<Restaurant,RVSearchAdapter.myviewholder> {

    public RVSearchAdapter(@NonNull FirebaseRecyclerOptions<Restaurant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull Restaurant restaurant) {
        myviewholder.searchedName.setText(restaurant.getRestaurantName());
        myviewholder.searchedPhone.setText(restaurant.getPhoneNumber());
        myviewholder.searchedEmail.setText(restaurant.getEmail());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView searchedName,searchedEmail,searchedPhone;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            searchedName=itemView.findViewById(R.id.textViewSearchedName);
            searchedEmail=itemView.findViewById(R.id.textViewSearchedEmail);
            searchedPhone=itemView.findViewById(R.id.textViewSearchedPhoneNumber);
        }
    }

}

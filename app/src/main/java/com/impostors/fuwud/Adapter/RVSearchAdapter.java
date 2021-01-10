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

public class RVSearchAdapter extends FirebaseRecyclerAdapter<Restaurant,RVSearchAdapter.ViewHolderForSearch> {

    public RVSearchAdapter(@NonNull FirebaseRecyclerOptions<Restaurant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderForSearch viewHolderForSearch, int i, @NonNull Restaurant restaurant) {
        viewHolderForSearch.textViewSearchedName.setText(restaurant.getRestaurantName());
        viewHolderForSearch.textViewSearchedPhoneNumber.setText(restaurant.getPhoneNumber());
        viewHolderForSearch.textViewSearchedCuisine.setText(restaurant.getCuisine());

    }

    @NonNull
    @Override
    public ViewHolderForSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_search,parent,false);
        return new ViewHolderForSearch(view);
    }

    class ViewHolderForSearch extends RecyclerView.ViewHolder {
        TextView textViewSearchedName,textViewSearchedCuisine,textViewSearchedPhoneNumber;
        public ViewHolderForSearch(@NonNull View itemView) {
            super(itemView);
            textViewSearchedName=itemView.findViewById(R.id.textViewSearchedName);
            textViewSearchedCuisine=itemView.findViewById(R.id.textViewSearchedCuisine);
            textViewSearchedPhoneNumber=itemView.findViewById(R.id.textViewSearchedPhoneNumber);
        }
    }

}

package com.impostors.fuwud.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.impostors.fuwud.Model.Restaurant;
import com.impostors.fuwud.R;

import java.util.List;

public class RVRestaurantAdapter extends RecyclerView.Adapter<RVRestaurantAdapter.ViewHolderForRestaurant> {
    private List<Restaurant> listOfRestaurants;
    private Context mContext;

    public RVRestaurantAdapter(Context mContext, List<Restaurant> listOfRestaurants) {
        this.mContext = mContext;
        this.listOfRestaurants = listOfRestaurants;
        Log.e("listofrest",listOfRestaurants.toString());
    }

    @NonNull
    @Override
    public ViewHolderForRestaurant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_restaurant_layout,parent,false);

        return new RVRestaurantAdapter.ViewHolderForRestaurant(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForRestaurant holder, int position) {

        final Restaurant restaurant=listOfRestaurants.get(position);
        holder.restaurantName.setText(restaurant.getRestaurantName());
        holder.email.setText(restaurant.getEmail());
        holder.phoneNumber.setText(restaurant.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return listOfRestaurants.size();
    }

    public void setListOfRestaurants(List<Restaurant> listOfRestaurants) {
        this.listOfRestaurants = listOfRestaurants;
    }

    /*
    @Override
    protected void onBindViewHolder(@NonNull ViewHolderForRestaurant viewHolderForRestaurant, int i, @NonNull Restaurant restaurant) {
        viewHolderForRestaurant.restaurantName.setText(restaurant.getRestaurantName());
        viewHolderForRestaurant.email.setText(restaurant.getEmail());
        viewHolderForRestaurant.phoneNumber.setText(restaurant.getPhoneNumber());
    }

    @NonNull
    @Override
    public ViewHolderForRestaurant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_restaurant_layout,parent,false);
        return new ViewHolderForRestaurant(view);
    }*/

    class ViewHolderForRestaurant extends RecyclerView.ViewHolder{
    TextView email,phoneNumber,restaurantName;


    public ViewHolderForRestaurant(@NonNull View itemView){
        super(itemView);
        email=itemView.findViewById(R.id.emailRV);
        phoneNumber=itemView.findViewById(R.id.phoneNumberRV);
        restaurantName=itemView.findViewById(R.id.restaurantNameRV);

    }

    }




}

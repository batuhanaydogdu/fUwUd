package com.impostors.fuwud.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.impostors.fuwud.Model.PrevOrder;

import com.impostors.fuwud.R;

public class OrderAdapter extends FirebaseRecyclerAdapter<PrevOrder, OrderAdapter.viewholder> {

    public OrderAdapter(@NonNull FirebaseRecyclerOptions<PrevOrder> options) {
        super(options);


    }

    TextView Date1;
    TextView productPrice1;
    TextView restaurantName1;


    @Override
    protected void onBindViewHolder(@NonNull viewholder viewholder, int i, @NonNull PrevOrder prevOrder) {

        viewholder.restaurantName1.setText(prevOrder.getRestaurantName());
        viewholder.productPrice1.setText(prevOrder.getPrice()+ " ");
        viewholder.Date1.setText(prevOrder.getDate().toString());


    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cart,parent,false);
        return new viewholder(view);
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView Date1,productPrice1,restaurantName1;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            Date1= (TextView)itemView.findViewById(R.id.Date1);
            productPrice1 = (TextView)itemView.findViewById(R.id.productPrice1);
            restaurantName1 = (TextView)itemView.findViewById((R.id.restaurantName1));

        }
    }
}

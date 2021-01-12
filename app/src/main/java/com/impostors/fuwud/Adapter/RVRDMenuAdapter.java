package com.impostors.fuwud.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.impostors.fuwud.Activity.MainPageActivity;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

import java.util.HashMap;
import java.util.Map;

public class RVRDMenuAdapter extends FirebaseRecyclerAdapter<Product,RVRDMenuAdapter.RDcardMenuItemHolder> {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private String restaurant_id;
    private Boolean flag;
    Context context;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;




    public RVRDMenuAdapter(@NonNull FirebaseRecyclerOptions<Product> options,String restaurant_id, Context context) {

        super(options);
        this.restaurant_id=restaurant_id;
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull RDcardMenuItemHolder rDcardMenuItemHolder, int i, @NonNull Product product) {
        //her itemi setleme
        rDcardMenuItemHolder.textViewProductRDName.setText(product.getName());
        rDcardMenuItemHolder.textViewProductRDPrice.setText(product.getBuyPrice()+"");

    }

    @NonNull
    @Override
    public RVRDMenuAdapter.RDcardMenuItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //card bağlantısı
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_restaurant_menu_item, parent, false);
        return new RVRDMenuAdapter.RDcardMenuItemHolder(itemView);
    }




    public class RDcardMenuItemHolder extends RecyclerView.ViewHolder {

        public TextView textViewProductRDName;
        public TextView textViewProductRDPrice;
        public ImageButton imageButtonRDBuy;
        public CardView cardView;

        //Constructor
        public RDcardMenuItemHolder(final View view) {
            super(view);

            cardView = view.findViewById(R.id.CardView);
            textViewProductRDPrice = (TextView) view.findViewById(R.id.textViewProductRDPrice);
            textViewProductRDName = (TextView) view.findViewById(R.id.textViewProductRDName);
            imageButtonRDBuy = (ImageButton) view.findViewById(R.id.imageButtonRDBuy);

            imageButtonRDBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                MainPageActivity.addToBasket(getItem(getAdapterPosition()));
                    addToBasket(getItem(getAdapterPosition()));

                }
            });


        }


    }
    private void addToBasket(final Product product){
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        flag = true;
        Query queryForCheck=databaseReference.child("users").child(currentUser.getUid()).child("currentBasket");
        queryForCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    Product p=d.getValue(Product.class);
                    Log.e("aa",p.toString());
                    if(!p.getRestaurant_id().equals(restaurant_id)){
                        flag =false;
                        Log.e("bb","bb");
                    }

                }
                if(flag) {
                    databaseReference.child("users").child(currentUser.getUid()).child("currentBasket").push().setValue(product);
                }
                else{
                    Toast.makeText(context, "Sadece 1 restauranttan sipariş verebilirsin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });











    }

}

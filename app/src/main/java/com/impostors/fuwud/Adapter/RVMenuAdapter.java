package com.impostors.fuwud.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RVMenuAdapter extends FirebaseRecyclerAdapter<Product,RVMenuAdapter.cardMenuItemHolder> {
    private Context context;

    public RVMenuAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @Override
    public Product getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public cardMenuItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.update_alert, parent, false);
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_layout, parent, false);
        return new cardMenuItemHolder(itemView);
    }


    @Override
    protected void onBindViewHolder(@NonNull cardMenuItemHolder cardMenuItemHolder, int i, @NonNull Product product) {
        cardMenuItemHolder.productName.setText((product.name));
        cardMenuItemHolder.productPrice.setText(String.valueOf(product.buyPrice));
    }

    public class cardMenuItemHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public CardView CardView;
        public TextView productPrice;
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;


        public cardMenuItemHolder(final View view) {
            super(view);
            productPrice = view.findViewById(R.id.productPrice);
            productName = view.findViewById(R.id.productName);
            buttonEdit=view.findViewById(R.id.buttonEdit);
            CardView = view.findViewById(R.id.CardView);
            buttonDelete=view.findViewById(R.id.buttonDelete);
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference();

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view1=view.inflate(context,R.layout.insertion_alert,null);
                AlertDialog.Builder ad=new AlertDialog.Builder(context);
                ad.setMessage("Edit");
                ad.setTitle("Edit product name");
                final EditText UpdatedProductName=view1.findViewById(R.id.editTextUpdateName);
                final EditText UpdatedProductPrice=view1.findViewById(R.id.editTextUpdatePrice);
                ad.setView(view1);
                ad.setPositiveButton("Update", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                     Query query=databaseReference.child("products").orderByChild("name").equalTo(getItem(getAdapterPosition()).getName().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.e("msg",getItem(getAdapterPosition()).getName());

                            for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                                Log.e("msg","ssssssss");
                                final Map<String,Object> updateMap=new HashMap<>();
                                updateMap.put("name",UpdatedProductName.getText().toString());
                                appleSnapshot.getRef().updateChildren(updateMap);
                                Log.e("msg",appleSnapshot.getRef().toString());

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.create().show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Product a =getItem(getAdapterPosition());
                a.getName();
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference();
                Query query = databaseReference.child("products").orderByChild("name").equalTo(a.getName());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });


        }


    }
}

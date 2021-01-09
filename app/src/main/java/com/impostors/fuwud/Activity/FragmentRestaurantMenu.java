package com.impostors.fuwud.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.impostors.fuwud.Adapter.RVMenuAdapter;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FragmentRestaurantMenu extends Fragment  {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Activity context;
    private RecyclerView recyclerView;
    private RVMenuAdapter adapter;
    private Button buttonInsert;
    private Button buttonEdit;
    private Button buttonDelete;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_panel_menu, container, false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("products");
        context=getActivity();
        buttonInsert=view.findViewById(R.id.buttonInsert);
        recyclerView=view.findViewById(R.id.rvmenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        buttonEdit=view.findViewById(R.id.buttonEdit);
        buttonDelete=view.findViewById(R.id.buttonDelete);


        /*buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("msg","HADı BAKALAIIIM MUCıZE");
                View editTextAlertUpdate = getLayoutInflater().inflate(R.layout.update_alert,null);
                AlertDialog.Builder ad=new AlertDialog.Builder(context);
                ad.setMessage("Update product");
                ad.setTitle("Update product name and price");
                final EditText updatedProductName=editTextAlertUpdate.findViewById(R.id.editTextUpdateName);
                final EditText UpdatedPrice=editTextAlertUpdate.findViewById(R.id.editTextUpdatePrice);

                ad.setView(editTextAlertUpdate);
                ad.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String productName=updatedProductName.getText().toString();
                        final double productPrice=Double.parseDouble(UpdatedPrice.getText().toString());

                        Map<String,Object> updateInfo=new HashMap<>();
                        updateInfo.put("name",productName);
                        updateInfo.put("buyPrice",productPrice);


                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.create().show();




            }
        });*/


        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View editTextAlert = getLayoutInflater().inflate(R.layout.insertion_alert,null);
                AlertDialog.Builder ad=new AlertDialog.Builder(context);
                ad.setMessage("Name of the new product");
                ad.setTitle("Add new product");

                final EditText InsertedProductName=editTextAlert.findViewById(R.id.editTextInsertedProductName);
                final EditText InsertedPrice=editTextAlert.findViewById(R.id.editTextTextPrice);

                ad.setView(editTextAlert);

                ad.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String productName = InsertedProductName.getText().toString();
                        final String productPriceCheck=InsertedPrice.getText().toString();


                        if(TextUtils.isEmpty(productName) || TextUtils.isEmpty(productPriceCheck)) {
                            Toast.makeText(context, "Enter required fields", Toast.LENGTH_LONG).show();
                        }
                        else {
                            final double productPrice = Double.parseDouble(InsertedPrice.getText().toString());
                            Product product = new Product(productName, productPrice);
                            databaseReference.push().setValue(product);
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                ad.create().show();


            }
        });

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Product.class)
                .build();
    adapter= new RVMenuAdapter(options);
    recyclerView.setAdapter(adapter);
        return view;

    }
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }







    }


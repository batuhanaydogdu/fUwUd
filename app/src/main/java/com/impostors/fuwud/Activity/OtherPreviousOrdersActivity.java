package com.impostors.fuwud.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Adapter.RVOrderAdapter;
import com.impostors.fuwud.Model.PrevOrder;
import com.impostors.fuwud.R;

public class OtherPreviousOrdersActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RVOrderAdapter adapter;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        FirebaseRecyclerOptions<PrevOrder> options =
                new FirebaseRecyclerOptions.Builder<PrevOrder>().setQuery
                        (databaseReference.child("users").child(currentUser.getUid()).child("completedOrders"), PrevOrder.class).build();
        adapter = new RVOrderAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

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
    public void init(){
        setContentView(R.layout.activity_other_previous_orders);
        recyclerView=findViewById(R.id.rvOtherPrev);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

    }
}
package com.impostors.fuwud.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.Adapter.OrderAdapter;
import com.impostors.fuwud.Adapter.RVMenuAdapter;
import com.impostors.fuwud.Model.PrevOrder;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

public class FragmentOrder extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Activity context;
    OrderAdapter adapter;
    private Button insertOrder;


    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_order, container, false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //FirebaseRecyclerOptions<PrevOrder> options =
                //new FirebaseRecyclerOptions.Builder<PrevOrder>().setQuery
                       // (FirebaseDatabase.getInstance().getReference().child("PrevOrder").limitToFirst(5), PrevOrder.class).build();





        return view;
    }
    /*public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }*/
}

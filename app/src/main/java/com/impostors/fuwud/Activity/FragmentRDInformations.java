package com.impostors.fuwud.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.impostors.fuwud.R;

public class FragmentRDInformations extends Fragment {
    TextView textViewLatitude,textviewLongitude,textViewPhoneInfo,textViewLongitude,textViewEmailInfo,textViewNameInfo;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_r_d_informations, container, false);

        textViewLatitude =view.findViewById(R.id.restaurantInfoLatitude);
        textViewPhoneInfo=view.findViewById(R.id.restaurantInfophone);
        textviewLongitude=view.findViewById(R.id.restaurantInfoLongitude);
        textViewEmailInfo=view.findViewById(R.id.textViewInfoEmail);
        textViewNameInfo=view.findViewById(R.id.restaurantInfoName);


        final String restaurantName=getActivity().getIntent().getStringExtra("restaurantName");
        final String restaurantEmail=getActivity().getIntent().getStringExtra("restaurantEmail");
/*         final double restauranLatitude=getActivity().getIntent().getDoubleExtra("restaurantLatitude",0.0);
         final double restaurantLongitude=getActivity().getIntent().getDoubleExtra("restaurantLongitude",0.0);*/
        final String restaurantPhone=getActivity().getIntent().getStringExtra("restaurantPhone");


        textViewPhoneInfo.setText(restaurantPhone);
        textViewNameInfo.setText(restaurantName);
/*        textViewLatitude.setText(String.valueOf(restauranLatitude));
        textViewLongitude.setText(String.valueOf(restaurantLongitude));*/
        textViewEmailInfo.setText(restaurantEmail);


        return view;
    }
}
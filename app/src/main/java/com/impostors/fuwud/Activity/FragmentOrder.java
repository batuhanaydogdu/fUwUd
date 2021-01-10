package com.impostors.fuwud.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.impostors.fuwud.Adapter.RVOrderAdapter;
import com.impostors.fuwud.Model.User;
import com.impostors.fuwud.R;

public class FragmentOrder extends Fragment implements LocationListener {

    Activity context;
    RVOrderAdapter adapter;
    private Button insertOrder;
    Switch switchSearch;
    Button buttonListRestaurants;
    private LocationManager locationManager;
    private int checkForPermission;
    Double currentLatitude=0.0,currentLongitude=0.0;
    private String locationProvider = "gps";
    Location loc;
    TextView textViewCoordinate;
    private RecyclerView recyclerView;




    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_order, container, false);
        init(view);

        //FirebaseRecyclerOptions<PrevOrder> options =
        //new FirebaseRecyclerOptions.Builder<PrevOrder>().setQuery
        // (FirebaseDatabase.getInstance().getReference().child("PrevOrder").limitToFirst(5), PrevOrder.class).build();


        switchSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    switchSearch.setText("Yakınımdaki restaurantları");
                }
                else{
                    switchSearch.setText("Kayıtlı adresime göre");
                }
            }
        });


        buttonListRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(switchSearch.isChecked()){
                    getLocation();


                    checkForPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);

                    if(checkForPermission == PackageManager.PERMISSION_GRANTED && currentLatitude!=0.0&&currentLongitude!=0.0) {

                        Intent intent_to_ListedRestaurant = new Intent(getActivity().getApplication(), ListedRestaurantActivity.class);
                        intent_to_ListedRestaurant.putExtra("latitude", currentLatitude);
                        intent_to_ListedRestaurant.putExtra("longitude", currentLongitude);
                        startActivity(intent_to_ListedRestaurant);

                    }




                }

                else{

                    Query queryForLoc=databaseReference.child("users").child(currentUser.getUid()).orderByKey();
                    Query queryForLoc2=databaseReference.child("users").child(currentUser.getUid()).child("latitude");




                    queryForLoc.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                for (DataSnapshot issue : snapshot.getChildren()) {
                                    if(issue.getKey().equals("longitude")){
                                        currentLongitude= Double.parseDouble(issue.getValue().toString());
                                    }
                                    if(issue.getKey().equals("latitude")){
                                        currentLatitude=Double.parseDouble(issue.getValue().toString());
                                    }



                                }
                            }

                            if(currentLatitude!=0.0&&currentLongitude!=0.0){
                            Intent intent_to_ListedRestaurant = new Intent(getActivity().getApplication(), ListedRestaurantActivity.class);
                            intent_to_ListedRestaurant.putExtra("latitude", currentLatitude);
                            intent_to_ListedRestaurant.putExtra("longitude", currentLongitude);
                            startActivity(intent_to_ListedRestaurant);}
                            else{
                                Toast.makeText(getContext(),"ADRES KAYDETMEMİŞSİN ABİ",Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {



                        }
                    });









                }



            }
        });


        return view;
    }


    private void init(View view){
        switchSearch=view.findViewById(R.id.switchSearch);
        buttonListRestaurants=view.findViewById(R.id.buttonListRestaurants);
        locationManager=(LocationManager)getContext().getSystemService(getContext().LOCATION_SERVICE);
        textViewCoordinate=view.findViewById(R.id.textViewCoordinate);


        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }


    private void listCloseRestaurants(){



    }


    private void getLocation(){

        checkForPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);

        if(checkForPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }else
        {
            locationManager = (LocationManager) getContext()
                    .getSystemService(Context.LOCATION_SERVICE);
            boolean checkGPS = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (checkGPS) {
                if (loc == null) { // eğer bir kere loc aldıysak 2.ye gerek yok o kadar hızlı yer değiştiremez çünkü değişmeme sebebi bu
                    try {

                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                10,// locationu değiştirmek için kaç ms gerek
                                10, this);//locationu update etmek için kaç metre uzaklık gerek
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            loc = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (loc != null) {
                                onLocationChanged(loc);


                            }
                        }
                    } catch (SecurityException e) {

                    }
                }
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        textViewCoordinate.setText(currentLatitude+" q "+currentLongitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100){

            checkForPermission = ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION);



            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getContext(), "izin kabul edildi.", Toast.LENGTH_LONG).show();

                locationManager = (LocationManager) getContext()
                        .getSystemService(Context.LOCATION_SERVICE);
                boolean checkGPS = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (checkGPS) {
                    if (loc == null) { // eğer bir kere loc aldıysak 2.ye gerek yok o kadar hızlı yer değiştiremez çünkü değişmeme sebebi bu
                        try {

                            locationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER,
                                    10, // locationu değiştirmek için kaç ms gerek
                                    10, this);//locationu update etmek için kaç metre uzaklık gerek
                            Log.d("GPS Enabled", "GPS Enabled");
                            if (locationManager != null) {
                                loc = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                                if (loc != null) {
                                    onLocationChanged(loc);
                                    if(checkForPermission == PackageManager.PERMISSION_GRANTED && currentLatitude!=0.0&&currentLongitude!=0.0) {

                                        Intent intent_to_ListedRestaurant = new Intent(getActivity().getApplication(), ListedRestaurantActivity.class);
                                        intent_to_ListedRestaurant.putExtra("latitude", currentLatitude);
                                        intent_to_ListedRestaurant.putExtra("longitude", currentLongitude);
                                        startActivity(intent_to_ListedRestaurant);

                                    }





                                }
                            }
                        } catch (SecurityException e) {

                        }
                    }
                }

            } else {
                Log.e("location", "izin reddedildi");
                Toast.makeText(getContext(), "İzin reddedildi.", Toast.LENGTH_LONG).show();
            }

        }
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













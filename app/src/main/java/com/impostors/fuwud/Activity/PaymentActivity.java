package com.impostors.fuwud.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.impostors.fuwud.Model.PrevOrder;
import com.impostors.fuwud.Model.Product;
import com.impostors.fuwud.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity {
    TextView textViewPaymentPrice;
    EditText editTextCartCode,editTextCartValidDate;
    ImageButton imageButtonPaymentNext;
    String restaurantId;


    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
        textViewPaymentPrice.setText(getIntent().getDoubleExtra("price",0)+"");
        restaurantId=getIntent().getStringExtra("restaurantId");
        imageButtonPaymentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextCartCode.getText().toString().equals("")&&!editTextCartValidDate.getText().toString().equals("")&&getIntent().getDoubleExtra("price",0)!=0
                &&restaurantId!=null){
                    Toast.makeText(getApplicationContext(),"Ödeme isteğin restauranta yönlendirildi.",Toast.LENGTH_LONG);
                    PrevOrder prevOrder=new PrevOrder();
                    prevOrder.setCompleted(false);
                    prevOrder.setOwnerUid(currentUser.getUid());
                    prevOrder.setPrice(getIntent().getDoubleExtra("price",0));
                    prevOrder.setDate(new Date());
                    setOrdersAndDelete(prevOrder);

                    //TODO restaurant için prevOrderden complete kısmını true veya false yapması için istek yarat


                }

            }
        });
    }

    private void setOrdersAndDelete(final PrevOrder prevOrder){  //TODO ABİSİ bunu silicem sonra buraya listener şeklinde olmayan productları prevOrdere EKLEYECEM
        Query query=databaseReference.child("users").child(currentUser.getUid()).child("currentBasket");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d:snapshot.getChildren()){
                    prevOrder.getProducts().put(d.getKey(),d.getValue(Product.class));
                    Log.e("abisi","neden yoksun?");

                }
                databaseReference.child("restaurants").child(restaurantId).child("orders").push().setValue(prevOrder);
                Intent payment_main_intent = new Intent(PaymentActivity.this, MainPageActivity.class);
                startActivity(payment_main_intent);
                finish();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("users").child(currentUser.getUid()).child("currentBasket").removeValue();

    }


    private void init(){
        textViewPaymentPrice=findViewById(R.id.textViewPaymentPrice);
        editTextCartCode=findViewById(R.id.editTextCartCode);
        editTextCartValidDate=findViewById(R.id.editTextCartValidDate);
        imageButtonPaymentNext=findViewById(R.id.imageButtonPaymentNext);


        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();


    }
}
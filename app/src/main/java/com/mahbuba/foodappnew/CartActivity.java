package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    RecyclerView re;
    EditText address, phone;
    TextView pr;

    FirebaseDatabase database;
    DatabaseReference reference,ref2;
    FirebaseAuth fAuth;

    PointCartHolder ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();

        pr = findViewById(R.id.pricee);


        FirebaseUser user = fAuth.getCurrentUser();
        String userID = user.getUid();


        reference = database.getReference(userID);


        re = findViewById(R.id.rv);
        re.setLayoutManager(new LinearLayoutManager(this));


   /*     FirebaseRecyclerOptions<Pointvalue> options = new FirebaseRecyclerOptions.Builder<Pointvalue>().
                setQuery(reference, Pointvalue.class).build();


        ph = new PointCartHolder(options);
        re.setAdapter(ph);*/

        FirebaseRecyclerOptions<PointValue> options = new FirebaseRecyclerOptions.Builder<PointValue>().
                setQuery(reference, PointValue.class).build();


        ph = new PointCartHolder(options);
        re.setAdapter(ph);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sum = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {

                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("price");

                    int pvalue = Integer.parseInt(String.valueOf((price)));
                    sum = sum + pvalue;
                    pr.setText(String.valueOf(sum));


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
             ph.stopListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
      ph.startListening();
    }

    public void Confirm(View view) {


    /*    ref2 = database.getReference("Address");
        FirebaseUser user = fAuth.getCurrentUser();
        String userID = user.getUid();
        //    database = FirebaseDatabase.getInstance().getReference().child(userID);
        String id = reference.push().getKey();

AddressClass addressClass=new AddressClass(x,addd,phnn,status);


                ref2.child(userID).setValue(addressClass);   */

                Toast.makeText(CartActivity.this, "Your info has been recorded ! ", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CartActivity.this, ConfirmActivity.class);

        startActivity(intent);
    }
}
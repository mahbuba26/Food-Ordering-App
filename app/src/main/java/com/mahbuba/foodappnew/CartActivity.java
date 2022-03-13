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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahbuba.foodappnew.Model.Pointvalue;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
public class CartActivity extends AppCompatActivity {
    RecyclerView re;
    EditText address, phone;
    TextView pr;
    List<FetchData> fetchData;
    CartAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference reference, rrefer;
    FirebaseAuth fAuth;
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    PointCartHolder ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        pr = findViewById(R.id.pricee);


        FirebaseUser user = fAuth.getCurrentUser();
        String userID = user.getUid();

        //    database = FirebaseDatabase.getInstance().getReference().child(userID);
        reference = database.getReference(userID);

//        rrefer= (DatabaseReference) reference.orderByChild("date").equalTo(x);
        re = findViewById(R.id.rv);
        re.setLayoutManager(new LinearLayoutManager(this));
        /*
fetchData =new ArrayList<>();
reference.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
        for(DataSnapshot dSnapshot: datasnapshot.getChildren()){

            FetchData data=datasnapshot.getValue(FetchData.class);
            fetchData.add(data);
        }
        adapter=new CartAdapter(fetchData);
        re.setAdapter(adapter);


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

        adapter=new CartAdapter(fetchData);
        re.setAdapter(adapter);
*/                                           //value choto hater
        FirebaseRecyclerOptions<Pointvalue> options = new FirebaseRecyclerOptions.Builder<Pointvalue>().
                setQuery(reference, Pointvalue.class).build();
//FirebaseDatabase.getInstance().getReference().child("userid");

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
        String addd = address.getText().toString().trim();
        String phnn = phone.getText().toString().trim();


        if (TextUtils.isEmpty(addd)) {
            address.setError("Please enter your address ...");
            return;
        }
        if (TextUtils.isEmpty(phnn)) {
            phone.setError("Please enter your phone number ...");
            return;
        }
        Intent intent = new Intent(CartActivity.this, ConfirmActivity.class);
        startActivity(intent);
    }
}
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
import com.mahbuba.foodappnew.Model.Pointvalue;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class project extends AppCompatActivity {
    RecyclerView re;
    EditText address, phone;
    TextView pr;
    String g_id;
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
        setContentView(R.layout.activity_project);

        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        pr = findViewById(R.id.pricee);

        g_id=intent.getStringExtra("userid");

        FirebaseUser user = fAuth.getCurrentUser();
       // String userID = user.getUid();
        String userID = g_id;
        reference = database.getReference(userID);

//        rrefer= (DatabaseReference) reference.orderByChild("date").equalTo(x);
        re = findViewById(R.id.recycler);
        re.setLayoutManager(new LinearLayoutManager(this));
                                      //value choto hater
        FirebaseRecyclerOptions<Pointvalue> options = new FirebaseRecyclerOptions.Builder<Pointvalue>().
                setQuery(reference, Pointvalue.class).build();


        ph = new PointCartHolder(options);
        re.setAdapter(ph);



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




}
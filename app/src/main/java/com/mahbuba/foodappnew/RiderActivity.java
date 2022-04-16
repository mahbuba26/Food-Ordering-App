package com.mahbuba.foodappnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;

public class RiderActivity extends AppCompatActivity {
    FirebaseDatabase database;
    FirebaseAuth fAuth;
     RecyclerView recyclerView;
    RiderAdapter adapter;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        reference = database.getReference("Address");
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RiderModel> options = new FirebaseRecyclerOptions.Builder<RiderModel>().
                setQuery(reference, RiderModel.class).build();


        adapter = new RiderAdapter(options);
        recyclerView.setAdapter(adapter);
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}
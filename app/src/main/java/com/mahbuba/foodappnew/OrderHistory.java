package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

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

import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity  {
AdapterUser adapterUser;
  //  private List<UserId> listData;
  RecyclerView rv;
  ArrayList<UserId> list;
    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();

        rv=findViewById(R.id.history);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = fAuth.getCurrentUser();
        String userID = user.getUid();

        //    database = FirebaseDatabase.getInstance().getReference().child(userID);
        reference = database.getReference(userID);
        String username =fAuth.getCurrentUser().getUid();
        reference2 = FirebaseDatabase.getInstance().getReference(username);
     //   listData=new ArrayList<>();

        list=new ArrayList<>();
  //      FirebaseRecyclerOptions<UserId> options = new FirebaseRecyclerOptions.Builder<UserId>().
   //             setQuery(reference,UserId.class).build();


       // adapterUser = new AdapterUser(options);
        adapterUser = new AdapterUser(this,list);

        rv.setAdapter(adapterUser);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    UserId user=dataSnapshot.getValue(UserId.class);
                    list.add(user);
                }
                adapterUser.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    }

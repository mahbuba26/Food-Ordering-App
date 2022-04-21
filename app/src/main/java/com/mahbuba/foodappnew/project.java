package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder2;
import com.mahbuba.foodappnew.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;

public class project extends AppCompatActivity {
    RecyclerView re;

    ActivityMainBinding binding;
    TextView pr;
    String g_id;
TextView adddress, status,phne,dat;
    FirebaseDatabase database;
    DatabaseReference reference, rrefer;
    FirebaseAuth fAuth;
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
    PointCartHolder2 ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_project);

        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        adddress = findViewById(R.id.adddd);
        status = findViewById(R.id.sttta);
       phne= findViewById(R.id.phonee);
       dat = findViewById(R.id.dateee);


        pr = findViewById(R.id.pricee);

        g_id=intent.getStringExtra("userid");
       String userId = fAuth.getCurrentUser().getUid();

        String userID = g_id;
        reference = database.getReference(userID);
        rrefer = database.getReference("Address");

//        rrefer= (DatabaseReference) reference.orderByChild("date").equalTo(x);
        re = findViewById(R.id.recycler);
        re.setLayoutManager(new LinearLayoutManager(this));
                                      //value choto hater
        FirebaseRecyclerOptions<PointValue> options = new FirebaseRecyclerOptions.Builder<PointValue>().
                setQuery(reference, PointValue.class).build();


        ph = new PointCartHolder2(options);
        re.setAdapter(ph);



        rrefer.child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){


                        DataSnapshot dataSnapshot = task.getResult();
                        String address = String.valueOf(dataSnapshot.child("address").getValue());
                        String date = String.valueOf(dataSnapshot.child("date").getValue());
                        String phone = String.valueOf(dataSnapshot.child("phone").getValue());
                        String statuss = String.valueOf(dataSnapshot.child("status").getValue());
                        adddress.setText("Address : "+ address);
                       status.setText("UserID  : "+ statuss);
                        phne.setText("Phone   : "+ phone);
                        dat.setText("Date    : "+ date);


                    }else {

                        Toast.makeText(project.this,"Address Info not found ",Toast.LENGTH_SHORT).show();

                    }


                }else {

                 //   Toast.makeText(project.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }
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


    public void click(View view) {
        // reference.child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    Toast.makeText(project.this,"Successfuly Cleared !",Toast.LENGTH_SHORT).show();
                    //    binding.etusername.setText("");


                }else {

                    Toast.makeText(project.this,"Something went wrong ! Try again .",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
}
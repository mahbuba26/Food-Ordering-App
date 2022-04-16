package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahbuba.foodappnew.Model.CategoryData;

import java.util.ArrayList;
import java.util.Map;

public class ActivityAdd extends AppCompatActivity {
Spinner spin;
EditText edt;

    EditText cat;
    EditText img,itm,prc,shp;

    Button sublist,upload;
    DatabaseReference reference;
    FirebaseDatabase database;
ValueEventListener listener;
ArrayAdapter<String> adapter;
ArrayList<String> arraylist;
    CategoryData categoryData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spin=findViewById(R.id.spinner);
        edt=findViewById(R.id.txt);



        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Foods");

        arraylist=new ArrayList<>();
        adapter=new ArrayAdapter<String>(ActivityAdd.this,android.R.layout.simple_spinner_dropdown_item,arraylist);

    listener=reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
for (DataSnapshot item: snapshot.getChildren()){
//     adddress.setText("Address : "+ address);
    Map<String, Object> map = (Map<String, Object>) item.getValue();
    Object price = map.get("item");

    String ad=snapshot.child("item").getValue().toString();
  //  arraylist.add(item.getValue().toString());
    arraylist.add(ad);
    adapter.notifyDataSetChanged();
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spin.setAdapter(adapter);
}
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });











    }
}
package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mahbuba.foodappnew.Model.CategoryData;

import java.util.ArrayList;

public class ExistingAddItem extends AppCompatActivity {
    TextView cat;
    EditText img,itm,prc,shp;
    Spinner ap;
    ArrayList<String> spin;
    ValueEventListener listener;
    DatabaseReference ref;
    ArrayAdapter<String> adap;
    Button sublist,upload;
    DatabaseReference reference;
    FirebaseDatabase database;

    CategoryData categoryData;

    ArrayList<CategoryData> categorydata =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_add_item);

        cat = findViewById(R.id.category_id);


        img = findViewById(R.id.cat_image);
        itm = findViewById(R.id.cat_item);
        prc = findViewById(R.id.cat_price);
        shp = findViewById(R.id.cat_shop);
        sublist = findViewById(R.id.btn_add_to_subList);

        ap=findViewById(R.id.spinner);
        ref= FirebaseDatabase.getInstance().getReference("Category");
        spin=new ArrayList<String>();
        adap=new ArrayAdapter<String>(ExistingAddItem.this,android.R.layout.simple_spinner_dropdown_item,spin);

        ap.setAdapter(adap);
        ap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item=spin.get(i);
                String item2=ap.getSelectedItem().toString();
                cat.setText(item);
                // Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listener =   ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot mydata : snapshot.getChildren())

                    spin.add(mydata.getValue().toString());

                adap.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Firebase init
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Foods");


        sublist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!img.getText().toString().isEmpty() && !itm.getText().toString().isEmpty() && !prc.getText().toString().isEmpty()  && !shp.getText().toString().isEmpty()){

                    addToSublist(img.getText().toString(),itm.getText().toString(),prc.getText().toString(),shp.getText().toString());
                }



            }
        });

        }

    private void addToSublist(String Image, String Item, String Price, String Shop) {

        categoryData=new CategoryData(Image,Item,Price,Shop);
     //   categorydata.add(categoryData);
        String id = reference.push().getKey();
        //     reference.child(String.valueOf(id)).setValue(pointvalue);
        reference.child(cat.getText().toString()).child("data").child(id).setValue(categoryData);

        img.setText("");
        itm.setText("");
        prc.setText("");
        shp.setText("");
        cat.setText("");
    }

    public void addnew(View view) {
        Intent i=new Intent(ExistingAddItem.this,AddItem.class);
        startActivity(i);
    }
}

package com.mahbuba.foodappnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mahbuba.foodappnew.Model.CategoryData;

import java.util.ArrayList;

public class AddItem extends AppCompatActivity {
    EditText cat;
    EditText img,itm,prc,shp;

    Button sublist,upload;
    DatabaseReference reference;
    FirebaseDatabase database;

    CategoryData categoryData;
   UploadItem uploadItem;
    ArrayList<CategoryData> categorydata =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        cat = findViewById(R.id.category_id);


        img = findViewById(R.id.cat_image);
        itm = findViewById(R.id.cat_item);
        prc = findViewById(R.id.cat_price);
        shp = findViewById(R.id.cat_shop);
        upload = findViewById(R.id.btn_final_upload);
        sublist = findViewById(R.id.btn_add_to_subList);


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

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadItem=new UploadItem(cat.getText().toString()
                        ,categorydata);

                reference.child(cat.getText().toString()).setValue(uploadItem);
            }
        });

    }

    private void addToSublist(String Image, String Item, String Price, String Shop) {

        categoryData=new CategoryData(Image,Item,Price,Shop);
        categorydata.add(categoryData);

        img.setText("");
        itm.setText("");
       prc.setText("");
        shp.setText("");
    }
}
package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DisplayActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
ImageView img;
Button add;
    TextView item,price,shop;
    TextView val;
    Integer va;
    int count=0;
    String userID;
    FirebaseFirestore fStore;
    public String Image;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    SimpleDateFormat sdf = new SimpleDateFormat("dd");
Long pricee;
    String tempUserName,tempUserId,tempUserAge;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        tempUserId = intent.getStringExtra("Item");
        tempUserName = intent.getStringExtra("Price");

        Toast.makeText(DisplayActivity.this, tempUserName, Toast.LENGTH_SHORT).show();
        tempUserAge = intent.getStringExtra("Shop");
        Image = intent.getStringExtra("Image");
//Image=intent.get
        //view init

        item = findViewById(R.id.main_text_id);
        price = findViewById(R.id.main_text_name);
        val = findViewById(R.id.value);
        img = findViewById(R.id.image22);
        add = findViewById(R.id.materialButton);
      // long total= Integer.parseInt(tempUserName)*Integer.parseInt(va);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                fAuth = FirebaseAuth.getInstance();
                FirebaseUser user = fAuth.getCurrentUser();
                String userID = user.getUid();
            //    database = FirebaseDatabase.getInstance().getReference().child(userID);
                reference = database.getReference(userID);
                //userID = fAuth.getCurrentUser().getUid();
                Random r = new Random();
                //int id = Integer.parseInt(sdf.format(new Date()));
                String id = reference.push().getKey();
                //    push=id;
            String i2=tempUserId;

              long Pieces = Long.valueOf(val.getText().toString());
           //   long P= Long.parseLong(tempUserName);
                long PP = new Long(tempUserName);
//Long P=pricee*Pieces;
              long P=Pieces*PP;
//price , piece int and multiple

                //long x = Long.parseLong(sdf.format(new Date()));
long x= Long.parseLong(sdf.format(new Date()));
            /*    PointValue pointvalue = new PointValue(x,i, Pieces, P);

                reference.child(String.valueOf(id)).setValue(pointvalue);
                Toast.makeText(DisplayActivity.this, "Added to cart successfully", Toast.LENGTH_SHORT).show();*/
                AlertDialog.Builder b=new AlertDialog.Builder(DisplayActivity.this);
                b.setTitle("Confirmation");
                b.setMessage("Are you sure you want to add the food in your cart?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        PointValue pointvalue = new PointValue(x,i2, Pieces, P);

                        reference.child(String.valueOf(id)).setValue(pointvalue);

                        Toast.makeText(DisplayActivity.this, "Added in your cart !", Toast.LENGTH_SHORT).show();
                    }
                });
                b.setNegativeButton(("No"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DisplayActivity.this, "Cannot be added ", Toast.LENGTH_SHORT).show();
                    }
                });
                b.show();

            }});


//if(!tempUserAge.isEmpty() && !tempUserId.isEmpty() && !tempUserName.isEmpty())
        if (!tempUserId.isEmpty() && !Image.isEmpty() && !tempUserName.isEmpty()) {
            item.setText(tempUserId);
            price.setText(tempUserName);
            Picasso.get().load(Image).into((ImageView) img);
            //    shop.setText(tempUserAge);
        }
    }

    public void Increase(View v){
        count++;
        val.setText("" + count);
    }

    public void Decrease(View v){

        if(count<=0)
            count=0;
        else
        count--;
        val.setText("" + count);
    }

}
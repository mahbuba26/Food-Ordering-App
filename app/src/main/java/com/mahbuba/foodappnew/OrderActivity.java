package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mahbuba.foodappnew.Interface.SubCategoryOnClickInterface;
import com.mahbuba.foodappnew.Model.Category;
import com.mahbuba.foodappnew.Model.CategoryData;
import com.mahbuba.foodappnew.ViewHolder.CategoryDataViewHolder;
import com.mahbuba.foodappnew.ViewHolder.CategoryViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference,reference2;
    DrawerLayout drawerLayout;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    FirebaseRecyclerAdapter<CategoryData, CategoryDataViewHolder> adapter2;
    RecyclerView.LayoutManager manager;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        drawerLayout=findViewById(R.id.drawer_layout);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Foods");
        manager = new LinearLayoutManager(this);

        AlertDialog.Builder b=new AlertDialog.Builder(OrderActivity.this);
        b.setTitle("Confirmation");
        b.setMessage("Please clean your cart before proceed !!!");
        b.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fAuth=FirebaseAuth.getInstance();
               // String fAuth="lvrH8O8rm5eTJy2t6DNdCagJp7T2";

                String username =fAuth.getCurrentUser().getUid();



                if (!username.isEmpty()){

                    deleteData(username);

                }else{

                 //   Toast.makeText(OrderActivity.this,"",Toast.LENGTH_SHORT).show();
                }

            }
        });
  /*      b.setNegativeButton(("No"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(OrderActivity.this, " ", Toast.LENGTH_SHORT).show();
            }
        });  */
        b.show();


















        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(manager);
        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(reference,Category.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {

                CategoryViewHolder.item.setText(model.getItem());
                FirebaseRecyclerOptions<CategoryData> options2 = new FirebaseRecyclerOptions.Builder<CategoryData>()
                        .setQuery(reference.child(model.getItem()).child("data"),CategoryData.class)
                        .build();

                adapter2=new FirebaseRecyclerAdapter<CategoryData, CategoryDataViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryDataViewHolder holder, int position, @NonNull CategoryData model) {
                        holder.Item.setText(model.getItem());
                        holder.Price.setText(model.getPrice());
                        holder.Shop.setText(model.getShop());
                        Picasso.get().load(model.getImage()).into(holder.Image);

                        holder.SubCategoryInterfaceClick(new SubCategoryOnClickInterface() {
                            @Override
                            public void onClick(View view, boolean isLongPressed) {
                                Intent intent = new Intent(OrderActivity.this,DisplayActivity.class);
                                intent.putExtra("Item",model.getItem());
                                intent.putExtra("Price",model.getPrice());
                                intent.putExtra("Image",model.getImage());
                                //   intent.putExtra("Shop",model.getShop());

                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public CategoryDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v2 = LayoutInflater.from(getBaseContext())
                                .inflate(R.layout.item_view_categorydata,parent,false);
                        return new CategoryDataViewHolder(v2);
                    }
                };

                adapter2.startListening();
                adapter2.notifyDataSetChanged();
                holder.category_recyclerView.setAdapter(adapter2);
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1 = LayoutInflater.from(getBaseContext())
                        .inflate(R.layout.item_view_category,parent,false);
                return new CategoryViewHolder(v1);
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);



    }

    private void deleteData(String username) {

        reference2 = FirebaseDatabase.getInstance().getReference(username);
        // reference.child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
        reference2.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    Toast.makeText(OrderActivity.this,"Successfuly Cleared !",Toast.LENGTH_SHORT).show();
                    //    binding.etusername.setText("");


                }else {

                    Toast.makeText(OrderActivity.this,"Something went wrong ! Try again .",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }


    public void ClickAboutUs(View view){

        redirectActivity(this,AboutUs.class);
    }
    public void ClickLogout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public static void logout(final Activity activity) {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
        FirebaseAuth.getInstance().signOut();//logout
        //startActivity(new Intent(getApplicationContext(),Login.class));
        // finish();
    }

    public static void redirectActivity(Activity activity,Class aClass) {
        Intent intent=new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);


    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }


    public void Cart(View view) {
        Intent intent=new Intent(OrderActivity.this,CartActivity.class);
        startActivity(intent);
    }
}


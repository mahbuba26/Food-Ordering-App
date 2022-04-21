package com.mahbuba.foodappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.mahbuba.foodappnew.Interface.SubCategoryOnClickInterface;

public class ActivityHonda extends AppCompatActivity {

    private FirebaseFirestore firestore;

private RecyclerView mfirestore;
DatabaseReference r,reference;
private  FirebaseRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honda);
//firestore=FirebaseFirestore.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Address");
        mfirestore=findViewById(R.id.honda);

        FirebaseRecyclerOptions<HondaModel> options = new FirebaseRecyclerOptions.Builder<HondaModel>().
                setQuery(reference, HondaModel.class).build();

        adapter= new FirebaseRecyclerAdapter<HondaModel, HondaViewHolder>(options) {
            @NonNull
            @Override
            public HondaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.honda_model,parent,false);



                return new HondaViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull HondaViewHolder holder, int position, @NonNull HondaModel model) {
holder.name.setText(model.getPhone());
holder.id.setText(model.getStatus());

                holder.SubCategoryInterfaceClick(new SubCategoryOnClickInterface() {
                    @Override
                    public void onClick(View view, boolean isLongPressed) {
                        r = FirebaseDatabase.getInstance().getReference(model.getStatus());
                        if (r != null) {

                            Intent intent = new Intent(ActivityHonda.this, project.class);
                            intent.putExtra("fname", model.getPhone());
                            intent.putExtra("userid", model.getStatus());

                            startActivity(intent);
                        } else {
                            view.setBackgroundColor(Color.GREEN);
                        }
                    } });


            }
        };

        mfirestore.setHasFixedSize(true);
    mfirestore.setLayoutManager(new LinearLayoutManager(this));
    mfirestore.setAdapter(adapter);




    }

    private class HondaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public SubCategoryOnClickInterface subCategoryOnClickInterface;
        private TextView name,id;
        public HondaViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.fullName);
            id=itemView.findViewById(R.id.userid);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            subCategoryOnClickInterface.onClick(view,false);
        }

        public void SubCategoryInterfaceClick(SubCategoryOnClickInterface subCategoryOnClickInterface)
        {
            this.subCategoryOnClickInterface = subCategoryOnClickInterface;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    //}
}
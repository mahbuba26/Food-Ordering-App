package com.mahbuba.foodappnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mahbuba.foodappnew.Model.Pointvalue;
import com.mahbuba.foodappnew.ViewHolder.PointCartHolder;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.MyViewHolder>{
Context context;
ArrayList<UserId> list;

    public AdapterUser(Context context,ArrayList<UserId> list) {
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.history_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
UserId userId=list.get(position);
holder.Userid.setText(userId.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
TextView Userid;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            Userid=itemView.findViewById(R.id.USER);
        }
    }
}
package com.mahbuba.foodappnew.ViewHolder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mahbuba.foodappnew.PointValue;
import com.mahbuba.foodappnew.R;

public class PointCartHolder2 extends FirebaseRecyclerAdapter<PointValue,PointCartHolder2.myViewHolder2> {
    private Context context;
    DatabaseReference reference, reference2;
    FirebaseAuth fAuth;

    public PointCartHolder2(@NonNull FirebaseRecyclerOptions<PointValue> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull PointCartHolder2.myViewHolder2 holder, @SuppressLint("RecyclerView") int position, @NonNull PointValue model) {
        holder.Item.setText("Item    :  " + model.getItem());
        holder.Piece.setText("Piece  :  " + model.getPieces().toString());
        holder.Price.setText("Price  :  " + model.getPrice().toString());
        holder.Date.setText("Date   :  " + model.getDate());




        myViewHolder2 viewHolder2=(myViewHolder2) holder;



    }

    @NonNull
    @Override
    public PointCartHolder2.myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout2,parent,false);
        return new PointCartHolder2.myViewHolder2(view);
    }

    class myViewHolder2 extends  RecyclerView.ViewHolder{
        TextView Item , Price, Piece,Date;



        public myViewHolder2(@NonNull final View itemView) {
            super(itemView);
            Date=itemView.findViewById(R.id.data);
            Item = (TextView) itemView.findViewById(R.id.data_id2);
            Piece = (TextView)itemView.findViewById(R.id.data_name2);
            Price=(TextView)itemView.findViewById(R.id.data_age2);


        }
    }
}

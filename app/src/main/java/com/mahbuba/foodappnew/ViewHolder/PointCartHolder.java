package com.mahbuba.foodappnew.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mahbuba.foodappnew.CartAdapter;
import com.mahbuba.foodappnew.FetchData;
import com.mahbuba.foodappnew.Interface.SubCategoryOnClickInterface;
import com.mahbuba.foodappnew.Model.Pointvalue;
import com.mahbuba.foodappnew.PointValue;
import com.mahbuba.foodappnew.R;

public class PointCartHolder extends FirebaseRecyclerAdapter<Pointvalue,PointCartHolder.myViewHolder>{

    public PointCartHolder(@NonNull FirebaseRecyclerOptions<Pointvalue> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Pointvalue model) {
        holder.Item.setText(model.getItem());
        holder.Piece.setText(model.getPieces().toString());
        holder.Price.setText(model.getPrice().toString());

        myViewHolder viewHolder=(myViewHolder) holder;



      //  viewHolder.Item.setText(fetchData.getItem());
       // viewHolder.Piece.setText(fetchData.getPieces().toString());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);
        return new myViewHolder(view);


    }

    class myViewHolder extends  RecyclerView.ViewHolder{
            TextView Item , Price, Piece;

            public myViewHolder(@NonNull final View itemView) {
                super(itemView);
                Item = (TextView) itemView.findViewById(R.id.data_id2);
                Piece = (TextView)itemView.findViewById(R.id.data_name2);
                Price=(TextView)itemView.findViewById(R.id.data_age2);

            }
    }
}
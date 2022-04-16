package com.mahbuba.foodappnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    List<FetchData> fetchDataList;

    public CartAdapter(List<FetchData> fetchDataList) {
        this.fetchDataList = fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);
        myViewHolder viewHolder=new myViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        myViewHolder viewHolder=(myViewHolder) holder;



        FetchData fetchData=fetchDataList.get(position);
        viewHolder.Item.setText(fetchData.getItem());
        viewHolder.Piece.setText(fetchData.getPieces().toString());
        viewHolder.Price.setText(fetchData.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
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

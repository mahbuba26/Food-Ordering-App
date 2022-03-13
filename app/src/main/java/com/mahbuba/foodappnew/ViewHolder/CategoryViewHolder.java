package com.mahbuba.foodappnew.ViewHolder;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.mahbuba.foodappnew.Model.Category;
import com.mahbuba.foodappnew.R;

import java.text.BreakIterator;
import java.util.List;

public class CategoryViewHolder extends  RecyclerView.ViewHolder implements Filterable {



    public static TextView item;
    public RecyclerView category_recyclerView;
    public RecyclerView.LayoutManager manager;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);


        manager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        item = itemView.findViewById(R.id.category_name);
        category_recyclerView = itemView.findViewById(R.id.recyclerView);
        category_recyclerView.setLayoutManager(manager);
    }


    @Override
    public Filter getFilter() {
        return null;
    }
}

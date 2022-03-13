package com.mahbuba.foodappnew.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahbuba.foodappnew.Interface.SubCategoryOnClickInterface;
import com.mahbuba.foodappnew.R;

public class CategoryDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView Item;
    public TextView Price;
    public TextView Shop;
    public ImageView Image;
    public SubCategoryOnClickInterface subCategoryOnClickInterface;

    public CategoryDataViewHolder(@NonNull final View itemView) {
        super(itemView);
        Item = itemView.findViewById(R.id.data_id);
        Price = itemView.findViewById(R.id.data_age);
        Shop = itemView.findViewById(R.id.data_name);
        Image= itemView.findViewById(R.id.image);
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


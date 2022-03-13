package com.mahbuba.foodappnew;


import com.mahbuba.foodappnew.Model.CategoryData;

import java.util.ArrayList;

public class UploadItem {

    private String Item;
    private ArrayList<CategoryData> data;

    public UploadItem() {
    }

    public UploadItem(String categoryName, ArrayList<CategoryData> data) {
        this.Item = categoryName;
        this.data = data;
    }

    public ArrayList<CategoryData> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryData> data) {
        this.data = data;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }
}

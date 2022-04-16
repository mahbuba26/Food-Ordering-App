package com.mahbuba.foodappnew.Model;

public class Category {
    private String Item;

  /*  public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }
*/
    public Category() {
    }

    public Category(String ItemName) {
        this.Item= ItemName;
    }

    public void setItem(String ItemName) {
       this.Item = ItemName;
    }
//126 128
    public  String getItem() {
        return Item;
    }

}

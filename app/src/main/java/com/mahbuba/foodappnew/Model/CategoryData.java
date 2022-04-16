package com.mahbuba.foodappnew.Model;

public class CategoryData {

    private String Image;
    private String Item;
    private String Price;
    private String Shop;

    public CategoryData() {
    }
    public CategoryData(String image, String Item, String price,String shop) {
        this.Image = image;
        this.Item = Item;
        this.Price = price;
        this.Shop=shop;
    }
    public String getShop() {
        return Shop;
    }

    public void setShop(String shop) {
        Shop = shop;
    }

    public void setImage(String image) {
        Image = image;
    }


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }
}

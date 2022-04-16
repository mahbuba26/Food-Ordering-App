package com.mahbuba.foodappnew;

public class PointValue {
   String Date;
    String Item;
    Long Pieces;
    Long Price;


    public PointValue(){}

    public PointValue(String x, String y, Long id, Long total){
        this.Date=x;
        this.Item=y;
        this.Pieces=id;
        this.Price=total;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Long getPieces() {
        return Pieces;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public void setPieces(Long pieces) {
        Pieces = pieces;
    }

    public String getItem() {
        return Item;
    }



    public void setItem(String item) {
        Item = item;
    }






}


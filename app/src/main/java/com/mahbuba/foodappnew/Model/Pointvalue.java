package com.mahbuba.foodappnew.Model;

public class Pointvalue {
    String Item;
    Long Pieces;
    Long Price;
public Pointvalue(){}
    public Pointvalue(String item, Long pieces, Long price) {
        Item = item;
        Pieces = pieces;
        Price = price;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public Long getPieces() {
        return Pieces;
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
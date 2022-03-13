package com.mahbuba.foodappnew;

public class FetchData {
    String item;
    Long pieces;
    Long price;

    public FetchData(String item,Long pieces,Long price) {
        this.item = item;
        this.pieces=pieces;
        this.price=price;
    }

    public  Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPieces() {
        return pieces;
    }

    public void setPieces(Long pieces) {
        this.pieces = pieces;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

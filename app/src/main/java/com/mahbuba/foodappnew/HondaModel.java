package com.mahbuba.foodappnew;

public class HondaModel {

//4 static extraa
    private  String fName;
    private  String userID;
    public HondaModel() {

    }
    public HondaModel(String fName,String userID) {
        this.fName = fName;
        this.userID = userID;
    }
    public  String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public  String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

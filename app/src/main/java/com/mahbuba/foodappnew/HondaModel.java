package com.mahbuba.foodappnew;

public class HondaModel {

//4 static extraa
    private  String status;
    private  String phone;
    public HondaModel() {

    }
    public HondaModel(String status,String phone) {
        this.status = status;
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

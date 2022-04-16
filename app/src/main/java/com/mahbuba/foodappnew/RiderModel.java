package com.mahbuba.foodappnew;

public class RiderModel {

    String address;
    String date;
    String phone;
    String status;

    public RiderModel() {
    }
    public RiderModel(String address,String date,String phone,String status) {
        this.address = address;
        this.date = date;
        this.phone = phone;
        this.status = status;
    }



    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

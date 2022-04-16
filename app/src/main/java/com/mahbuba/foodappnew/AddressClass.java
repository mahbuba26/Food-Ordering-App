package com.mahbuba.foodappnew;

public class AddressClass {
    String Date;
    String Address,Phone,Status;

    public AddressClass(){}

    public AddressClass(String x,String address, String phone, String status) {
        Date=x;
        Address = address;
        Phone=phone;
        Status=status;

    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}

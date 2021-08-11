package com.example.apprutasmintic.model.entity;

public class Parent extends User {
  String address;


    public Parent(String names, String email,  String phone_number, Roles role, String address) {
        super(names, email, phone_number, role);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Parent{"+
        "names='" + getNames() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone_number='" + getPhone_number() + '\'' +
                ", role=" + String.valueOf(getRole()) + '\'' +
                "address='" + address + '\'' +
                '}';
    }



}

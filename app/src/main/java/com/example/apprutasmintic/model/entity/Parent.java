package com.example.apprutasmintic.model.entity;

public class Parent extends User {
  String address;


    public Parent(String names, String email, String password, String phone_number, Roles role, String address) {
        super(names, email, password, phone_number, role);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

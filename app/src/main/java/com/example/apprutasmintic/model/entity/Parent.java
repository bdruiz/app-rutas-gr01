package com.example.apprutasmintic.model.entity;

public class Parent extends User {
  String address;
  int id_student;

    public Parent(String names, String email, String phone_number, Roles role, String address, int id_student) {
        super(names, email, phone_number, role);
        this.address = address;
        this.id_student = id_student;
    }

    public Parent(String names, String email, String phone_number, Roles role, String address) {
        super(names, email, phone_number, role);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    @Override
    public String toString() {
        return "Parent{"+
                "names='" + getNames() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone_number='" + getPhone_number() + '\'' +
                ", role=" + String.valueOf(getRole()) + '\'' +
                "address='" + address + '\'' +
                ", id_student=" + id_student +
                '}';
    }





}

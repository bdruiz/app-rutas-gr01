package com.example.apprutasmintic.model.entity;

public class Student {
    String names;
    String address;
    String path_photo;
    String uid_tutor;
    String advice_note;
    String phone_tutor;


    public Student() {

        names = "";
        address = "";
        path_photo = "";
        uid_tutor = "";
        advice_note = "";
        phone_tutor = "";
    }

    public Student(String names, String address, String path_photo, String uid_tutor, String advice_note, String phone_tutor) {
        this.names = names;
        this.address = address;
        this.path_photo = path_photo;
        this.uid_tutor = uid_tutor;
        this.advice_note = advice_note;
        this.phone_tutor = phone_tutor;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath_photo() {
        return path_photo;
    }

    public void setPath_photo(String path_photo) {
        this.path_photo = path_photo;
    }

    public String getUid_tutor() {
        return uid_tutor;
    }

    public void setUid_tutor(String uid_tutor) {
        this.uid_tutor = uid_tutor;
    }

    public String getAdvice_note() {
        return advice_note;
    }

    public void setAdvice_note(String advice_note) {
        this.advice_note = advice_note;
    }

    public String getPhone_tutor() {
        return phone_tutor;
    }

    public void setPhone_tutor(String phone_tutor) {
        this.phone_tutor = phone_tutor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "names='" + names + '\'' +
                ", address='" + address + '\'' +
                ", path_photo='" + path_photo + '\'' +
                ", uid_tutor='" + uid_tutor + '\'' +
                ", advice_note='" + advice_note + '\'' +
                ", phone_tutor='" + phone_tutor + '\'' +
                '}';
    }
}


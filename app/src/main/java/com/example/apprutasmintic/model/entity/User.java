package com.example.apprutasmintic.model.entity;

public class User {
    private String names;
    private String email;
    private String password;
    private String phone_number;

    public enum Roles {
        PADRE, MONITORA;
    }

    private Roles role;


    public User(String names, String email, String password, String phone_number, Roles role) {
        this.names = names;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.role = role;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Roles getRole() {
        return role;
    }

}

package com.example.apprutasmintic.model.entity;

public class User {
    private String names;
    private String email;
    private String phone_number;

    public enum Roles {
        PADRE, MONITORA;
    }

    private Roles role;


    public User(String names, String email, String phone_number, Roles role) {
        this.names = names;
        this.email = email;

        this.phone_number = phone_number;
        this.role = role;
    }

    public User() {
        this.names = "";
        this.email = "";
        this.phone_number = "";
        this.role = null;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Roles getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "names='" + names + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", role=" + role +
                '}';
    }
}

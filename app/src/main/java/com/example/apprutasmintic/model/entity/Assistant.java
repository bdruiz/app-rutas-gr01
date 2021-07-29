package com.example.apprutasmintic.model.entity;

public class Assistant extends User{
    int id_route;

    public Assistant(String names, String email, String password, String phone_number, Roles role, int id_route) {
        super(names, email, password, phone_number, role);
        this.id_route = id_route;
    }

    public int getId_route() {
        return id_route;
    }


}

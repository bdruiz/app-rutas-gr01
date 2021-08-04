package com.example.apprutasmintic.model.entity;

public class Assistant extends User{
    String id_route;

    public Assistant(String names, String email, String phone_number, Roles role, String id_route) {
        super(names, email, phone_number, role);
        this.id_route = id_route;
    }

    public void setId_route(String id_route) {
        this.id_route = id_route;
    }

    public String getId_route() {
        return id_route;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "names='" + getNames() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone_number='" + getPhone_number() + '\'' +
                ", role=" + String.valueOf(getRole()) + '\'' +
                "id_route='" + id_route + '\'' +
                '}';
    }
}

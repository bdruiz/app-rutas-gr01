package com.example.apprutasmintic.model.repository;

import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.view.login.LoginMVP;

import java.util.Arrays;
import java.util.List;

public class UserRepository implements LoginMVP.Model {

    private LoginMVP.Presenter loginPresenter;

    private List<User> users;

    public UserRepository() {
        users = Arrays.asList(
                new Assistant("Monica Jimenez", "monica@correo.com","123456","3000000000", User.Roles.MONITORA,105),
                new Assistant("Sara Fernadez", "sara@correo.com","123456","3000000001", User.Roles.MONITORA,102),
                new Parent("Cristian Ramirez", "cristian@correo.com", "123456", "310300301", User.Roles.PADRE, "Calle 123# 12-32"),
                new Parent("Andrea Perez", "andrea@correo.com", "123456", "310300302", User.Roles.PADRE, "Calle 45#12-34"),
                new Parent("Juliana Suarez", "juliana@correo.com", "123456", "310300303", User.Roles.PADRE, "Cra 1 #5-25")
        );
    }


    @Override
    public boolean validateEmailPassword(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User.Roles getRole(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user.getRole();
            }
        }

        return null;
    }

    @Override
    public void setLoginPresenter(LoginMVP.Presenter presenter) {
        this.loginPresenter = presenter;

    }


}




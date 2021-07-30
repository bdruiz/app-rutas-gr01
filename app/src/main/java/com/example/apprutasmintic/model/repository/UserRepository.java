package com.example.apprutasmintic.model.repository;

import androidx.annotation.NonNull;

import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.view.login.LoginMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class UserRepository implements LoginMVP.Model {

    private LoginMVP.Presenter loginPresenter;

    private List<User> users;

    private FirebaseAuth mAuth;

    public UserRepository() {
        users = Arrays.asList(
                new Assistant("Monica Jimenez", "monica@correo.com","123456","3000000000", User.Roles.MONITORA,105),
                new Assistant("Sara Fernadez", "sara@correo.com","123456","3000000001", User.Roles.MONITORA,102),
                new Parent("Cristian Ramirez", "cristian@correo.com", "123456", "310300301", User.Roles.PADRE, "Calle 123# 12-32"),
                new Parent("Andrea Perez", "andrea@correo.com", "123456", "310300302", User.Roles.PADRE, "Calle 45#12-34"),
                new Parent("Juliana Suarez", "juliana@correo.com", "123456", "310300303", User.Roles.PADRE, "Cra 1 #5-25")
        );

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void validateEmailPassword(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginPresenter.authenticationSuccessful();
                        } else {
                            loginPresenter.authenticationFailure(task.getException().getMessage());
                        }
                    }
                });
    }


    @Override
    public boolean isAuthenticated(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
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




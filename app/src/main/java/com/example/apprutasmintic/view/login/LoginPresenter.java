package com.example.apprutasmintic.view.login;

import android.util.Patterns;

import com.example.apprutasmintic.ActivityForgotPass;
import com.example.apprutasmintic.Monitor1;
import com.example.apprutasmintic.Padres1;
import com.example.apprutasmintic.view.padres.Padres1Activity;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.model.repository.UserRepository;

public class LoginPresenter implements LoginMVP.Presenter {


    private final LoginMVP.View view;
    private final LoginMVP.Model model;

    public LoginPresenter(LoginMVP.View view) {
        this.view = view;
        this.model = new UserRepository();
        this.model.setLoginPresenter(this);
    }

    public LoginPresenter(LoginMVP.View view, LoginMVP.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void login() {
        LoginInfo info = view.getLoginInfo();

        // Validar Datos
        if (info.getEmail() == null
                || info.getEmail().trim().isEmpty()) {
            view.showEmailError("Correo electronico vacio");

        } else if (!Patterns.EMAIL_ADDRESS.matcher(info.getEmail()).matches()) {
            view.showEmailError("Correo electronico no válido");

        } else if (info.getPassword() == null
                || info.getPassword().trim().isEmpty()) {
            view.showPasswordError("Contraseña vacia");

        } else if (info.getPassword().trim().length() < 6) {
            view.showPasswordError("Contraseña no válida");

        }

        model.validateEmailPassword(info.getEmail(), info.getPassword());
    }

    @Override
    public void forgotpwd() {
        view.showActivity(ActivityForgotPass.class);

    }

    @Override
    public void authenticate() {
        if (model.isAuthenticated()) {
            openactivity();
            //view.showActivity(PrincipalActivity.class);
        }
    }

    @Override
    public void authenticationSuccessful() {
        openactivity();
        //view.showActivity(PrincipalActivity.class);
    }

    @Override
    public void authenticationFailure(String message) {
        view.showErrorMessage(message);
    }

    public void openactivity() {
        LoginInfo info = view.getLoginInfo();
        User.Roles role = model.getRole(info.getEmail());
        switch (role) {
            case PADRE:
                view.showActivity(Padres1Activity.class);
                break;
            case MONITORA:
                view.showActivity(Monitor1.class);
                break;
            default:
                break;
        }

    }
}




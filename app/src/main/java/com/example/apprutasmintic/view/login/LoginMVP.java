package com.example.apprutasmintic.view.login;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apprutasmintic.model.entity.User;

public interface LoginMVP {

        interface Model {

            void validateEmailPassword(String email, String password);
            boolean isAuthenticated();
            User.Roles getRole(String email);
            void setLoginPresenter(Presenter presenter);
        }

        interface Presenter {

            void login();
            void forgotpwd();
            void authenticate();
            void authenticationSuccessful();
            void authenticationFailure(String message);

        }

        interface View {
            LoginInfo getLoginInfo();

            void showEmailError(String message);

            void showPasswordError(String message);

            void showActivity(Class<? extends AppCompatActivity> type);

            void showErrorMessage(String message);
        }



}

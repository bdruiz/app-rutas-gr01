package com.example.apprutasmintic.view.login;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apprutasmintic.model.entity.User;

public interface LoginMVP {

        interface Model {

            boolean validateEmailPassword(String email, String password);
            User.Roles getRole(String email);
            void setLoginPresenter(Presenter presenter);
        }

        interface Presenter {

            void login();
            void forgotpwd(); //este lo comenté y el metodo adentro del presenter también y no pasó nada
            //porque el boton olvidó su contraseña tiene el intent directo
        }

        interface View {
            LoginInfo getLoginInfo();

            void showEmailError(String message);

            void showPasswordError(String message);

            void showActivity(Class<? extends AppCompatActivity> type);

            void showErrorMessage(String message);
        }



}

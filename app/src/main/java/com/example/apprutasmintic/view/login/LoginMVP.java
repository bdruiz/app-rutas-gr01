package com.example.apprutasmintic.view.login;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.model.repository.UserRepository;
import com.google.android.gms.tasks.Task;

public interface LoginMVP {

        interface Model {

            void validateEmailPassword(String email, String password);
            boolean isAuthenticated();
            void setLoginPresenter(Presenter presenter);
            void readUserFirebase(final UserRepository.OnGetDataListener listener);
        }

        interface Presenter {

            void login();
            void forgotpwd();
            void authenticate();
            void authenticationSuccessful();
            void authenticationFailure(String message);

            void saveLocalData(Assistant assistant);
            void saveLocalData(Parent parent);

        }

        interface View {
            LoginInfo getLoginInfo();

            void showEmailError(String message);
            void showPasswordError(String message);
            void showActivity(Class<? extends AppCompatActivity> type);
            void showErrorMessage(String message);
            void showProgressBar();
            void dimissProgressBar();

            Context getContex();
            void finishactivity();
        }



}

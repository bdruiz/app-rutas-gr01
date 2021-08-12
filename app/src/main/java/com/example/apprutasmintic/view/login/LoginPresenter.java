package com.example.apprutasmintic.view.login;

import android.util.Log;
import android.util.Patterns;

import com.example.apprutasmintic.ActivityForgotPass;
import com.example.apprutasmintic.view.monitor.Monitor1;
import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.padres.Padres1Activity;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.model.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

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

        } else {
            model.validateEmailPassword(info.getEmail(), info.getPassword());

        }
    }

    @Override
    public void forgotpwd() {
        view.showActivity(ActivityForgotPass.class);

    }

    @Override
    public void authenticate() {
        if (model.isAuthenticated()) {
            openactivity();
            Log.i("AU", "AUTHENTICADO 1");

        }
    }

    @Override
    public void authenticationSuccessful() {
        //view.showActivity(PrincipalActivity.class);
        model.readUserFirebase(new UserRepository.OnGetDataListener() {
            @Override
            public void onStart() {
                view.showProgressBar();
            }

            @Override
            public void onSuccess(DataSnapshot snapshot) {


                String names = snapshot.child("name").getValue(String.class);
                String email = snapshot.child("email").getValue(String.class);
                String phone = snapshot.child("phone").getValue(String.class);
                String role = snapshot.child("role").getValue(String.class);


                if (role.toLowerCase().equals("parent")) {

                    String address = snapshot.child("address").getValue(String.class);
                    Parent parent = new Parent(names, email, phone, User.Roles.PADRE, address);
                    int student_id = snapshot.child("student_id").getValue(Integer.class);
                    Log.i("INFO", parent.toString());
                    saveLocalData(parent);


                } else if (role.toLowerCase().equals("assistant")) {

                    String id_route = snapshot.child("id_route").getValue(String.class);
                    Assistant assistant = new Assistant(names, email, phone, User.Roles.MONITORA, id_route);
                    Log.i("INFO", assistant.toString());
                    saveLocalData(assistant);


                }

                Log.i("AU", "SUCESSS AUTH");
                view.dimissProgressBar();
                view.finishactivity();
                openactivity();


            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.w("Firebase Database", "loadPost:onCancelled", databaseError.toException());
            }
        });


    }


    @Override
    public void authenticationFailure(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void saveLocalData(Assistant assistant) {
        UserSharedPreferences.setAllData(view.getContex(), assistant);
    }

    @Override
    public void saveLocalData(Parent parent) {
        UserSharedPreferences.setAllData(view.getContex(), parent);
    }

    public void openactivity() {

        User.Roles role = UserSharedPreferences.getRole(view.getContex());

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




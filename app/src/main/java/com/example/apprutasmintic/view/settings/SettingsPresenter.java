package com.example.apprutasmintic.view.settings;

import android.util.Log;

import androidx.preference.EditTextPreference;

import com.example.apprutasmintic.model.repository.UserRepository;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseError;

public class SettingsPresenter implements SettingsMVP.Presenter{

    private final SettingsMVP.View view;
    private final SettingsMVP.Model model;


    public SettingsPresenter(SettingsMVP.View view) {
        this.view = view;
        this.model = new UserRepository();
    }


    @Override
    public void logout() {
        view.confirmLogout();

    }

    @Override
    public void logoutConfirmed() {
        model.logout();
        view.showActivity(LoginActivity.class);


    }

    @Override
    public void updateAddrOk(String addr, EditTextPreference preferenceaddr) {
        model.updateAddressData(addr, new UserRepository.OnGetDataListenerUpdateAddr() {
            @Override
            public void onSuccess(Task task) {
                preferenceaddr.setText(addr);
                UserSharedPreferences.setAddress(preferenceaddr.getContext(), addr);
            }


            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.e("Firebase",databaseError+"");
            }
        });
    }

    @Override
    public void updateRouteIdOk(String route, EditTextPreference preferenceRoute) {
        model.updateRouteIdData(route, new UserRepository.OnGetDataListenerUpdateRoute() {
            @Override
            public void onSuccess(Task task) {
                preferenceRoute.setText(route);
                UserSharedPreferences.setRouteID(preferenceRoute.getContext(), route);

            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.e("Firebase",databaseError+"");

            }
        });

    }


    @Override
    public void updatepassOk(String oldpass, String newPass) {
        model.updatePassword(oldpass, newPass, new UserRepository.OnGetDataListenerUpdatepwd() {
            @Override
            public void onSuccess(Task task) {
                if(!task.isSuccessful()){
                    view.showMessage("Algo Fallo. Porfavor Intentelo mas Tarde");

                }else {
                    view.showMessage("Contraseña Actualizada con exito");

                }

            }

            @Override
            public void authfailed() {
                view.showMessage("Fallo de Autenticación. Contraseña Actual Incorrecta");

            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.e("Firebase",databaseError+"");

            }
        });

    }

}

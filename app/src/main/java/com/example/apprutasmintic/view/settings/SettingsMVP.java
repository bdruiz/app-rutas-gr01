package com.example.apprutasmintic.view.settings;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;

import com.example.apprutasmintic.model.repository.UserRepository;

public interface SettingsMVP {

    interface Model {

        void logout();
        void updateAddressData(String newAddress, UserRepository.OnGetDataListenerUpdateAddr listener);
        void updateRouteIdData(String newRoute, UserRepository.OnGetDataListenerUpdateRoute listener);
        void updatePassword(String oldpass, String newPass, UserRepository.OnGetDataListenerUpdatepwd listener);

    }

    interface Presenter {

        void logout();

        void logoutConfirmed();
        void updateAddrOk(String addr, EditTextPreference preference);

        void updateRouteIdOk(String strRoute, EditTextPreference signaturePreferenceRoute);
        void updatepassOk(String oldpass, String newPass);

    }

    interface View {

        Context getApplicationContext();

        void showActivity(Class<? extends AppCompatActivity> type);

        void confirmLogout();
         void showMessage(String msg);

    }

}

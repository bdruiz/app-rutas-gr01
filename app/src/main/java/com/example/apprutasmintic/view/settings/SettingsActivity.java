package com.example.apprutasmintic.view.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SettingsMVP.View {
        private SettingsMVP.Presenter presenter;


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            presenter = new SettingsPresenter(this);

            User.Roles role = UserSharedPreferences.getRole(getContext());
            EditTextPreference signaturePreferenceRoute, signaturePreferenceAddr;
            Preference logoutPreference;
            Preference updatePwdPrefrence;
            SwitchPreferenceCompat darkmodePrefence;


            if (role == User.Roles.MONITORA) {

                setPreferencesFromResource(R.xml.root_preferences_assistant, rootKey);
                signaturePreferenceRoute = findPreference("routeid");
                signaturePreferenceRoute.setText(UserSharedPreferences.getRouteID(getContext()));

                signaturePreferenceRoute.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        presenter.updateRouteIdOk(newValue.toString().trim(), signaturePreferenceRoute);
                        return false;
                    }
                });

            } else if (role == User.Roles.PADRE) {

                setPreferencesFromResource(R.xml.root_preferences_parent, rootKey);
                signaturePreferenceAddr = findPreference("address");
                signaturePreferenceAddr.setText(UserSharedPreferences.getAddress(getContext()));

                signaturePreferenceAddr.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        presenter.updateAddrOk(newValue.toString().trim(), signaturePreferenceAddr);

                        return false;
                    }
                });

            }


            logoutPreference = findPreference("logout");
            logoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {

                    presenter.logout();
                    return false;
                }
            });


            updatePwdPrefrence = findPreference("changepwd");
            updatePwdPrefrence.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {


                    showResetPasswordAlert();

                    return false;
                }
            });

            darkmodePrefence=findPreference("themedark");
            darkmodePrefence.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                 /*   Log.i("test",newValue.toString());
                    if (darkmodePrefence.isChecked()){
                        Log.i("test","check1");
                    }
                    else {
                        Log.i("test","check2");
                    }*/

                    boolean dark= (boolean) newValue;
                    Log.i("test",dark+"");
                    if(dark){
                        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        darkmodePrefence.setChecked(true);
                    }else{
                        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        darkmodePrefence.setChecked(false);

                    }

                    return false;
                }
            });


        }//End OnCreate


        public void showResetPasswordAlert() {


            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Actualizar Contraseña");


            View viewInflated = getLayoutInflater().inflate(R.layout.layout_update_pwd, null);
            //LayoutInflater.from(getContext()).inflate(R.layout.layout_update_pwd, null);
            builder.setView(viewInflated);

            final TextInputEditText input_old = viewInflated.findViewById(R.id.editTextoldPassword);
            final TextInputEditText input_new = viewInflated.findViewById(R.id.editTextnewPassword);
            final TextInputEditText input_new2 = viewInflated.findViewById(R.id.editTextrepnewPassword);


            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String old_pass, new_pass, new_pass2;

                    old_pass = input_old.getText().toString();
                    new_pass = input_new.getText().toString();
                    new_pass2 = input_new2.getText().toString();
                    if (old_pass.isEmpty()) {
                        showMessage("Campo Contraseña Actual Vacio");
                        Log.i("test", "vacio");
                        return;
                    }
                    if (new_pass.isEmpty()) {
                        showMessage("Campo Nueva Contraseña Vacio");
                        Log.i("test", "vacio2");
                        return;
                    }
                    if (new_pass2.isEmpty()) {
                        showMessage("Campo Repita Nueva Contraseña Vacio");
                        Log.i("test", "vacio3");
                        return;
                    }

                    if (!new_pass.equals(new_pass2)) {
                        showMessage("La nueva Contraseña No Coincide en ambos campos");
                        Log.i("test", "no son correctas");
                        return;
                    }
                    if (new_pass.length() < 6) {
                        showMessage("La nueva Contraseña es muy corta");
                        Log.i("test", "muy corta");
                        return;
                    }
                    if (new_pass.equals(old_pass)) {
                        showMessage("La nueva Contraseña no puede ser igual a la actual");
                        return;
                    }

                    Log.i("test", old_pass + " " + new_pass + " " + new_pass2);

                    presenter.updatepassOk(old_pass, new_pass);
                    dialog.dismiss();

                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

        }


        @Override
        public Context getApplicationContext() {
            return getContext();
        }

        @Override
        public void showActivity(Class<? extends AppCompatActivity> type) {
            Intent intent = new Intent(getApplicationContext(), type);
            startActivity(intent);
            ActivityCompat.finishAffinity(getActivity());
        }

        @Override
        public void confirmLogout() {


            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.app_name)
                    .setMessage("¿Está seguro en cerrar la sesión?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("which", which + "");

                            //TODO Pasar a MVP
                            UserSharedPreferences.deleteAllData(getContext());
                            presenter.logoutConfirmed();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        }

        @Override
        public void showMessage(String msg) {
            Snackbar snackbar_fail = Snackbar
                    .make(getView(), msg, Snackbar.LENGTH_LONG);
            snackbar_fail.show();

        }




    }
}

package com.example.apprutasmintic.view.monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apprutasmintic.model.repository.StudentRepository;
import com.example.apprutasmintic.model.repository.StudentSharedPreferences;
import com.example.apprutasmintic.view.monitor.Monitor2;
import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.example.apprutasmintic.view.padres.Padres1Activity;
import com.example.apprutasmintic.view.padres.Padres1Frag;
import com.example.apprutasmintic.view.padres.novedadFrag;
import com.example.apprutasmintic.view.settings.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;

import static java.security.AccessController.getContext;

public class Monitor1 extends AppCompatActivity {

    Button btnAColegio;
    Button btnDesdeColegio;
    TextView tv_names, tv_route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor1);

       // btnAColegio = findViewById(R.id.btnAColegio);
       // btnDesdeColegio = findViewById(R.id.btnDesdeColegio);
        tv_names = findViewById(R.id.textViewNombreMonitora);
        tv_route = findViewById(R.id.textViewRutaMonitora);
        tv_names.setText(UserSharedPreferences.getNames(this));
        tv_route.setText("Ruta: " + UserSharedPreferences.getRouteID(this));
        StudentRepository s=new StudentRepository();
        s.countStudents(this);
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkmode = sharedPreferences.getBoolean("themedark",false);


        //setDarkmode(darkmode);




    }



    public void btnCerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        UserSharedPreferences.deleteAllData(this);
        finish();
        Intent intent = new Intent(Monitor1.this, LoginActivity.class);
        startActivity(intent);

    }

    public void onClick(View view) {
        FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.CardRutaColegio: {
                Intent intent = new Intent(Monitor1.this, Monitor2.class);
                intent.putExtra("modo", "hacia");
                startActivity(intent);
                break;
            }
            case R.id.CardRutaCasa: {
                Intent intent = new Intent(Monitor1.this, Monitor2.class);
                intent.putExtra("modo", "desde");
                startActivity(intent);
                break;
            }
          /*  case R.id.btnCerrarSesion: {
                FirebaseAuth.getInstance().signOut();
                UserSharedPreferences.deleteAllData(this);
                finish();
                Intent intent = new Intent(Monitor1.this, LoginActivity.class);
                startActivity(intent);
            }*/

        }
    }
    public void openSettings(View view) {
        Intent intent = new Intent(Monitor1.this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_route.setText("Ruta: " + UserSharedPreferences.getRouteID(this));
    }


    public void setDarkmode(boolean var){
        if(var){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            tv_route.setTextColor(ContextCompat.getColor(this, R.color.whiteTextColor));
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            tv_route.setTextColor(ContextCompat.getColor(this, R.color.black));
        }


    }
}
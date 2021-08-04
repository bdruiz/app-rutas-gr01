package com.example.apprutasmintic.view.monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apprutasmintic.Monitor2;
import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Monitor1 extends AppCompatActivity {

    Button btnAColegio;
    Button btnDesdeColegio;
    TextView tv_names, tv_route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor1);

        btnAColegio = findViewById(R.id.btnAColegio);
        btnDesdeColegio = findViewById(R.id.btnDesdeColegio);
        tv_names = findViewById(R.id.textViewNombreMonitora);
        tv_route = findViewById(R.id.textViewRutaMonitora);
        tv_names.setText(UserSharedPreferences.getNames(this));
        tv_route.setText("Ruta: " + UserSharedPreferences.getRouteID(this));


        btnAColegio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Monitor1.this, Monitor2.class);
                intent.putExtra("modo", "hacia");
                startActivity(intent);
            }
        });
        btnDesdeColegio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Monitor1.this, Monitor2.class);
                intent.putExtra("modo", "desde");
                startActivity(intent);
            }
        });
    }

    public void cerrarsesion(View view) {
        FirebaseAuth.getInstance().signOut();
        UserSharedPreferences.deleteAllData(this);
        finish();
        Intent intent = new Intent(Monitor1.this, LoginActivity.class);
        startActivity(intent);

    }

}
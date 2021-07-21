package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apprutasmintic.R;

public class MainActivity extends AppCompatActivity {


    Button btnLogin;
    Button btnfgtPass;
    EditText etMail;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnfgtPass = findViewById(R.id.btnFgtPass);
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Mail = etMail.getText().toString().trim();
                String Pass = etPass.getText().toString().trim();

                //Acá voy a revisar los datos con las bases de datos y ver qué actividad se abrirá
                //mientras tanto voy a hacer Padres: mail:padre Pass: padre
                //monitor: mail: moni Pass: moni

                if (Mail.equals("padre") && Pass.equals("padre")) {
                    Intent intent = new Intent(MainActivity.this, Padres1.class);
                    startActivity(intent);
                }
                else if (Mail.equals("moni") && Pass.equals("moni")) {
                    Intent intent = new Intent(MainActivity.this, Monitor1.class);
                    startActivity(intent);

                } else {
                    Toast msj = Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
                    msj.show();
                }


            }
        });
        btnfgtPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityForgotPass.class);
                startActivity(intent);

            }
        });


    }
}
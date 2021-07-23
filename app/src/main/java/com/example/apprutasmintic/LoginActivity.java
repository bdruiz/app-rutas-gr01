package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etMail;
    EditText etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etMail = findViewById(R.id.editTextEmail);
        etPass = findViewById(R.id.editTextPassword);
    }

    public void onLoginClick(View view) {

        String Mail = etMail.getText().toString().trim();
        String Pass = etPass.getText().toString().trim();

        //Acá voy a revisar los datos con las bases de datos y ver qué actividad se abrirá
        //mientras tanto voy a hacer Padres: mail:padre Pass: padre
        //monitor: mail: moni Pass: moni

        if (Mail.equals("padre") && Pass.equals("padre")) {
            Intent intent = new Intent(LoginActivity.this, Padres1.class);
            startActivity(intent);
        }
        else if (Mail.equals("moni") && Pass.equals("moni")) {
            Intent intent = new Intent(LoginActivity.this, Monitor1.class);
            startActivity(intent);

        } else {
            Toast msj = Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
            msj.show();
        }

    }

    public void forgot_pass(View view) {
        Intent intent = new Intent(LoginActivity.this, ActivityForgotPass.class);
        startActivity(intent);
    }
}
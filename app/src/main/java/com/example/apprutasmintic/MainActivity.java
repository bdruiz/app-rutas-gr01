package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apprutasmintic.view.monitor.Monitor1;

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




    }
}
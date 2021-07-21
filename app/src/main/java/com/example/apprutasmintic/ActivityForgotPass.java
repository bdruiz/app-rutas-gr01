package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apprutasmintic.R;

public class ActivityForgotPass extends AppCompatActivity {

    Button btnSubmitForgot;
    EditText etMailForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        etMailForgot = findViewById(R.id.etMailForgot);
        btnSubmitForgot = findViewById(R.id.btnSubmitForgot);

        btnSubmitForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail = etMailForgot.getText().toString().trim();
                //esto ya lo mando a la base de datos para que se mande la contraseña al correo
            }
        });
    }
}
package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apprutasmintic.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityForgotPass extends AppCompatActivity {

    Button btnSubmitForgot;
    TextInputEditText etMailForgot;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        etMailForgot = findViewById(R.id.etMailForgot);
        btnSubmitForgot = findViewById(R.id.btnSubmitForgot);
        mAuth = FirebaseAuth.getInstance();

        btnSubmitForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail = etMailForgot.getText().toString().trim();
                //TODO Se valida que correo exista, no sea nulo y se pasa a MVP

                mAuth.sendPasswordResetEmail(Mail);
                Toast toast = Toast.makeText(getApplicationContext(), "Contraseña enviada al correo ", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                finish();
            }
        });
    }
}
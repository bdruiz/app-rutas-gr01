package com.example.apprutasmintic.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apprutasmintic.ActivityForgotPass;
import com.example.apprutasmintic.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {
    TextInputLayout tilMail, tilPass;
    TextInputEditText tieMail, tiePass;
    ImageView imgbtn;
    private LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        tilPass.setError("");
        tilMail.setError("");

    }


    private void initUI() {
        presenter = new LoginPresenter(this);
        tilMail = findViewById(R.id.textInputEmail);
        tieMail = findViewById(R.id.editTextEmail);
        tiePass = findViewById(R.id.editTextPassword);
        tilPass = findViewById(R.id.textInputPassword);
        imgbtn = findViewById(R.id.imgbtn);


    }


    public void onLoginClick(View view) {
        // Presentador llama el metodo iniciarSesion()
        tilMail.setError("");
        tilPass.setError("");
        presenter.login();
    }

    public void forgot_pass(View view) {
        Intent intent = new Intent(LoginActivity.this, ActivityForgotPass.class);
        startActivity(intent);
    }

    @Override
    public LoginInfo getLoginInfo() {
        return new LoginInfo(
                tieMail.getText().toString().trim(),
                tiePass.getText().toString().trim());
    }

    @Override
    public void showEmailError(String message) {
        tilMail.setError(message);
        //Toast msj = Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT);
        //msj.show();

    }

    @Override
    public void showPasswordError(String message) {
        tilPass.setError(message);
        //Toast msj = Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT);
        //msj.show();


    }

    @Override
    public void showActivity(Class<? extends AppCompatActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);

    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(imgbtn, message, BaseTransientBottomBar.LENGTH_SHORT).show();
        //  Toast msj = Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT);
        // msj.show();

    }
}
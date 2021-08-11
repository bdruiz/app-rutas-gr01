package com.example.apprutasmintic.view.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apprutasmintic.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {
    TextInputLayout tilMail, tilPass;
    TextInputEditText tieMail, tiePass;
    ImageView imgbtn;
    AlertDialog.Builder builder;
    AlertDialog progressDialog;
    private LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        tilPass.setError("");
        tilMail.setError("");

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.authenticate();
        }

    private void initUI() {
        presenter = new LoginPresenter(this);
        tilMail = findViewById(R.id.textInputEmail);
        tieMail = findViewById(R.id.editTextEmail);
        tiePass = findViewById(R.id.editTextPassword);
        tilPass = findViewById(R.id.textInputPassword);
        imgbtn = findViewById(R.id.imgbtn);
        progressDialog = getDialogProgressBar().create();



    }


    public void onLoginClick(View view) {
        // Presentador llama el metodo iniciarSesion()
        tilMail.setError("");
        tilPass.setError("");
        presenter.login();


    }

    public void forgot_pass(View view) {
        /*Intent intent = new Intent(LoginActivity.this, ActivityForgotPass.class);
        startActivity(intent);*/
        presenter.forgotpwd();
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
        Intent intent = new Intent(LoginActivity.this, type);
        startActivity(intent);
        Log.i("INFO", "ABRIO ACTIVIDAD");

    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(imgbtn, message, BaseTransientBottomBar.LENGTH_SHORT).show();
        //  Toast msj = Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT);
        // msj.show();

    }

    @Override
    public void showProgressBar() {
        progressDialog.show();

    }

    @Override
    public void dimissProgressBar() {

        progressDialog.dismiss();
    }



    @Override
    public Context getContex() {
        return this ;
    }

    @Override
    public void finishactivity() {
        finish();
    }


    public AlertDialog.Builder getDialogProgressBar() {

        if (builder == null) {
            builder = new AlertDialog.Builder(this);

            builder.setTitle(R.string.loading);

            final ProgressBar progressBar = new ProgressBar(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            progressBar.setLayoutParams(lp);
            builder.setView(progressBar);
        }
        return builder;
    }

}
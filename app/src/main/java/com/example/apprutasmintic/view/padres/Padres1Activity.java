package com.example.apprutasmintic.view.padres;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.padres.novedadFrag;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;


public class Padres1Activity extends AppCompatActivity implements Padres1MVP.View {

    TextView tv_Names, tv_Address;
    FrameLayout lytFragmentPadres1;
    EditText etNovedad;
    Button btnEnviarNovedad;
    Button btnCancelNovedad;


    private Padres1MVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padres1);
        getSupportFragmentManager().beginTransaction().add(R.id.lytFragmentPadres1, new Padres1Frag()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUI();//esto toca ponerlo acá para que los fragment carguen despues del onCreate de la actividad
    }

    private void initUI() {
        presenter = new Padres1Presenter(this);
        lytFragmentPadres1 = findViewById(R.id.lytFragmentPadres1);
        tv_Address = findViewById(R.id.textViewDireccionPadres);
        tv_Names = findViewById(R.id.textViewNombrePadres);
        btnEnviarNovedad = findViewById(R.id.btnEnviarNovedad);
        tv_Names.setText(UserSharedPreferences.getNames(this));
        tv_Address.setText(UserSharedPreferences.getAddress(this));

    }

    public void onSeguirRutaClick(View view) {
        presenter.openActivityPadres2();
    }

    @Override
    public void showActivity(Class<? extends AppCompatActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);

    }

    public void onWhatsappMonitora(View view) {
        presenter.whatsMonitora();
    }

    @Override
    public void openIntentSendWhatApp(String url, String phone) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url + phone));
        startActivity(i);
    }

    public void cerrarsesion(View view) {
        //TODO PASAR A MVP
        FirebaseAuth.getInstance().signOut();
        UserSharedPreferences.deleteAllData(this);
        finish();
        Intent intent = new Intent(Padres1Activity.this, LoginActivity.class);
        startActivity(intent);


    }



    /* @Override
     public void onBackPressed() {
     finish();
     }
     */
    public void onClick(View view) {
        FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btnGenerarNovedad: {
                tf.replace(R.id.lytFragmentPadres1, new novedadFrag());
                tf.addToBackStack(null);
                tf.commit();
                break;
            }
            case R.id.btnCancelNovedad: {
                tf.replace(R.id.lytFragmentPadres1, new Padres1Frag());
                tf.addToBackStack(null);
                tf.commit();
                onStart(); //esto es para que la tarjeta de persona no vuelva a basico
                break;
            }
            case R.id.btnEnviarNovedad: {
                tf.replace(R.id.lytFragmentPadres1, new Padres1Frag());
                tf.addToBackStack(null);
                tf.commit();
                etNovedad = findViewById(R.id.etNovedad); //este toca ponerlo acá porque antes del replace no existe
                presenter.generarNovedad(this,etNovedad);
                onStart();//esto es para que la tarjeta de persona no vuelva a basico
                break;
            }

        }
    }
}
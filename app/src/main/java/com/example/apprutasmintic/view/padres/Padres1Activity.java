package com.example.apprutasmintic.view.padres;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentContainerView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.novedadFrag;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;


public class Padres1Activity extends AppCompatActivity implements Padres1MVP.View, novedadFrag.NOVEDAD {

    Button btnSRuta;
    Button btnGNovedad;
    Button btnWMonitora;
    TextView tv_Names, tv_Address;


    FragmentContainerView lytFragmentNovedad;
    LinearLayout lytActiPadres1;

    EditText etNovedad;
    Button btnEnviarNovedad;
    Button btnCancelNovedad;


    private Padres1MVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padres1);
        Log.d("paso", "empece oncreate");
        initUI();
        Log.d("paso", "termine oncreate");

    }

    private void initUI() {
        presenter = new Padres1Presenter(this);
        btnSRuta = findViewById(R.id.btnSeguirRuta);
        btnGNovedad = findViewById(R.id.btnGenerarNovedad);
        btnWMonitora = findViewById(R.id.btnWappMonitora);
        tv_Address = findViewById(R.id.textViewDireccionPadres);
        tv_Names = findViewById(R.id.textViewNombrePadres);
        tv_Names.setText(UserSharedPreferences.getNames(this));
        tv_Address.setText(UserSharedPreferences.getAddress(this));



        lytFragmentNovedad = findViewById(R.id.lytFragmentNovedad);
        lytActiPadres1 = findViewById(R.id.lytActiPadres1);

        etNovedad = findViewById(R.id.etNovedad);
        btnEnviarNovedad = findViewById(R.id.btnEnviarNovedad);

        btnSRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Padres1Activity.this, Padres2.class);
                startActivity(intent);
            }
        });

        btnWMonitora.setOnClickListener(new View.OnClickListener() {



            public void onClick(View v) {
                presenter.sendWhatsAppMonitora();
            }
        });




    }

    public void onSeguirRutaClick(View view) {
        presenter.openActivityPadres2();
    }
    @Override
    public void showActivity(Class<? extends AppCompatActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);

    }

    @Override
    public void openIntentSendWhatApp(String url, String phone) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url+phone));
        startActivity(i);
    }

    @Override
    public void onStringSend(String data) {

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

}

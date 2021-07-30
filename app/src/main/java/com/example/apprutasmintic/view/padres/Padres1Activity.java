package com.example.apprutasmintic.view.padres;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apprutasmintic.Padres2;
import com.example.apprutasmintic.R;
import com.example.apprutasmintic.novedadFrag;


public class Padres1Activity extends AppCompatActivity implements Padres1MVP.View, novedadFrag.NOVEDAD {

    Button btnSRuta;
    Button btnGNovedad;
    Button btnWMonitora;
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

    }

    public void onSeguirRutaClick(View view) {
        presenter.openActivityPadres2();
    }

    public void showActivity(Class<? extends AppCompatActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);

    }

    @Override
    public void onStringSend(String data) {

    }
}

package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.apprutasmintic.R;

public class Padres1 extends AppCompatActivity implements novedadFrag.NOVEDAD {

    Button btnWappMonitora;
    Button btnGenerarNovedad;
    Button btnSeguirRuta;
    FragmentContainerView lytFragmentNovedad;
    LinearLayout lytActiPadres1;

    EditText etNovedad;
    Button btnEnviarNovedad;
    Button btnCancelNovedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padres1);

        btnWappMonitora = findViewById(R.id.btnWappMonitora);
        btnGenerarNovedad = findViewById(R.id.btnGenerarNovedad);
        btnSeguirRuta = findViewById(R.id.btnSeguirRuta);
        lytFragmentNovedad = findViewById(R.id.lytFragmentNovedad);
        lytActiPadres1 = findViewById(R.id.lytActiPadres1);

        etNovedad = findViewById(R.id.etNovedad);
        btnEnviarNovedad = findViewById(R.id.btnEnviarNovedad);

        btnWappMonitora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=" + "573057035528";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        btnSeguirRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Padres1.this, Padres2.class);
                startActivity(intent);
            }
        });

        btnGenerarNovedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acá debo hacer que se abra el fragment de generar novedad a pantalla completa
                FragmentManager manager = getSupportFragmentManager();
                lytFragmentNovedad.setVisibility(View.VISIBLE);
                manager.beginTransaction()
                        .show(manager.findFragmentById(R.id.lytFragmentNovedad))
                        .addToBackStack(null)
                        .commit();

                lytActiPadres1.setVisibility(View.GONE);


            }
        });

    }

    @Override
    public void onStringSend(String data) {
        Log.d("Click listener", "ya en Padres1 se recibió el data " + data);
        //{Acá se manda esa info a la data base y poner un toast de novedad enviada}
        FragmentManager manager = getSupportFragmentManager();
        lytFragmentNovedad.setVisibility(View.GONE);
        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.lytFragmentNovedad))
                .addToBackStack(null)
                .commit();
        lytActiPadres1.setVisibility(View.VISIBLE);

    }
}
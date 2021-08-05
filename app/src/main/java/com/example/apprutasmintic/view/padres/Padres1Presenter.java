package com.example.apprutasmintic.view.padres;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentContainerView;
import com.example.apprutasmintic.novedadFrag;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.view.padres.Padres1Model;

import java.util.zip.Inflater;

public class Padres1Presenter extends AppCompatActivity implements Padres1MVP.Presenter {


    private final Padres1MVP.View view;
    private final Padres1MVP.Model model;

    public Padres1Presenter(Padres1MVP.View view) {
        this.view = view;
        this.model = new Padres1Model();
    }

    public Padres1Presenter(Padres1MVP.View view, Padres1MVP.Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void openActivityPadres2() {
        //Log.d("openActivityPadres2", "abriendo el presenter");
        view.showActivity(Padres2.class);
    }

    @Override
    public void whatsMonitora() {
        String telefono = model.numeroMonitora();
        Log.d("jeisson", "telefono es: " + telefono);
        view.openIntentSendWhatApp("https://api.whatsapp.com/send?phone=", telefono);
    }

    @Override
    public void generarNovedad(FragmentContainerView lytFragmentNovedad, LinearLayout lyt) {
        //debo hacer aparecer el fragment y desaparecer el layout
        FragmentManager manager = getSupportFragmentManager();
        Log.d("jeisson", " voy a hacerlos visibles e invisibles ");
        lytFragmentNovedad.setVisibility(View.VISIBLE);
        lyt.setVisibility(View.GONE);
        Log.d("jeisson", " aca ya lo hice ");
        //manager.beginTransaction().add(R.id.lytFragmentNovedad, manager.findFragmentById(R.id.fragment_novedad) ,"TagName").commit();
        /*manager.beginTransaction()
                .show(manager.findFragmentById(R.id.lytFragmentNovedad))
                .addToBackStack(null)
                .commit();*/


    }


}

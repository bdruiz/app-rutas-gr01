package com.example.apprutasmintic.view.padres;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.example.apprutasmintic.R;

public class Padres1Presenter extends AppCompatActivity implements Padres1MVP.Presenter {


    private final Padres1MVP.View view;
    private final Padres1MVP.Model model;
    EditText etNovedad;

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
    public void generarNovedad(Padres1MVP.View view,EditText etNovedad) {
        String novedad = etNovedad.getText().toString().trim();
        model.subirNovedad(novedad);
    }


    @Override
    public void whatsMonitora() {
        String telefono = model.numeroMonitora();
        Log.d("jeisson", "telefono es: " + telefono);
        view.openIntentSendWhatApp("https://api.whatsapp.com/send?phone=", telefono);
    }


}

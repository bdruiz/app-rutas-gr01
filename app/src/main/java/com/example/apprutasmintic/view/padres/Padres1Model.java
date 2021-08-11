package com.example.apprutasmintic.view.padres;

import android.util.Log;

import com.example.apprutasmintic.view.padres.Padres1MVP;

public class Padres1Model implements Padres1MVP.Model{

    public Padres1Model(){

    }
    @Override
    public String numeroMonitora() {
        String telefono = "573057035528";
        return telefono;
    }
    @Override
    public void subirNovedad(String novedad) {
        Log.d("jei bug", "padres1Model: acá debo subir a base de datos la sgte novedad: "+novedad);

    }

}

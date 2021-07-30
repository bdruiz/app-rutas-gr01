package com.example.apprutasmintic.view.padres;

import android.util.Log;
import com.example.apprutasmintic.Padres2;

public class Padres1Presenter implements Padres1MVP.Presenter {


    private final Padres1MVP.View view;
    //private final Padres1MVP.Model model;


    public Padres1Presenter(Padres1MVP.View view) {
        this.view = view;
        //this.model = new Padres1Model();

    }

    public Padres1Presenter(Padres1MVP.View view, Padres1MVP.Model model) {
        this.view = view;
       // this.model = model;
    }


    @Override
    public void openActivityPadres2() {
        //Log.d("openActivityPadres2", "abriendo el presenter");
        view.showActivity(Padres2.class);
    }


}

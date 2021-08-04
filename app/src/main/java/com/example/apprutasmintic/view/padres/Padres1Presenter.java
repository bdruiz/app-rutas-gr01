package com.example.apprutasmintic.view.padres;

import android.content.Intent;
import android.net.Uri;

public class Padres1Presenter implements Padres1MVP.Presenter {


    private final Padres1MVP.View view;
    // private final Padres1MVP.Model model;


    public Padres1Presenter(Padres1MVP.View view) {
        this.view = view;

    }


    @Override
    public void openActivityPadres2() {
        //Log.d("openActivityPadres2", "abriendo el presenter");
        view.showActivity(Padres2.class);
    }

    @Override
    public void sendWhatsAppMonitora() {
        view.openIntentSendWhatApp("https://api.whatsapp.com/send?phone=", "573057035528");
    }


}

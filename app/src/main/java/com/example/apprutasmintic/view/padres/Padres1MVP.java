package com.example.apprutasmintic.view.padres;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apprutasmintic.model.entity.User;

public interface Padres1MVP {


    interface Model {

        //metodos de la clase model
    }

    interface Presenter {

        void openActivityPadres2();
        void sendWhatsAppMonitora();

    }

    interface View {

        void showActivity(Class<? extends AppCompatActivity> type);
        void openIntentSendWhatApp(String url, String phone);

    }


}



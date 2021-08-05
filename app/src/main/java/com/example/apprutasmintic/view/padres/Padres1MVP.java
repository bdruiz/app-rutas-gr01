package com.example.apprutasmintic.view.padres;

import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.apprutasmintic.model.entity.User;

public interface Padres1MVP {


    interface Model {

        String numeroMonitora();
    }

    interface Presenter {

        void openActivityPadres2();

        void generarNovedad(FragmentContainerView lytFragment, LinearLayout lyt);

        void whatsMonitora();
    }

    interface View {

        void showActivity(Class<? extends AppCompatActivity> type);

        void openIntentSendWhatApp(String url, String phone);

    }


}



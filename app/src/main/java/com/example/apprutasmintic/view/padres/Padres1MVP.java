package com.example.apprutasmintic.view.padres;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.apprutasmintic.model.entity.User;

public interface Padres1MVP {


    interface Model {

        String numeroMonitora();
        void subirNovedad(String novedad);
    }

    interface Presenter {

        void openActivityPadres2();

        void generarNovedad(View view,EditText etNovedad);

        void whatsMonitora();
    }

    interface View {

        void showActivity(Class<? extends AppCompatActivity> type);
        void openIntentSendWhatApp(String url, String phone);

    }


}



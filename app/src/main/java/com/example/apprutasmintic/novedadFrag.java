package com.example.apprutasmintic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.apprutasmintic.R;


public class novedadFrag extends Fragment {

 Button  btnEnviarNovedad;

    public novedadFrag() {

    }


    public static novedadFrag newInstance() {
        novedadFrag fragment = new novedadFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       /* btnEnviarNovedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acá ya debo recoger los datos del EditText y enviarlos a la base de datos
                getActivity().onBackPressed();
            }
        });*/


        return inflater.inflate(R.layout.fragment_novedad, container, false);
    }
}
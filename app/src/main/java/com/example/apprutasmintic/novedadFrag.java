package com.example.apprutasmintic;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.apprutasmintic.R;

import java.util.zip.Inflater;


public class novedadFrag extends Fragment {

    NOVEDAD strNovedad;

    public interface NOVEDAD {
        void onStringSend(String data);
    }

    public novedadFrag() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        strNovedad = (NOVEDAD) context;
        //esto es lo que me hace comunicar este frag con MainActivity,
        //en donde debe haber un metodo itemSelected
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflater1 = inflater.inflate(R.layout.fragment_novedad, container, false);
        return inflater1;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText etNovedad = view.findViewById(R.id.etNovedad);
        Button btnEnviarNovedad = view.findViewById(R.id.btnEnviarNovedad);
        Log.d("Click listener", "Voy a ejecutar el click" + etNovedad.getText().toString());
        btnEnviarNovedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click listener", "Estoy ejecutando el click");
                String data = etNovedad.getText().toString();
                Aceptar(data);
            }
        });
    }


    public void Aceptar(String data) {
        Log.d("Click listener", "Entre a Aceptar con el String "+ data.toString());
        strNovedad.onStringSend(data);
    }
}
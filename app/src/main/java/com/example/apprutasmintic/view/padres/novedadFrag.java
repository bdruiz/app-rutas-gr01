package com.example.apprutasmintic.view.padres;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.apprutasmintic.R;

public class novedadFrag extends Fragment {
    com.example.apprutasmintic.novedadFrag.NOVEDAD strNovedad;

    /*public interface NOVEDAD {
        void onStringSend(String data);
    }*/

    public novedadFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //strNovedad = (com.example.apprutasmintic.novedadFrag.NOVEDAD) context;
        //esto es lo que me hace comunicar este frag con MainActivity,
        //en donde debe haber un metodo itemSelected
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("jeisson", " voy a hacer el inflater ");
        View inflater1 = inflater.inflate(R.layout.fragment_novedad, container, false);
        return (LinearLayout)inflater1;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
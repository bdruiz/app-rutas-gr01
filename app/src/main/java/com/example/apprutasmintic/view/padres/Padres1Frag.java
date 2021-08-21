package com.example.apprutasmintic.view.padres;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;


public class Padres1Frag extends Fragment {
    //TODO PASAR LOS PROCEDIMIENTOS A CADA UNO DE LOS FRAGMENTOS
    TextView tv_Names, tv_Address;
    public Padres1Frag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_padres1, container, false);

        tv_Address = rootView.findViewById(R.id.textViewDireccionPadres);
        tv_Names = rootView.findViewById(R.id.textViewNombrePadres);
        tv_Names.setText(UserSharedPreferences.getNames(getContext()));
        tv_Address.setText(UserSharedPreferences.getAddress(getContext()));
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getContext() );
        boolean darkmode = sharedPreferences.getBoolean("themedark",false);


        //setDarkmode(darkmode);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tv_Address.setText(UserSharedPreferences.getAddress(getContext()));

    }


    public void setDarkmode(boolean var){
        if(var){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            tv_Address.setTextColor(ContextCompat.getColor(getContext(), R.color.whiteTextColor));
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            tv_Address.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
}
}
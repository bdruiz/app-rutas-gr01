package com.example.apprutasmintic.view.padres;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apprutasmintic.R;


public class Padres1Frag extends Fragment {

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
        View rootView = (LinearLayout)inflater.inflate(R.layout.fragment_padres1, container, false);
        return rootView;
    }
}
package com.example.apprutasmintic.view.padres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.apprutasmintic.R;

public class Padres2 extends AppCompatActivity {


    Button btnCancelSeguirRuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padres2);
        btnCancelSeguirRuta = findViewById(R.id.btnCancelSeguirRuta);


    }
}
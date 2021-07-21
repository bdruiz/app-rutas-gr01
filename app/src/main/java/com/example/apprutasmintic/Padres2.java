package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apprutasmintic.R;

public class Padres2 extends AppCompatActivity {


    Button btnCancelSeguirRuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padres2);
        btnCancelSeguirRuta = findViewById(R.id.btnCancelSeguirRuta);

        btnCancelSeguirRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Padres2.this, Padres1.class);
                Padres2.this.finish();
            }
        });
    }
}
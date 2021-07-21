package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apprutasmintic.R;

public class Monitor1 extends AppCompatActivity {

    Button btnAColegio;
    Button btnDesdeColegio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor1);

        btnAColegio = findViewById(R.id.btnAColegio);
        btnDesdeColegio = findViewById(R.id.btnDesdeColegio);

        btnAColegio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Monitor1.this, Monitor2.class);
                intent.putExtra("modo", "hacia");
                startActivity(intent);
            }
        });
        btnDesdeColegio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Monitor1.this, Monitor2.class);
                intent.putExtra("modo", "desde");
                startActivity(intent);
            }
        });
    }
}
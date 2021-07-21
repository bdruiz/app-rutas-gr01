package com.example.apprutasmintic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apprutasmintic.R;

public class Monitor2 extends AppCompatActivity {

    ImageView ivLastOne;
    ImageView ivNextOne;
    Button btnTerminarRuta;
    TextView tvTipoViaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor2);

        String modo = getIntent().getStringExtra("modo");
        ivLastOne = findViewById(R.id.ivLastOne);
        ivNextOne = findViewById(R.id.ivNextOne);
        tvTipoViaje=findViewById(R.id.texTituloCamino);
        btnTerminarRuta = findViewById(R.id.btnTerminarRuta);
        if (modo.equals("hacia")){
            tvTipoViaje.setText(tvTipoViaje.getText()+" El colegio");
        }else if (modo.equals("desde")){
            tvTipoViaje.setText(tvTipoViaje.getText()+" Casa");
        }

        ivLastOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acá debo mover los datos del fragment para que muestre la anterior tarjeta
            }
        });
        ivNextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //acá mocer los datos del fragment para que se muestre la sgte tarjeta
            }
        });
        btnTerminarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //terminar ruta y escribirles un mensaje a todos los padres
                //toca cerrar esta actividad y cerrar la base de datos
            }
        });

    }
}
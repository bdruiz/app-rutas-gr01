package com.example.apprutasmintic.view.monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.example.apprutasmintic.view.padres.Padres1Presenter;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Monitor2 extends AppCompatActivity {

    FrameLayout flStudentCard;
    lectorTXT lector;
    private List<String> mLines;
    JSONObject jsonEstudiantes;
    TextView tvEstudianteName;
    TextView tvEstudianteAdress;
    Button ivNextOne;
    Button ivLastOne;
    int posicion = 0;
    JSONArray listaEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor2);
        //este se va a un if que me va a dar el orden de lectura de la lista de estudiantes
        String modo = getIntent().getStringExtra("modo");
        getSupportFragmentManager().beginTransaction().add(R.id.flStudentCard, new estudiantesFrag()).commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            initUI();//esto toca ponerlo acá para que los fragment carguen despues del onCreate de la actividad
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initUI() throws JSONException {
        flStudentCard = findViewById(R.id.flStudentCard);
        tvEstudianteName = findViewById(R.id.tvEstudianteName);
        tvEstudianteAdress = findViewById(R.id.tvEstudianteAdress);

        lector = new lectorTXT(this);
        String full = lector.readfull("estudiantes.txt");
        try {
            jsonEstudiantes = new JSONObject(full);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listaEstudiantes = jsonEstudiantes.getJSONArray("students"); //esta es una lista de objetosJson
        cardRefresh(listaEstudiantes.getJSONObject(posicion)); //acá pongo mi estudiante 0
        //listaEstudiantes.getJSONObject(1).get("names");
    }

    public void onClick(View view) throws JSONException {

        switch (view.getId()) {
            case R.id.ivLastOne: {
                posicion -= 1;
                JSONObject newStudent = listaEstudiantes.getJSONObject(posicion);
                cardRefresh(newStudent);
                break;
            }
            case R.id.ivNextOne: {
                posicion += 1;
                JSONObject newStudent = listaEstudiantes.getJSONObject(posicion);
                cardRefresh(newStudent);
                break;
            }

        }
    }

    public void cardRefresh(JSONObject newStudent) throws JSONException {
        tvEstudianteName.setText(newStudent.get("names").toString());
        tvEstudianteAdress.setText(newStudent.get("address").toString());
    }
}

package com.example.apprutasmintic.view.monitor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.entity.Student;
import com.example.apprutasmintic.model.repository.StudentRepository;
import com.example.apprutasmintic.model.repository.StudentSharedPreferences;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.example.apprutasmintic.view.padres.Padres1Presenter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Monitor2 extends AppCompatActivity {
    TextView tvTipoViaje;
    FrameLayout flStudentCard;
    lectorTXT lector;
    private List<String> mLines;
    JSONObject jsonEstudiantes;
    TextView tvEstudianteName;
    TextView tvEstudianteAdress;
    TextView tvEstudianteTelTutor;
    TextView tvEstudianteNovedad;
    TextView tvEstudiantecuenta;
    Button ivNextOne;
    Button ivLastOne;
    Button btnpresente, btnausente, btnterminar;
    Student studentCard;
    ImageView imgEstudiante;
    StudentRepository studentRepo;
    String modo;

    int posicion = 0;
    JSONArray listaEstudiantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor2);

        getSupportFragmentManager().beginTransaction().add(R.id.flStudentCard, new estudiantesFrag()).commit();
        studentCard = new Student();
        studentRepo = new StudentRepository();

        tvTipoViaje = findViewById(R.id.texTituloCamino);
        modo = getIntent().getStringExtra("modo");
        if (modo.equals("hacia")) {
            tvTipoViaje.append(" El colegio");
            StudentSharedPreferences.setIDCurrentStudent(this, 1);
        } else if (modo.equals("desde")) {
            tvTipoViaje.append(" Casa");
            StudentSharedPreferences.setIDCurrentStudent(this, 5);
        }


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
        tvEstudianteTelTutor = findViewById(R.id.tvEstudianteTelefonoTutor);
        tvEstudianteNovedad = findViewById(R.id.tvEstudianteNovedad);
        tvEstudiantecuenta = findViewById(R.id.textViewEstudianteCuenta);
        btnausente = findViewById(R.id.btnausente);
        btnpresente = findViewById(R.id.btnpresente);
        imgEstudiante = findViewById(R.id.imageViewIMGEstudiante);

        // imgEstudiante = findViewById(R.id.imageViewIMGEstudiante);
        loadStudent(StudentSharedPreferences.getIDCurrentStudent(this));


        // btnausente.setOnClickListener((View.OnClickListener) this);


        //  listaEstudiantes = jsonEstudiantes.getJSONArray("students"); //esta es una lista de objetosJson
        // cardRefresh(listaEstudiantes.getJSONObject(posicion)); //acá pongo mi estudiante 0
        //listaEstudiantes.getJSONObject(1).get("names");
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivLastOne:
            /*    posicion -= 1;
                JSONObject newStudent = listaEstudiantes.getJSONObject(posicion);
                cardRefresh(newStudent);
                btnausente.setEnabled(true);
                btnpresente.setEnabled(true);
                */
                if (modo.equals("hacia")) {
                    moveBackward();

                } else if (modo.equals("desde")) {
                    moveForward();
                }

                break;

            case R.id.ivNextOne:
                /*
                posicion += 1;
                JSONObject newStudent = listaEstudiantes.getJSONObject(posicion);
                cardRefresh(newStudent);

                 */
                // int i =;
                // i++;

                if (modo.equals("hacia")) {
                    moveForward();

                } else if (modo.equals("desde")) {
                    moveBackward();
                }


                break;
        }


    }

    public void moveForward() {
        if (StudentSharedPreferences.getIDCurrentStudent(this) < StudentSharedPreferences.getNumberStudents(this)) {
            StudentSharedPreferences
                    .setIDCurrentStudent(
                            this,
                            StudentSharedPreferences.getIDCurrentStudent(this) + 1);
            Log.i("SHARED", StudentSharedPreferences.getIDCurrentStudent(this) + "");
            loadStudent(StudentSharedPreferences.getIDCurrentStudent(this));
        } else {
            if (modo.equals("hacia")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Fin de la Lista");
                builder.setMessage("Ha llegado al último estudiante");
                builder.setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    public void moveBackward() {

        if (StudentSharedPreferences.getIDCurrentStudent(this) > 1) {
            StudentSharedPreferences.setIDCurrentStudent(
                    this,
                    StudentSharedPreferences.getIDCurrentStudent(this) - 1);
            Log.i("SHARED", StudentSharedPreferences.getIDCurrentStudent(this) + "");
            loadStudent(StudentSharedPreferences.getIDCurrentStudent(this));
        } else {
            if (modo.equals("desde")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Fin de la Lista");
                builder.setMessage("Ha llegado al último estudiante");
                builder.setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }


    public void cardRefresh() {
        tvEstudianteName.setText(studentCard.getNames());
        tvEstudianteAdress.setText(studentCard.getAddress());
        tvEstudianteTelTutor.setText("Télefono Tutor: " + studentCard.getPhone_tutor());
        tvEstudianteNovedad.setText("Novedad: " + studentCard.getAdvice_note());
        showStudentPhoto(studentCard.getPath_photo());

        if (modo.equals("hacia")) {
            tvEstudiantecuenta.setText(
                    StudentSharedPreferences.getIDCurrentStudent(this)
                            + "/" + StudentSharedPreferences.getNumberStudents(this));
        } else if (modo.equals("desde")) {
            tvEstudiantecuenta.setText(
                    (StudentSharedPreferences.getNumberStudents(this) -
                            StudentSharedPreferences.getIDCurrentStudent(this)) + 1
                            + "/" + StudentSharedPreferences.getNumberStudents(this));
        }


    }

    public void terminar(View view) {


        Log.i("TEST", "Terminar RUTA");


        //   btnterminar.setEnabled(false);
    }


    public void loadStudent(int idStudent) {

        studentRepo.readStudent(idStudent, new StudentRepository.OnGetStudentListener() {
            @Override
            public void onSuccess(Student student) {
                studentCard = student;
                Log.i("NUMERO DE ESTU", "" + StudentSharedPreferences.getNumberStudents(getApplicationContext()));
                Log.i("SA", "" + studentCard.toString());
                //tvEstudianteAdress.setText(student.getNames());
                cardRefresh();
            }
        });

    }

    public void showStudentPhoto(String url) {
        Glide.with(this)
                .load(url)
                .fitCenter()
                //          .centerCrop()
                .into(imgEstudiante);
    }

}



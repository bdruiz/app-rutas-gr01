package com.example.apprutasmintic.view.padres;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.example.apprutasmintic.view.ValidarAsistenciaActivity;
import com.example.apprutasmintic.view.map.MapActivity;
import com.example.apprutasmintic.view.login.LoginActivity;
import com.example.apprutasmintic.view.monitor.Monitor1;
import com.example.apprutasmintic.view.settings.SettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Padres1Activity extends AppCompatActivity implements Padres1MVP.View {

    TextView tv_Names, tv_Address;
    FrameLayout lytFragmentPadres1;
    TextInputEditText etNovedad;
    Button btnEnviarNovedad;
    Button btnCancelNovedad;


    private Padres1MVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padres1);
        getSupportFragmentManager().beginTransaction().add(R.id.lytFragmentPadres1, new Padres1Frag()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUI();//esto toca ponerlo acá para que los fragment carguen despues del onCreate de la actividad

    }

    private void initUI() {
        presenter = new Padres1Presenter(this);
        lytFragmentPadres1 = findViewById(R.id.lytFragmentPadres1);


        // btnEnviarNovedad = findViewById(R.id.btnEnviarNovedad);


    }

    public void onSeguirRutaClick(View view) {
        //TODO pasar a MVP
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
        //presenter.openActivityPadres2();
    }

    @Override
    public void showActivity(Class<? extends AppCompatActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);

    }

    public void onWhatsappMonitora(View view) {
        presenter.whatsMonitora();
    }

    @Override
    public void openIntentSendWhatApp(String url, String phone) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url + phone));
        startActivity(i);
    }

    public void cerrarsesion(View view) {
        //TODO PASAR A MVP
        FirebaseAuth.getInstance().signOut();
        UserSharedPreferences.deleteAllData(this);
        finish();
        Intent intent = new Intent(Padres1Activity.this, LoginActivity.class);
        startActivity(intent);


    }

    public void abrirAsistencia(View view) {
        Intent intent = new Intent(Padres1Activity.this, ValidarAsistenciaActivity.class);
        startActivity(intent);


    }


    /* @Override
     public void onBackPressed() {
     finish();
     }
     */
    public void onClick(View view) {
        FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.CardGenerarNovedad:
                tf.replace(R.id.lytFragmentPadres1, new novedadFrag());
                tf.addToBackStack(null);
                tf.commit();
                break;

            case R.id.btnCancelNovedad:
                Log.i("5555", "CANCEL NOVEDAD");
              //  tf.replace(R.id.lytFragmentPadres1, new Padres1Frag());
               // tf.addToBackStack(null);
                //tf.commit();
                //onStart(); //esto es para que la tarjeta de persona no vuelva a basico
                finish();
                break;

            case R.id.btnEnviarNovedad:
                Log.i("5555", "BTN NOVEDAD");
                // tf.replace(R.id.lytFragmentPadres1, new Padres1Frag());
                // tf.addToBackStack(null);
                //tf.commit();

                 etNovedad = findViewById(R.id.etNovedad); //este toca ponerlo acá porque antes del replace no existe
                String novedad = etNovedad.getText().toString().trim();
                escribirNovedad(novedad);

                // presenter.generarNovedad(this,etNovedad);
                // onStart();//esto es para que la tarjeta de persona no vuelva a basico

                break;


        }
    }

    public void escribirNovedad(String novedad){
        //TODO PASAR A MVP
        if(!novedad.equals("") && novedad.length()<=100){
            DatabaseReference studentsRef = FirebaseDatabase.getInstance().getReference("students");
            DatabaseReference currentStudentRef = studentsRef.child(String.valueOf(UserSharedPreferences.getStudentID(this)));
            currentStudentRef.child("advice_note").setValue(novedad).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull  Task<Void> task) {
                    //TODO verificar que si se escriba la novedad
                }
            });

            Toast.makeText(this,"Novedad Generada con exito", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this,"Novedad Vacia o Supera los limites",Toast.LENGTH_SHORT).show();
        }


    }

    public void openSettings(View view) {
        Intent intent = new Intent(Padres1Activity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
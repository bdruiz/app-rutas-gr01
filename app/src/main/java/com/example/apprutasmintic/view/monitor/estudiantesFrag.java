package com.example.apprutasmintic.view.monitor;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.AttendanceRepository;
import com.example.apprutasmintic.model.repository.StudentRepository;
import com.example.apprutasmintic.model.repository.StudentSharedPreferences;


public class estudiantesFrag extends Fragment implements View.OnClickListener {

Button btnausente;
Button btnpresente;
TextView tvpresente, tvausente;
TextView tvTipoViaje;
ImageView imgviaje;
    AttendanceRepository attenRepo;
String modo;

    public estudiantesFrag() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("jeisson", " voy a hacer el inflater ");
        View inflater1 = inflater.inflate(R.layout.fragment_student_card, container, false);
        tvTipoViaje = inflater1.findViewById(R.id.texTituloCamino);
        btnpresente = inflater1.findViewById(R.id.btnpresente);
        btnpresente.setOnClickListener(this);
        btnausente = inflater1.findViewById(R.id.btnausente);
        btnausente.setOnClickListener(this);
        attenRepo=new AttendanceRepository();
        imgviaje=inflater1.findViewById(R.id.imgmodoviaje);

        modo = this.getArguments().getString("modo");
        if (modo.equals("hacia")) {
            tvTipoViaje.append(" El colegio");
            imgviaje.setImageResource(R.drawable.colegio);
            StudentSharedPreferences.setIDCurrentStudent(getContext(), 1);
        } else if (modo.equals("desde")) {
            tvTipoViaje.append(" Casa");
            imgviaje.setImageResource(R.drawable.casa);
            StudentSharedPreferences.setIDCurrentStudent(getContext(), 5);
        }
        Log.i("modo", modo);
        return inflater1;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnpresente){
            btnpresente.setEnabled(false);
            btnausente.setEnabled(true);
          //  btnpresente.setBackgroundColor(Color.parseColor("#11CFC5"));

            if (modo.equals("hacia")){
                attenRepo.writeAttendance(
                        StudentSharedPreferences.getIDtudent(getContext()),
                        "MORNING",
                        true);
                Log.i("FECHA", ""+attenRepo.dateToUnix());
                Log.i("TEST", "PRESENTE");

            }

         else if (modo.equals("desde")){
                attenRepo.writeAttendance(
                        StudentSharedPreferences.getIDtudent(getContext()),
                        "AFTERNOON",
                        true);
                Log.i("FECHA", "" + attenRepo.dateToUnix());
                Log.i("TEST", "PRESENTE");

        }


        }else if(view.getId()==R.id.btnausente){
            btnausente.setEnabled(false);
            btnpresente.setEnabled(true);

            if (modo.equals("hacia")){
                attenRepo.writeAttendance(
                        StudentSharedPreferences.getIDtudent(getContext()),
                        "MORNING",
                        false);
                Log.i("TEST", "auSENTE");

            } else if (modo.equals("desde")){
                attenRepo.writeAttendance(
                        StudentSharedPreferences.getIDtudent(getContext()),
                        "AFTERNOON",
                        false);
                Log.i("TEST", "auSENTE");
            }


        }
    }


}
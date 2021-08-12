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
import android.widget.LinearLayout;

import com.example.apprutasmintic.R;


public class estudiantesFrag extends Fragment implements View.OnClickListener {

Button btnausente;
Button btnpresente;

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

        btnpresente = inflater1.findViewById(R.id.btnpresente);
        btnpresente.setOnClickListener(this);
        btnausente = inflater1.findViewById(R.id.btnausente);
        btnausente.setOnClickListener(this);
        return (LinearLayout)inflater1;
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
            Log.i("TEST", "PRESENTE");

        }else if(view.getId()==R.id.btnausente){

            btnausente.setEnabled(false);
            btnpresente.setEnabled(true);
            Log.i("TEST", "auSENTE");
        }
    }


}
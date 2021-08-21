package com.example.apprutasmintic.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ValidarAsistenciaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    AsistenciaAdapter adapter;
    List<AsistenciaInfo> data=new ArrayList<AsistenciaInfo>();

    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_asistencia);

        mbase = FirebaseDatabase.getInstance().getReference();

        recyclerView = findViewById(R.id.recyclerasistencia);


        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        Log.i("data","aca llego");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference studentIdRef = rootRef.child("attendance").child(String.valueOf(UserSharedPreferences.getStudentID(this)));
        //DatabaseReference currentStudentRef = mbase.child("attendance").child(String.valueOf(UserSharedPreferences.getStudentID(this)));
        studentIdRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // AsistenciaInfo asistencia2=new AsistenciaInfo();
                for(DataSnapshot snap : snapshot.getChildren()){
                    AsistenciaInfo asistencia1=new AsistenciaInfo();

                    //   Map<String, String> map  = (Map) snap.getValue();

                    //Log.i("MAP", map.toString());
                   Log.i("data",snap.toString());
                    //Log.i("data",snapshot.child("").getValue(String.class));

                    Log.i("key", snap.getRef().getKey());
                    asistencia1.setDate(Long.parseLong(snap.getKey()));
                    Log.i("sd", snap.child("morning").getValue(boolean.class)+"");
                    asistencia1.setAttendad_Morning(snap.child("morning").getValue(boolean.class));
                    if(snap.hasChild("afternoon")){
                        Log.i("sd", snap.child("afternoon").getValue(boolean.class)+"");
                        asistencia1.setAttendad_Afternoon(snap.child("afternoon").getValue(boolean.class));
                    }


                 //   Log.i("data",snapshot.child("1628571600").child("morning").getValue(Boolean.class)+"");
                   // Log.i("data",snapshot.child("morning").getValue(String.class));
                   // Log.i("data",     snapshot.child("afternoon").getValue(String.class));


                   // if(snapshot.hasChild()){
                   //     asistencia1.setAttendad(snapshot.child("morning").getValue(boolean.class));
                       // asistencia1.setFecha(Long.getLong(snapshot.getValue(String.class)));
                       // asistencia1.setTrip("MAÑANA");
                        Log.i("data_Object",asistencia1.toString());
                        data.add(asistencia1);
                    Log.i("SUB TOTAL", data.toString());
                //    }else if(snapshot.hasChild("afternoon")){
                   /*     asistencia2.setAttendad(snapshot.child("afternoon").getValue(boolean.class));
                        asistencia2.setFecha(Long.parseLong(snapshot.getKey()));
                        asistencia2.setTrip("TARDE");
                        Log.i("data",asistencia1.toString());
                        data.add(asistencia2);

                    */
                  //  }



                }
Log.i("TOTAL", data.toString());
                adapter = new AsistenciaAdapter(data);
                recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
       /* data = Arrays.asList(
                new AsistenciaInfo("Mañana", 12072021, true),
                new AsistenciaInfo("Tarde", 12072021, false),
                new AsistenciaInfo("Mañana", 13072021, true),
                new AsistenciaInfo("Tarde", 13072021, false),
                new AsistenciaInfo("Mañana", 14072021, false),
                new AsistenciaInfo("Tarde", 14072021, true),
                new AsistenciaInfo("Mañana", 15072021, true),
                new AsistenciaInfo("Tarde", 15072021, true),
                new AsistenciaInfo("Mañana", 16072021, true),
                new AsistenciaInfo("Tarde", 16072021, true),
                new AsistenciaInfo("Mañana", 17072021, true),
                new AsistenciaInfo("Tarde", 17072021, true),
                new AsistenciaInfo("Mañana", 18072021, true),
                new AsistenciaInfo("Tarde", 18072021, null)
                );*/







    }
}
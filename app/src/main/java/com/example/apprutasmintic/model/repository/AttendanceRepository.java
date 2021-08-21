package com.example.apprutasmintic.model.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apprutasmintic.model.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceRepository {

    public DatabaseReference dbRef;
    public long att_date;
    public String trip;

    public AttendanceRepository() {

    }


    public void writeAttendance(int id_student, String day_trip, boolean atten) {
        //TODO ORANIZAR CON GET Y SET
        att_date = dateToUnix();
        trip = day_trip;
        DatabaseReference attendancesRef = FirebaseDatabase.getInstance().getReference("attendance");
        DatabaseReference currentStudentRef = attendancesRef.child(String.valueOf(id_student));
        dbRef = currentStudentRef;

       //NO ES NECESARIO CONSULTAR SI EXISTE SIMPLEMENTE SE CREA LA RUTA COMPLETA, si ya existe se reemplza
  //2      dbRef.child(String.valueOf(att_date)).addListenerForSingleValueEvent(new ValueEventListener() {
 //2           @Override
//2            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.exists()) {
                    // Existe la fecha
                    if (trip.equals("MORNING")) {
                        dbRef.child(String.valueOf(att_date)).child("morning").setValue(atten);
                    } else if (trip.equals("AFTERNOON")) {
                        dbRef.child(String.valueOf(att_date)).child("afternoon").setValue(atten);
                    }

//                } else {
                    // si no existe la fecha,  se crea la fechax.


                  //   currentStudentRef.child(String.valueOf(att_date)).setValue(att_date);


                    /*

                    currentStudentRef.setValue(att_date).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (trip.equals("MORNING")) {
                                        dbRef.child(String.valueOf(att_date)).child("morning").setValue(atten);
                                    } else if (trip.equals("AFTERNOON")) {
                                        dbRef.child(String.valueOf(att_date)).child("afternoon").setValue(atten);
                                    }
                                }
                            });
*/
//                }

//2            }

//2            @Override
//2            public void onCancelled(DatabaseError error) {

//2            }
//2        });


        //mDatabase.child("users").child(userId).setValue(user);
    }

    public long dateToUnix() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date currrent_date = new Date();
        String str_dt = sdf.format(currrent_date);
        //String str_dt = "10-08-2021";
        Date date_whitout_time = null;
        try {
            date_whitout_time = sdf.parse(str_dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long unixTime = date_whitout_time.getTime() / 1000;

        return unixTime;
    }
    public String UnixToStr(long timeStamp) {

        java.util.Date time=new java.util.Date((long)timeStamp*1000);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


        String str_dt = sdf.format(time);


        return str_dt;
    }


}

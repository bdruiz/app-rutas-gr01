package com.example.apprutasmintic.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apprutasmintic.model.entity.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentRepository {


    public interface OnGetStudentListener {



        public void onSuccess(Student student);

    }


    public StudentRepository() {
    }



    public void readStudent(int id, OnGetStudentListener listener) {


        DatabaseReference studentsRef = FirebaseDatabase.getInstance().getReference("students");
        DatabaseReference currentStudentRef = studentsRef.child(String.valueOf(id));

        currentStudentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               listener.onSuccess(snapshot.getValue(Student.class));
                // Student s =new Student();
                // snapshot.getValue(s.getClass());
               // Log.i("test", s.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void countStudents(Context context) {

        DatabaseReference studentsRef = FirebaseDatabase.getInstance().getReference("students");
        studentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i(snapshot.getKey(), snapshot.getChildrenCount() + "");

             /*  for (DataSnapshot snap: snapshot.getChildren()) {
                   Log.i(snap.getKey(),snap.toString());
               }
                */
               int count = (int) snapshot.getChildrenCount();
                StudentSharedPreferences.setNumberStudents(context,count);



            }

                @Override
                public void onCancelled (@NonNull DatabaseError error){

                }
            });

        }
    }


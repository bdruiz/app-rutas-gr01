package com.example.apprutasmintic.model.repository;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.entity.User;


public class StudentSharedPreferences {


    private static final String PREF_NUMBER_STUDENTS = "N_STUDENTS";
    private static final String PREF_CURRENT_STUDENT = "CURRENT_STUDENT";
    private static final String PREF_ID_STUDENT = "ID_STUDENT";


    static SharedPreferences getSP(Context ctx) {
        return ctx.getSharedPreferences("DataUserLocal", Context.MODE_PRIVATE);
    }

    public static void setIDtudent(Context ctx, int id) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putInt(PREF_ID_STUDENT, id);
        editor.commit();
    }

    public static int getIDtudent(Context ctx) {
        return getSP(ctx).getInt(PREF_ID_STUDENT,0);
    }


    public static void setNumberStudents(Context ctx, int number) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putInt(PREF_NUMBER_STUDENTS, number);
        editor.commit();
    }

    public static int getNumberStudents(Context ctx) {
        return getSP(ctx).getInt(PREF_NUMBER_STUDENTS,0);
    }

    public static void setIDCurrentStudent(Context ctx, int id) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putInt(PREF_CURRENT_STUDENT, id);
        editor.commit();
    }

    public static int getIDCurrentStudent(Context ctx) {
        return getSP(ctx).getInt(PREF_CURRENT_STUDENT,0);
    }



    public static void deleteAllData(Context ctx) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.clear();
        editor.commit();
    }
}

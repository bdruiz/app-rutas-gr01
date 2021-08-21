package com.example.apprutasmintic.model.repository;


import android.content.SharedPreferences;
import android.content.Context;


import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.entity.User;


public class UserSharedPreferences {


    private static final String PREF_UNAMES = "NAMES";
    private static final String PREF_UEMAIL = "EMAIL";
    private static final String PREF_UROLE = "ROLE";
    private static final String PREF_UPHONE = "PHONE";
    private static final String PREF_UADDRESS = "ADDRESS";
    private static final String PREF_UROUTE_ID = "ROUTEID";
    private static final String PREF_USTUDENT_ID = "STUDENTID";


    static SharedPreferences getSP(Context ctx) {
        return ctx.getSharedPreferences("DataUserLocal", Context.MODE_PRIVATE);
    }


    public static void setNames(Context ctx, String Name) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(PREF_UNAMES, Name);
        editor.commit();
    }


    public static String getNames(Context ctx) {
        return getSP(ctx).getString(PREF_UNAMES, "");
    }


    public static void setEmail(Context ctx, String email) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(PREF_UEMAIL, email);
        editor.commit();
    }

    public static String getEmail(Context ctx) {
        return getSP(ctx).getString(PREF_UEMAIL, "");
    }


    public static void setRole(Context ctx, String role) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(PREF_UROLE, role);
        editor.commit();
    }

    public static User.Roles getRole(Context ctx) {
        return User.Roles.valueOf(getSP(ctx).getString(PREF_UROLE, ""));
    }

    public static void setPhone(Context ctx, String phone) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(PREF_UPHONE, phone);
        editor.commit();
    }

    public static String getPhone(Context ctx) {
        return getSP(ctx).getString(PREF_UPHONE, "");
    }

    public static void setAddress(Context ctx, String address) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(PREF_UADDRESS, address);
        editor.commit();
    }

    public static String getAddress(Context ctx) {
        return getSP(ctx).getString(PREF_UADDRESS, "");
    }

    public static void setRouteID(Context ctx, String id) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putString(PREF_UROUTE_ID, id);
        editor.commit();
    }

    public static String getRouteID(Context ctx) {
        return getSP(ctx).getString(PREF_UROUTE_ID, "");
    }

    public static void setStudentID(Context ctx, int id) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.putInt(PREF_USTUDENT_ID, id);
        editor.commit();
    }

    public static int getStudentID(Context ctx) {
        return getSP(ctx).getInt(PREF_USTUDENT_ID, 0);
    }


    public static void setAllData(Context context, Parent parent) {

        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString(PREF_UNAMES, parent.getNames());
        editor.putString(PREF_UEMAIL, parent.getEmail());
        editor.putString(PREF_UPHONE, parent.getPhone_number());
        editor.putString(PREF_UROLE, String.valueOf(parent.getRole()));
        editor.putString(PREF_UADDRESS, parent.getAddress());
        editor.putInt(PREF_USTUDENT_ID,parent.getId_student());
        editor.commit();

    }

    public static void setAllData(Context context, Assistant assistant) {

        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString(PREF_UNAMES, assistant.getNames());
        editor.putString(PREF_UEMAIL, assistant.getEmail());
        editor.putString(PREF_UPHONE, assistant.getPhone_number());
        editor.putString(PREF_UROLE, String.valueOf(assistant.getRole()));
        editor.putString(PREF_UROUTE_ID, assistant.getId_route());

        editor.commit();

    }



    public static void deleteAllData(Context ctx) {
        SharedPreferences.Editor editor = getSP(ctx).edit();
        editor.clear();
        editor.commit();
    }
}

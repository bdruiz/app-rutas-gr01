package com.example.apprutasmintic.model.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.apprutasmintic.model.entity.Assistant;
import com.example.apprutasmintic.model.entity.Parent;
import com.example.apprutasmintic.model.entity.User;
import com.example.apprutasmintic.view.login.LoginMVP;
import com.example.apprutasmintic.view.settings.SettingsMVP;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Arrays;
import java.util.List;

public class UserRepository implements LoginMVP.Model, SettingsMVP.Model {

    private LoginMVP.Presenter loginPresenter;

    private FirebaseAuth mAuth;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void validateEmailPassword(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginPresenter.authenticationSuccessful();
                        } else {
                            loginPresenter.authenticationFailure(task.getException().getMessage());
                        }
                    }
                });
    }


    @Override
    public boolean isAuthenticated() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }


    @Override
    public void setLoginPresenter(LoginMVP.Presenter presenter) {
        this.loginPresenter = presenter;

    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void updateAddressData(String newAddress, OnGetDataListenerUpdateAddr listener) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference currentUserRef = usersRef.child(uid);

        currentUserRef.child("address").setValue(newAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isComplete()) {
                    listener.onSuccess(task);
                }
            }
        });


    }

    @Override
    public void updateRouteIdData(String newRoute, UserRepository.OnGetDataListenerUpdateRoute listener) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference currentUserRef = usersRef.child(uid);

        currentUserRef.child("id_route").setValue(newRoute).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isComplete()) {
                    listener.onSuccess(task);
                }
            }
        });

    }

    @Override
    public void updatePassword(String oldpass, String newPass, OnGetDataListenerUpdatepwd listener) {

        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();
        AuthCredential credential = EmailAuthProvider.getCredential(email,oldpass);

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            listener.onSuccess(task);
                        }
                    });

                }else {
                    listener.authfailed();

                }
            }
        });


    }


    @Override
    public void readUserFirebase(final OnGetDataListener listener) {
        listener.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference currentUserRef = usersRef.child(uid);

        currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                listener.onFailed(error);

            }
        });

    }


    public interface OnGetDataListener {
        //Se Implementa la interfaz para poder manejar las solicitudes de Firebase de manera Asincrona

        public void onStart();

        public void onSuccess(DataSnapshot snapshot);

        public void onFailed(DatabaseError databaseError);
    }

    public interface OnGetDataListenerUpdateAddr {
        public void onSuccess(Task task);
        public void onFailed(DatabaseError databaseError);
    }


    public interface OnGetDataListenerUpdateRoute {
        public void onSuccess(Task task);
        public void onFailed(DatabaseError databaseError);
    }


    public interface OnGetDataListenerUpdatepwd {
        public void onSuccess(Task task);
        public void authfailed();
        public void onFailed(DatabaseError databaseError);
    }
}




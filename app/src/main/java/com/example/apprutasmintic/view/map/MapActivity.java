package com.example.apprutasmintic.view.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apprutasmintic.R;
import com.example.apprutasmintic.model.repository.UserSharedPreferences;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //LocationRepository repository;
    private GoogleMap mMap;
    TextView tvnombre;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvnombre=findViewById(R.id.tvMapaNombreEst);
        getstudentData();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // TODO Que vamos a hacer con el mapa

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        LatLng bogota = new LatLng(4.7111876,-74.0323043);
        mMap.addMarker(new MarkerOptions().position(bogota));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, 18f));
    }

   public void getstudentData(){
        //TODO PASAR A MVP
       DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
       DatabaseReference studentIdRef = rootRef.child("students").child(String.valueOf(UserSharedPreferences.getStudentID(this)));
       studentIdRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               nombre=snapshot.child("names").getValue(String.class);
               tvnombre.append(nombre);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }
}
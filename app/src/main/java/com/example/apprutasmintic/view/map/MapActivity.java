package com.example.apprutasmintic.view.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //LocationRepository repository;
    private GoogleMap mMap;
    TextView tvnombre;
    String nombre;
    LatLng marker;
    ArrayList<LatLng> positions = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvnombre = findViewById(R.id.tvMapaNombreEst);
        getstudentData();
        getUpdateGeoData();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // TODO Que vamos a hacer con el mapa

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        LatLng bogota = new LatLng(4.7111876, -74.0323043);

      //  mMap.addMarker(new MarkerOptions().position(bogota));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, 13f));


    }

    public void getstudentData() {
        //TODO PASAR A MVP
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference studentIdRef = rootRef.child("students").child(String.valueOf(UserSharedPreferences.getStudentID(this)));
        studentIdRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nombre = snapshot.child("names").getValue(String.class);
                tvnombre.append(nombre);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void getUpdateGeoData(/*double lat, double longi*/) {
        //TODO PASAR A MVP
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference routeRef = rootRef.child("tracking").child("XYZ420");

        routeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double lat = snapshot.child("latitude").getValue(double.class);
                double lon = snapshot.child("longitude").getValue(double.class);

                Log.i("GEO", "CAMBIO DE POSICION" + lat + " " + lon);
                marker = new LatLng(lat, lon);
                positions.add(marker);

                Polyline polyline1 = mMap.addPolyline(new PolylineOptions().
                        addAll(positions));
                mMap.addMarker(new MarkerOptions().position(marker));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 18f));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("FIRE_GEO", error.toString());
            }
        });
    }


    private void AddMarkerToGoogleMap() {
        //Opcion para actualizar mapa cada x tiempo

        Toast.makeText(this, "Marker 1 will update in 5 second", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatLng bogota = new LatLng(4.7111876, -74.0323043);

                mMap.addMarker(new MarkerOptions().position(bogota));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bogota, 18f));
            }
        }, 5000);
    }

}
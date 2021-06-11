package com.example.studyroad_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_interface);

        configureTimerButton();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng Patras = new LatLng(38.246362, 21.734835);
        map.addMarker(new MarkerOptions().position(Patras).title("Patras"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Patras));
    }

    //Button that returns to timer screen
    public void configureTimerButton(){
        Button mapButton = (Button) findViewById(R.id.timerButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static void addMapPin(){
        //add a new map pin

        LatLng newMarker = new LatLng(38.355994, 21.750249);
        map.addMarker(new MarkerOptions().position(newMarker).title("Progress"));
    }
}

package com.example.sunnyfeng.nycforall;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

public class WomenMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int zoomAmount = 5;
    private LatLngBounds NYC = new LatLngBounds(
            new LatLng(40,-75), new LatLng(42, -73));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps);
        setContentView(R.layout.activity_womenmaps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       */

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(),9));

        LatLng nyc = new LatLng(40.7, -73.9);
        Marker temp1 = mMap.addMarker(new MarkerOptions().position(nyc).title("Name of Place").snippet("The event that happened here."));
        temp1.setTag(0);

        LatLng nyc1 = new LatLng(30, -73);
        Marker temp2 = mMap.addMarker(new MarkerOptions().position(nyc1).title("Second Name of Place").snippet("The event that happened here."));
        temp2.setTag(0);

        LatLng nyc2 = new LatLng(40, -73);
        Marker temp3 = mMap.addMarker(new MarkerOptions().position(nyc2).title("Third Name of Place").snippet("The event that happened here."));
        temp2.setTag(0);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(),zoomAmount));

        final Button zoomIn = findViewById(R.id.plus_button);
        zoomIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //CameraUpdateFactory.zoomIn();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(),zoomAmount+1));
                zoomAmount++;
            }
        });

        final Button zoomOut = findViewById(R.id.minus_button);
        zoomOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(),zoomAmount-1));
                zoomAmount--;
            }
        });

        final Button zoomReset = findViewById(R.id.reset_button);
        zoomReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(),zoomAmount));
            }
        });
    }



}

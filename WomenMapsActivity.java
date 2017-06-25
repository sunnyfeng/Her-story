package com.example.sunnyfeng.nycforall;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

public class WomenMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    Marker gandhi = null;
    Marker museum = null;
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


        /*
           HISTORICAL
         */

        LatLng nyc = new LatLng(40.7, -73.9);
        Marker temp1 = mMap.addMarker(new MarkerOptions()
                .position(nyc)
                .title("Name of Place")
                .snippet("The event that happened here.")
                .icon(getMarkerIcon("#7DE5F1"))
        );
        temp1.setTag(0);


        /*
           CURRENT
         */

        LatLng gandhiLoc = new LatLng(40.7729,-73.9701);
        gandhi = mMap.addMarker(new MarkerOptions()
                .position(gandhiLoc)
                .title("Madame Gandhi at Basement Bhangra")
                .snippet("Click for more info.")
                .icon(getMarkerIcon("#9AFFC5"))
        );
        gandhi.setTag(0);

        LatLng museumLoc = new LatLng(40.66435, -73.9529);
        museum = mMap.addMarker(new MarkerOptions()
                .position(museumLoc)
                .title("Museum of Women's Resistance")
                .snippet("Click for more info.")
                .icon(getMarkerIcon("#7DE5F1"))

        );
        museum.setTag(0);

        //zooming
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(),zoomAmount));

        /*
            BUTTONS
         */
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
        mMap.setOnInfoWindowClickListener(this);
    }

    public BitmapDescriptor getMarkerIcon(String hexCode){
        float[] colors = new float[3];
        Color.colorToHSV(Color.parseColor(hexCode),colors);
        return BitmapDescriptorFactory.defaultMarker(colors[0]);
    }

    public void onInfoWindowClick(Marker marker){
        //Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();

        if (marker.equals(gandhi)) {
            new AlertDialog.Builder(this).setMessage("Madame Gandhi is a DJ and activist whose music promotes female " +
                    "empowerment and raises awareness of gender equality issues, known for songs " +
                    "including \"Her\" and \"The Future is Female\". She and other " +
                    "select artists will be performing live for Basement Bhangra's 20th " +
                    "Anniversary.")
                    .setTitle("Madame Gandhi at the Basement Bhangra 20th Anniversary Party")
                    .setCancelable(false)
                    .setNeutralButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                }
                            })
                    .setPositiveButton("More Info",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intentGandhi = new Intent();
                                    intentGandhi.setAction(Intent.ACTION_VIEW);
                                    intentGandhi.addCategory(Intent.CATEGORY_BROWSABLE);
                                    intentGandhi.setData(Uri.parse("http://www.cityparksfoundation.org/event/basement-bhangra-20th-anniversary-apache-indian-panjabi-mc-dj-rekha-madame-gandhi-anik-khan-horsepowar-sikh-knowledge-dj-petra-dj-shilpa/"));
                                    startActivity(intentGandhi);
                                }
                            }
                    ).show();
        } else if (marker.equals(museum)){
            new AlertDialog.Builder(this).setMessage("The Museum of Women's Resistance (MoWRe) is a museum " +
                    "that brings awareness to the diversity and influence of women of African " +
                    "descent. MoWRe promotes the intersectionality of a wide array of social aspects, " +
                    "including race, social status, gender, and culture in relation to women all around" +
                    " the world. MoWRe hosts a number of exhibits and programs showcasing African literature and" +
                    " oral tradition.")
                    .setTitle("Museum of Women's Resistance")
                    .setCancelable(false)
                    .setNeutralButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.cancel();
                                }
                            })
                    .setPositiveButton("More Info",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intentMuseum = new Intent();
                                    intentMuseum.setAction(Intent.ACTION_VIEW);
                                    intentMuseum.addCategory(Intent.CATEGORY_BROWSABLE);
                                    intentMuseum.setData(Uri.parse("http://www.museumofwomensresistance.org/"));
                                    startActivity(intentMuseum);
                                }
                            }
                    ).show();
        }


    }

}

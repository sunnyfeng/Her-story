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


public class BlackMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    Marker film = null;
    Marker museum = null;
    Marker riot = null;
    Marker harlem = null;

    private GoogleMap mMap;
    private int zoomAmount = 10;
    private LatLngBounds NYC = new LatLngBounds(new LatLng(40.5, -75), new LatLng(41, -73));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maps);
        setContentView(R.layout.activity_blackmaps);
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

    /*
       HISTORICAL
     */

        LatLng riotLoc = new LatLng(40.809678, -73.952562);
        riot = mMap.addMarker(new MarkerOptions()
                .position(riotLoc)
                .title("New York Race Riots (1964)")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#FC0000"))
        );
        riot.setTag(0);

        LatLng harlemLoc = new LatLng(40.817349, -73.947231);
        harlem = mMap.addMarker(new MarkerOptions()
                .position(harlemLoc)
                .title("The Harlem Renaissance (1917-1935)")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#FC0000"))
        );
        harlem.setTag(0);


    /*
       CURRENT
     */

        LatLng filmLoc = new LatLng(40.671458, -73.963609);
        film = mMap.addMarker(new MarkerOptions()
                .position(filmLoc)
                .title("Black Queer Brooklyn on Film")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#FCF900"))
        );
        film.setTag(0);

        LatLng museumLoc = new LatLng(40.808461, -73.947743);
        museum = mMap.addMarker(new MarkerOptions()
                .position(museumLoc)
                .title("Studio Museum in Harlem")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#FCF900"))

        );
        museum.setTag(0);

        //zooming
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(), zoomAmount));

    /*
        BUTTONS
     */
        final Button zoomIn = findViewById(R.id.plus_button);
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CameraUpdateFactory.zoomIn();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(), zoomAmount + 1));
                zoomAmount++;
            }
        });

        final Button zoomOut = findViewById(R.id.minus_button);
        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(), zoomAmount - 1));
                zoomAmount--;
            }
        });

        final Button zoomReset = findViewById(R.id.reset_button);
        zoomReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NYC.getCenter(), zoomAmount));
            }
        });
        mMap.setOnInfoWindowClickListener(this);

        final Button contribute = findViewById(R.id.blackContributeButton);
        contribute.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Under construction. Sorry!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public BitmapDescriptor getMarkerIcon(String hexCode) {
        float[] colors = new float[3];
        Color.colorToHSV(Color.parseColor(hexCode), colors);
        return BitmapDescriptorFactory.defaultMarker(colors[0]);
    }

    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();

        createBlurb(marker, film, "Black Queer Brooklyn on Film",
                "Black Queer Brooklyn on Film is an event bringing together those of African" +
                        "descent and those who identify as LGBTQ in a film festival that celebrates" +
                        "art and film. Young African American LGBTQ artists in Brooklyn come to " +
                        "exhibit their works of art at the Brooklyn Museum. It is important for " +
                        "people of color who also identify as LGBTQ to come together and connect.",
                "http://gomag.com/event/black-queer-brooklyn-film/");

        createBlurb(marker, museum, "Studio Museum in Harlem",
                "The Studio Museum in Harlem was founded in 1968 by a group of artists and activists. " +
                        "They envisioned a place that supported African American arts and culture. " +
                        "The museum focuses on promoting works by artists of African and Latino descent, " +
                        "bridging together artists and the public. The Studio Museum seeks to expand our " +
                        "society's perception of modern and contemporary art by those of African descent. "+
                        "The museum carries nearly 2,000 pieces of art, so be sure to visit and learn!",
                "https://www.studiomuseum.org/");

        createBlurb(marker, riot, "New York Race Riots (1964)",
                "(July 18, 1964) The New York Race Riots of 1964 led the series of race riots through America. " +
                        "The riots began when a teenager James Powell was shot to death by a white officer. " +
                        "Around 8,000 people from Harlem launched a large riot to express their discontent. " +
                        "The race riots, although violent, showed the racial injustive and civil unrest that " +
                        "was pervasive throughout the cites and that reform was needed soon.",
                "http://crdl.usg.edu/events/ny_race_riots/?Welcome");

        createBlurb(marker, harlem, "The Harlem Renaissance (1917-1935)",
                "(1917-1935) The Harlem Renaissance was a cultural and artistic explosion after the end of WWI. " +
                        "During this period, numerous black writers, artists, and musicians expressed their  " +
                        "talent and gained recognition across the world. Langston Hughes, Zora Neal Hurston, and " +
                        "Claude McKay were among the new talents. This explosion began when many " +
                        "African Americans fled the oppressive South to the Northern cities. " +
                        "banners. From this movement flourished racial pride and promoted interracial acceptance " +
                        "of civil rights.",
                "http://www.pbs.org/wnet/jimcrow/stories_events_harlem.html");

    }

    private void createBlurb(Marker clickedMarker, Marker checkMarker, String title, String message, final String url) {
        if (clickedMarker.equals(checkMarker)) {
            new AlertDialog.Builder(this).setMessage(message)
                    .setTitle(title)
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
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                    intent.setData(Uri.parse(url));
                                    startActivity(intent);
                                }
                            }
                    ).show();
        }
    }

}
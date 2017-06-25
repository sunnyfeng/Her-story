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

    Marker gandhi = null;
    Marker museum = null;
    Marker strike = null;
    Marker suffrage = null;

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

        LatLng strikeLoc = new LatLng(40.7323, -73.9964);
        strike = mMap.addMarker(new MarkerOptions()
                .position(strikeLoc)
                .title("Women's Strike For Equality (1970)")
                .snippet("Click for more info.")
                .icon(getMarkerIcon("#3B2ED2"))
        );
        strike.setTag(0);

        LatLng suffrageLoc = new LatLng(40.73466, -73.9946);
        suffrage = mMap.addMarker(new MarkerOptions()
                .position(suffrageLoc)
                .title("Women March For Suffrage (1915)")
                .snippet("Click for more info.")
                .icon(getMarkerIcon("#3B2ED2"))
        );
        suffrage.setTag(0);


    /*
       CURRENT
     */

        LatLng gandhiLoc = new LatLng(40.7729, -73.9701);
        gandhi = mMap.addMarker(new MarkerOptions()
                .position(gandhiLoc)
                .title("Madame Gandhi at Basement Bhangra")
                .snippet("Click for more info.")
                .icon(getMarkerIcon("#C43693"))
        );
        gandhi.setTag(0);

        LatLng museumLoc = new LatLng(40.66435, -73.9529);
        museum = mMap.addMarker(new MarkerOptions()
                .position(museumLoc)
                .title("Museum of Women's Resistance")
                .snippet("Click for more info.")
                .icon(getMarkerIcon("#C43693"))

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
    }

    public BitmapDescriptor getMarkerIcon(String hexCode) {
        float[] colors = new float[3];
        Color.colorToHSV(Color.parseColor(hexCode), colors);
        return BitmapDescriptorFactory.defaultMarker(colors[0]);
    }

    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();

        createBlurb(marker, gandhi, "Madame Gandhi at the Basement Bhangra 20th Anniversary Party",
                "Madame Gandhi is a DJ and activist whose music promotes female " +
                        "empowerment and raises awareness of gender equality issues, known for songs " +
                        "including \"Her\" and \"The Future is Female\". She and other " +
                        "select artists will be performing live for Basement Bhangra's 20th " +
                        "Anniversary.",
                "http://www.cityparksfoundation.org/event/basement-bhangra-" +
                        "20th-anniversary-apache-indian-panjabi-mc-dj-rekha-madame-gandhi-anik-khan-" +
                        "horsepowar-sikh-knowledge-dj-petra-dj-shilpa/");

        createBlurb(marker, museum, "Museum of Women's Resistance",
                "The Museum of Women's Resistance (MoWRe) is a museum " +
                        "that brings awareness to the diversity and influence of women of African " +
                        "descent. MoWRe promotes the intersectionality of a wide array of social aspects, " +
                        "including race, social status, gender, and culture in relation to women all around" +
                        " the world. MoWRe hosts a number of exhibits and programs showcasing African literature and" +
                        " oral tradition.",
                "http://www.museumofwomensresistance.org/");

        createBlurb(marker, strike, "Women's Strike For Equality (1970)",
                "(August 26, 1970) The Women's Strike For Equality marked the 50th anniversary of the " +
                        "passage of the 19th Amendment, or the woman's right to vote. Women stopped " +
                        "their work and took to Fifth Avenue to march in the name of women's rights. " +
                        "Women of all backgrounds and male allies unified to promote issues such as " +
                        "women's health, the pay gap, and child care.",
                "http://time.com/4008060/women-strike-equality-1970/");

        createBlurb(marker, suffrage, "Women March For Suffrage (1915)",
                "(October 23, 1915) Preceding the passage of the 19th Amendment in 1920, NYC women " +
                        "dressed in white gathered with their children to fight for the right to vote, " +
                        "despite not being taken seriously by traditionalists. More than 25,000 women, " +
                        "hands locked together, paraded down Fifth Avenue wearing flowers and waving " +
                        "banners. This event changed the perception of the women's suffrage parades, " +
                        "from what had been viewed as silly spectacles, to respected and serious " +
                        "demonstrations.",
                "http://time.com/4081629/suffrage-parade-1915/");

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
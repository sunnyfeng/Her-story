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


public class GayMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

  Marker march = null;
  Marker cent = null;
  Marker storme = null;
  Marker stone = null;
  Marker fire = null;

  private GoogleMap mMap;
  private int zoomAmount = 11;
  private LatLngBounds NYC = new LatLngBounds(new LatLng(40.5,-75), new LatLng(41, -73));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_maps);
       setContentView(R.layout.activity_gaymaps);
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

        LatLng stoneLoc = new LatLng(40.734028,-74.002152);
        stone = mMap.addMarker(new MarkerOptions()
                .position(stoneLoc)
                .title("Stonewall Inn (1969)")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#FC9D00"))
        );
        stone.setTag(0);

        LatLng fireLoc = new LatLng(40.724822,-74.001054);
        fire = mMap.addMarker(new MarkerOptions()
                .position(fireLoc)
                .title("Gay Activists Alliance Firehouse (1971-4)")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#FC9D00"))
        );
        fire.setTag(0);


    /*
       CURRENT
     */

        LatLng marchLoc = new LatLng(40.749981,-73.983682);
        march = mMap.addMarker(new MarkerOptions()
                .position(marchLoc)
                .title("NYC Pride March")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#ADE0FD"))
        );
        march.setTag(0);

        LatLng centLoc = new LatLng(40.738077, -74.001016);
        cent = mMap.addMarker(new MarkerOptions()
                .position(centLoc)
                .title("LGBT Community Center")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#ADE0FD"))

        );
        cent.setTag(0);

        LatLng stoLoc = new LatLng(40.753247, -73.982039);
        storme = mMap.addMarker(new MarkerOptions()
                .position(stoLoc)
                .title("The Storme DeLarverie Papers")
                .snippet("Click to learn more.")
                .icon(getMarkerIcon("#ADE0FD"))

        );
        storme.setTag(0);


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

        final Button contribute = findViewById(R.id.gayContributeButton);
        contribute.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Under construction. Sorry!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public BitmapDescriptor getMarkerIcon(String hexCode){
        float[] colors = new float[3];
        Color.colorToHSV(Color.parseColor(hexCode),colors);
        return BitmapDescriptorFactory.defaultMarker(colors[0]);
    }

    public void onInfoWindowClick(Marker marker){
        //Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();

        createBlurb(marker, march, "NYC Pride March",
                "Commemorating the 1969 Stonewall Riots, the annual NYC Pride March " +
                        "celebrates LGBTQ+ pride by inspiring people and educating about LGBTQ+ rights. " +
                        "It promotes equality and has come to be a part of an event-filled week called " +
                        "Pride Week.",
                "http://www.nycpride.org/events/the-march/");

        createBlurb(marker, cent, "The LGBT Community Center",
                "Organized in 1983, the Lesbian, Gay, Bisexual & Transgender (LGBT) Community Center is a center that " +
                        "promotes the wellbeing of NYC's LGBTQ+ community. It hosts health programs, " +
                        "holds events for the arts and culture, and offers family support services. It " +
                        "serves as a local resource aiming to empower and unite LGBT people.",
                "http://gaycenter.org/about");

        createBlurb(marker, storme, "The Storme DeLarverie Papers",
                "Born in 1920 to an African-American mother and white father, Storme DeLarverie would become " +
                        "known widely as a performer, male impersonator, and gay civil rights activist. DeLarvarie is " +
                        "said to have thrown the first punch at the infamous Stonewall Riots, and continued " +
                        "advocating the LGBT movement throughout her life. The New York Public Library is currently " +
                        "holding a collection of her belongings, inclusive of journals, awards, and clothes.",
                "https://www.nypl.org/blog/2017/06/23/lgbt-icon-storme?utm_source=feedburner&utm_medium" +
                        "=feed&utm_campaign=Feed%3A+NYPLAIIBlogs+%28NYPL+Blogs%29");

        createBlurb(marker, stone, "Stonewall Inn (1969)",
                "(June 1969) Cited as one of the most important events in American LGBT history, the " +
                        "Stonewall Riots took place at the Stonewall Inn in 1969. On June 28th, police raided an " +
                        "underground gay bar in Greenwich Village, sparking strong backlash from the LGBT community that" +
                        " lasted five days. This uprising set in motion a long and continuous series of events " +
                        "contributing to the Gay Liberation Movement and LGBT pride movement. It continues to " +
                        "inspire today, its influence most notably seen in the annual pride parade.",
                "http://www.hisstory.com/this-day-in-history/the-stonewall-riot/");

        createBlurb(marker, fire, "Gay Activists Alliance Firehouse (1971-4)",
                "(1971-1974) From 1971 to 1974, the Gay Activists Alliance (GAA) held headquarters at what " +
                        "then used to be a firehouse. The GAA formed in 1969, comprised of former Gay Liberation " +
                        "Front (GFL) members who did not align with the GFL's broad and radical ways, but stood by its " +
                        "ideals about supporting LGBT rights. In a more focused approach, the GAA put all of its " +
                        "efforts toward this cause.",
                "web-static.nypl.org/exhibitions/1969/alliance.html");

    }

    private void createBlurb(Marker clickedMarker, Marker checkMarker, String title, String message, final String url){
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
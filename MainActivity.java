package com.example.sunnyfeng.nycforall;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

/**
 * Created by sunnyfeng on 6/24/17.
 */

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainest);
    }

    public void redirectToWomenMaps(View view){
        Intent intent = new Intent (this, WomenMapsActivity.class);
        startActivity(intent);
    }

    public void redirectToGayMaps(View view){
        Intent intent = new Intent (this, GayMapsActivity.class);
        startActivity(intent);
    }

    public void redirectToBlackMaps(View view){
        Intent intent = new Intent (this, BlackMapsActivity.class);
        startActivity(intent);
    }

}

package com.example.sunnyfeng.nycforall;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
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
        new AlertDialog.Builder(this).setMessage("In such a tumultuous time in history, it is imperative" +
                " that we take the time to understand other perspectives and remember that we are not alone.\n\nRightOnTime is an app that seeks to" +
                " promote equal rights and shed light on social issues as they have occurred throughout" +
                " time. The histories of marginalized groups often fall through the cracks, which is unacceptable " +
                " because their impact has shaped our lives in more ways than we can imagine." +
                "\n\nOur interactive platform offers both a glimpse into the past and a way to shape the present. " +
                "You choose an issue you care about, and RightOnTime highlights map locations of historical importance, " +
                "as well as relevant present-day events that offer opportunities to connect with supportive people. " +
                "\n\nWe're not just remembering the past - we are " +
                "promoting the future.")
                .setTitle("                  RightOnTime")
                .setCancelable(false)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        }
                ).show();
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

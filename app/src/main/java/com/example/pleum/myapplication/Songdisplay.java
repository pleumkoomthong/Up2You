package com.example.pleum.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by PLEUM on 19/10/2560.
 */

public class Songdisplay extends Activity {

    private String songID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songdisplay);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            songID = bundle.getString("songID");

            String result = String.format("songID is %s", songID);
            Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
        }



    }
}

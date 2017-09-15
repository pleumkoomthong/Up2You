package com.example.pleum.myapplication;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class InitialFonts extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("font/cloud_light.otf").setFontAttrId(R.attr.fontPath).build());


    }

}

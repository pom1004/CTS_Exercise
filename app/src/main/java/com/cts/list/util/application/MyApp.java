package com.cts.list.util.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;


/**
 * As of now no use of this app class
 */
public class MyApp extends Application {


    private static final String TAG = "MyApp";

    private static MyApp mInstance;


    public static synchronized MyApp getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

    }


}


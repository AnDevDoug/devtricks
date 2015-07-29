package com.hyperaware.android.devtricks;

import android.app.Application;

import com.hyperaware.android.devtricks.broadcast.BatteryChangedBroadcastReceiver;
import com.hyperaware.android.devtricks.broadcast.HeadsetPlugBroadcastReceiver;
import com.hyperaware.android.devtricks.logging.AndroidLogHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLogging();
        registerDynamicBroadcastReceivers();
        startSomeAsyncInit();
    }

    // Set up Android logcat logging via the default Java Logging
    // infrastructure.  Here, we're redirecting every logger in
    // package com.hyperaware.android.devtricks to logcat.
    //
    private void initLogging() {
        final String pkg = getClass().getPackage().getName();  // "com.hyperaware.android.devtricks"
        final AndroidLogHandler alh = new AndroidLogHandler(pkg);
        Logger logger = Logger.getLogger(pkg);
        logger.addHandler(alh);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.FINEST); // configure default log level here, perhaps via a resource?
        logger.info("Logging initialized with default level " + logger.getLevel());
    }

    // Register broadcast receivers that are only active while the process is running
    //
    private void registerDynamicBroadcastReceivers() {
        BatteryChangedBroadcastReceiver.register(this);
        HeadsetPlugBroadcastReceiver.register(this);
    }

    // Possibly perform some activities in the background to warm up the app for better
    // performance later.
    private void startSomeAsyncInit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Delay; don't negatively impact app launch time
                    Thread.sleep(3000);
                }
                catch (InterruptedException ignore) {
                }

                // init database helpers?
                // fetch/sync latest data from server?
                // warm up some data cache?
            }
        }, "App async init").start();
    }

}

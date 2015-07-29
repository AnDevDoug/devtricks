package com.hyperaware.android.devtricks.broadcast;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.hyperaware.android.devtricks.logging.Logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BatteryChangedBroadcastReceiver extends BroadcastReceiver {

    private static final Logger LOGGER = Logging.getLogger(BatteryChangedBroadcastReceiver.class);

    @Override
    public void onReceive(final Context context, final Intent intent) {
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("Battery level at " + level + '/' + scale);
        }

        // Maybe stop background work if battery is low and the device is not plugged in
    }

    public static void register(Application context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        context.registerReceiver(new BatteryChangedBroadcastReceiver(), filter);
    }

}

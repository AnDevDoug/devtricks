package com.hyperaware.android.devtricks.broadcast;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class HeadsetPlugBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // Maybe mute music/sound by user preference
    }

    public static void register(Application context) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        context.registerReceiver(new HeadsetPlugBroadcastReceiver(), filter);
    }

}

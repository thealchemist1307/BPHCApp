package com.crux.bphcfreshers;



import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static androidx.core.content.ContextCompat.getSystemService;

public class AlertActivity extends BroadcastReceiver {

    String CHANNEL_ID = "id";
    int notificationId = 1;


    @Override
    public void onReceive(Context context, Intent intent) {
        AlertDetails alertActivity=new AlertDetails(context);
        NotificationCompat.Builder nb= alertActivity.getChannel();

        alertActivity.getManager().notify(1,nb.build());
    }
}


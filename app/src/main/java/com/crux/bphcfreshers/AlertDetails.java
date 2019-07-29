package com.crux.bphcfreshers;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlertDetails extends ContextWrapper {

    String CHANNEL_ID = "id";
    int notificationId = 1;
    CharSequence name = "Name";
    String description = "Description";
    int importance = NotificationManager.IMPORTANCE_HIGH;

    String channel_name = "notif";
    String channel_description = "Notification";
    NotificationManager notificationManager;

    public AlertDetails(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CreateNotification();
        }
    }


    public void CreateNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            getManager().createNotificationChannel(channel);

        }


    }

    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        }
        return notificationManager;
    }

    public NotificationCompat.Builder getChannel() {
        Intent intent=new Intent(AlertDetails.this,timetableView.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,12,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentTitle("Get Up!")
                .setContentText("You have class")
                .setContentInfo("You have class")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
    }
}

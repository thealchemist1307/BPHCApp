package com.crux.bphcfreshers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class AlertDetails(base: Context?) : ContextWrapper(base) {
    var CHANNEL_ID = "id"
    var notificationId = 1
    var name: CharSequence = "Name"
    var description = "Description"
    var importance = NotificationManager.IMPORTANCE_HIGH
    var channel_name = "notif"
    var channel_description = "Notification"
    var notificationManager: NotificationManager? = null
    fun CreateNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH)
            channel.description = description
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            // Register the channel with the system; you can't change the importance
// or other notification behaviors after this
            manager!!.createNotificationChannel(channel)
        }
    }

    val manager: NotificationManager?
        get() {
            if (notificationManager == null) {
                notificationManager = getSystemService(NotificationManager::class.java) as NotificationManager
            }
            return notificationManager
        }

    val channel: NotificationCompat.Builder
        get() {
            val intent = Intent(this@AlertDetails, timetableView::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 12, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                    .setContentTitle("Get Up!")
                    .setContentText("You have class")
                    .setContentInfo("You have class")
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
        }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CreateNotification()
        }
    }
}
package com.crux.bphcfreshers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlertActivity : BroadcastReceiver() {
    var CHANNEL_ID = "id"
    var notificationId = 1
    override fun onReceive(context: Context, intent: Intent) {
        val alertActivity = AlertDetails(context)
        val nb = alertActivity.channel
        alertActivity.manager?.notify(1, nb.build())
    }
}
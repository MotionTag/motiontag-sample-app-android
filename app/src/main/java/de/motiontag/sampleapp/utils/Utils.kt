package de.motiontag.sampleapp.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import de.motiontag.sampleapp.CHANNEL_ID
import de.motiontag.sampleapp.R

fun Context.getForegroundNotification(): Notification {
    this.initNotificationChannel()

    val notification = NotificationCompat.Builder(this,
            CHANNEL_ID
        )
        .setSmallIcon(R.drawable.ic_notification_icon)
        .setContentTitle(this.getString(R.string.app_name))
        .setContentText(this.getString(R.string.tracking_activated))
        .setPriority(NotificationCompat.PRIORITY_LOW)
        .build()
    notification.flags = Notification.FLAG_ONGOING_EVENT
    return notification
}

private fun Context.initNotificationChannel() {
    val notificationService =
        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = notificationService.getNotificationChannel(CHANNEL_ID)
        if (notificationChannel == null) {
            val newNotificationChannel = NotificationChannel(
                CHANNEL_ID, this.getString(
                    R.string.app_name
                ),
                NotificationManager.IMPORTANCE_LOW
            )
            newNotificationChannel.setShowBadge(false)
            notificationService.createNotificationChannel(newNotificationChannel)
        }
    }
}
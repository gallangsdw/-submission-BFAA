package com.sdwtech.githubuser.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.sdwtech.githubuser.MainActivity
import com.sdwtech.githubuser.R
import java.util.*

class AlarmReceiver: BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_1"
        const val CHANNEL_NAME = "AlarmManager Channel"
    }
    override fun onReceive(context: Context, intent: Intent) {
        showAlarmNotification(context)
    }

    private fun showAlarmNotification(context: Context) {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0, intent, 0)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.octocat))
                .setContentTitle(context.resources.getString((R.string.notif)))
                .setContentText(context.resources.getString(R.string.text_notif))
                .setSound(alarmSound)
                .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000,1000,1000,1000,1000)

            builder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun setRepeatingAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
        }

        val pendingIntent = PendingIntent.getBroadcast(context,101, intent, 0)
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }
}
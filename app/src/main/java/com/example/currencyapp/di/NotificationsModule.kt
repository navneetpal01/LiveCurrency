package com.example.currencyapp.di

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.currencyapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NotificationsModule {

    @Singleton
    @Provides
    fun providesNotificationCompatBuilder(application: Application): NotificationCompat.Builder {
        return NotificationCompat.Builder(application, "Main-Channel")
            .setContentTitle("Alert Check the prices")
            .setContentText("Check the Euro Dollar and Pound prices")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    @SuppressLint("WrongConstant")
    @Singleton
    @Provides
    fun providesNotificationManagerCompat(application: Application): NotificationManagerCompat {
        val notificationManagerCompat = NotificationManagerCompat.from(application)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channels = NotificationChannel(
                "Main-Channel",
                "Currency Updates",
                NotificationManagerCompat.IMPORTANCE_DEFAULT
            )
            notificationManagerCompat.createNotificationChannel(channels)
        }
        return notificationManagerCompat
    }

}
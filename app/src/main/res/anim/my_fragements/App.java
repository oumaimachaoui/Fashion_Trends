package com.example.oumaima.my_fragements;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class App extends Application {

    public static final String CHANNEL_2_ID = "channel2";
    public static final String CHANNEL_1_ID = "channel1";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        //this condition is necessairy to see if the version is aqcuire the notification class
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");
            /************/
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel2);
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("This is Channel 1");
            NotificationManager manager2 = getSystemService(NotificationManager.class);
            manager2.createNotificationChannel(channel1);
        }
    }
}
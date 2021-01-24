package com.example.oumaima.my_fragements;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class myService extends Service {
    private NotificationManagerCompat notificationManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Nullable
    @Override
    public void onCreate(){
        Toast.makeText(this, "onCreate!", Toast.LENGTH_SHORT).show();
        notificationManager = NotificationManagerCompat.from(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //String message = intent.getStringExtra("title");
        String message = intent.getStringExtra("nameproduct");
        Toast.makeText(this, "StartCommand!", Toast.LENGTH_SHORT).show();
        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.veryminilogo)
                .setContentTitle("Fashion Oumaima")
                .setContentText("Votre produit favorable: "+message+" est ajouté dans votre liste")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(1, notification);
        String message2 = intent.getStringExtra("nameproduct");
        Toast.makeText(this, "onStartCommand!", Toast.LENGTH_SHORT).show();
        Notification notification2 = new NotificationCompat.Builder(this,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.veryminilogo)
                .setContentTitle("Fashion Oumaima")
                .setContentText("Votre produit: "+message2+" est ajouté au chariot")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManager.notify(2, notification2);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "onDestroy!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}

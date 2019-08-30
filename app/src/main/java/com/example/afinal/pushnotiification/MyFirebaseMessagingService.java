package com.example.afinal.pushnotiification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String title;
    String desc;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

        Intent intent=
                new Intent(getApplicationContext(), MainActivity.class);

//            title =remoteMessage.getNotification().getTitle();
//            desc = remoteMessage.getNotification().getBody();


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationbuilder=new NotificationCompat.Builder(this);
        notificationbuilder.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationbuilder.setContentText(remoteMessage.getNotification().getBody());
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationbuilder.setSound(alarmSound);


        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        notificationbuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("Default", "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        if (notificationManager != null) {
            notificationManager.notify(0,notificationbuilder.build());
        }
    }
}

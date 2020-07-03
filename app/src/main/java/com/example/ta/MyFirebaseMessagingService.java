package com.example.ta;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        String id_kapal ="0", latitude ="0",longitude ="0",status ="0",time ="0";
        if(remoteMessage.getData().size()>0){
            showNotification(remoteMessage.getData().get("tipe_kapal"),remoteMessage.getData().get("latitude"),remoteMessage.getData().get("longitude"),remoteMessage.getData().get("nama_status"),remoteMessage.getData().get("time"));
        }

        if (remoteMessage.getNotification()!= null){
            showNotification(id_kapal,latitude,longitude,status,time);
        }
    }

    private RemoteViews getCustomDesign(String id_kapal, String latitude, String longitude, String status, String time){
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(),R.layout.notif);
        remoteViews.setTextViewText(R.id.idkapal,id_kapal);
        remoteViews.setTextViewText(R.id.idlatitude,latitude);
        remoteViews.setTextViewText(R.id.idlongitude,longitude);
        remoteViews.setTextViewText(R.id.idstatus,status);
        remoteViews.setTextViewText(R.id.idtime,time);
        remoteViews.setImageViewResource(R.id.idimage,R.drawable.ic_message_black_24dp);
        return remoteViews;
    }

    private void showNotification(String id_kapal, String latitude, String longitude, String status, String time) {
        Intent intent = new Intent(this,SplashScreen.class);
        //Intent intent = new Intent(this,SecondActivity.class);
        String channel_id = "notification_channel";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channel_id)
                .setSmallIcon(R.drawable.ic_message_black_24dp)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            builder = builder.setContent(getCustomDesign(id_kapal,latitude,longitude,status,time));
        }
        else {
            builder = builder.setContentText(id_kapal)
                    .setContentText(latitude)
                    .setContentText(longitude)
                    .setContentText(status)
                    .setContentText(time)
                    .setSmallIcon(R.drawable.ic_message_black_24dp);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channel_id,"notification_app",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0,builder.build());
    }
}

package com.andriy.recordphonecalls.RecordCalls;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.andriy.recordphonecalls.R;

public class RecordNotification {
    private NotificationManager notificationManager;
    private Context context;

    RecordNotification(Context context) {
        notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        this.context = context;
    }

    void showNotification() {
        Notification.Builder builder = new Notification.Builder(context);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Record Call")
                .setContentText("Запись началась")
                .setUsesChronometer(true);

        Notification notification = builder.build();
        notificationManager.notify(10, notification);
    }

    void closeNotification() {
        notificationManager.cancel(10);
    }
}
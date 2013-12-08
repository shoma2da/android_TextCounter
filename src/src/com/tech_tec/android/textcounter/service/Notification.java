package com.tech_tec.android.textcounter.service;

import java.util.Map;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.flurry.android.FlurryAgent;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.tech_tec.android.textcounter.MainActivity;
import com.tech_tec.android.textcounter.R;

class Notification {
    
    static int NOTIFICATION_ID = 315;
    
    private Context mContext;
    private NotificationManager mNotificationManager;
    
    Notification(Context context) {
        mContext = context;
        mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
    
    void show(int stringLenght, String string) {
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        
        String message = (string == null) ? mContext.getString(R.string.hint) : string;
        
        @SuppressWarnings("deprecation")
        android.app.Notification notification = new android.app.Notification.Builder(mContext)
                                                    .setContentText(message)
                                                    .setContentTitle(stringLenght + "文字")
                                                    .setTicker(stringLenght + "文字")
                                                    .setSmallIcon(R.drawable.ic_launcher)
                                                    .setContentIntent(pendingIntent)
                                                    .getNotification();
        mNotificationManager.notify(NOTIFICATION_ID, notification);
        
        //ログ取得(Google, Flurry)
        EasyTracker easyTracker = EasyTracker.getInstance(mContext);
        Map<String, String> event = MapBuilder.createEvent("ui_action", "start_notification", "text_count", null).build();
        easyTracker.send(event);
        //---
        FlurryAgent.logEvent("start_notification");
    }
    
    void dismiss() {
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
    
}

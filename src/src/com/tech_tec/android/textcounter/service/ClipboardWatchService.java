package com.tech_tec.android.textcounter.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tech_tec.android.textcounter.clipboard.ClipboardTextGetter;

public class ClipboardWatchService extends Service {
    
    private Notification mNotification;
    private ClipboardTextGetter mGetter;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        mGetter = new ClipboardTextGetter(this);
        mNotification = new Notification(this);
        
        //クリップボード変更時の処理を登録
        OnClipboardChangeListenerImpl listener = new OnClipboardChangeListenerImpl(mGetter, mNotification);
        mGetter.addOnClipboardChangeListener(listener);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotification.dismiss();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return START_STICKY;
        }
        
        //現在のクリップボード状態を表示
        mNotification.show(mGetter.getTextLength(), mGetter.getText());
        
        return START_STICKY;
    }

}

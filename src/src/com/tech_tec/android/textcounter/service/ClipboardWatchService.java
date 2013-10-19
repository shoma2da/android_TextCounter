package com.tech_tec.android.textcounter.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.tech_tec.android.textcounter.clipboard.ClipboardTextGetter;

public class ClipboardWatchService extends Service {
    
    private Notification mNotification;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        ClipboardTextGetter getter = new ClipboardTextGetter(this);
        mNotification = new Notification(this);
        
        //現在のクリップボード状態を表示
        mNotification.show(getter.getTextLength(), getter.getText());
        
        //クリップボード変更時の処理を登録
        OnClipboardChangeListenerImpl listener = new OnClipboardChangeListenerImpl(getter, mNotification);
        getter.addOnClipboardChangeListener(listener);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotification.dismiss();
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

}

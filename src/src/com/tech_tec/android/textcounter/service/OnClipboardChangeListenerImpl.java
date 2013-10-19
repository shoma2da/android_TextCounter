package com.tech_tec.android.textcounter.service;

import com.tech_tec.android.textcounter.clipboard.ClipboardTextGetter;

import android.content.ClipboardManager.OnPrimaryClipChangedListener;

class OnClipboardChangeListenerImpl implements OnPrimaryClipChangedListener {
    
    private ClipboardTextGetter mGetter;
    private Notification mNotification;
    
    public OnClipboardChangeListenerImpl(ClipboardTextGetter getter, Notification notification) {
        mGetter = getter;
        mNotification = notification;
    }

    @Override
    public void onPrimaryClipChanged() {
        mNotification.show(mGetter.getTextLength(), mGetter.getText());
    }
    
}

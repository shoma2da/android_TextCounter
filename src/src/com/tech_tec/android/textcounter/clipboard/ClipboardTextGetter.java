package com.tech_tec.android.textcounter.clipboard;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardTextGetter {
    
    private ClipboardManager mClipboardManager;
    
    public ClipboardTextGetter(Context context) {
        mClipboardManager = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
    
    public String getText() {
        if (mClipboardManager.hasPrimaryClip() == false) {
            return null;
        }
        ClipData clipData = mClipboardManager.getPrimaryClip();
        Item itemAt = clipData.getItemAt(0);
        return itemAt.getText().toString();
    }
    
    public int getTextLength() {
        String text = getText();
        if (text == null) {
            return 0;
        }
        
        
        return text.length();
    }
    
    public void addOnClipboardChangeListener(OnPrimaryClipChangedListener listener) {
        mClipboardManager.addPrimaryClipChangedListener(listener);
    }
}

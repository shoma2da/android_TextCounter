package com.tech_tec.android.textcounter.setting;

import android.content.Context;
import android.content.SharedPreferences;

public class NotificationSetting {
    
    static final String FILE_NAME = "notification_setting";
    static final String KEY_NOTIFICATION_SETTING = "value";
    
    private SharedPreferences mPreferences;
    private NotificationSettingChangeAction mAction;
    
    public NotificationSetting(Context context, NotificationSettingChangeAction action) {
        mPreferences = (SharedPreferences)context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mAction = action;
    }
    
    public void setSetting(boolean enabled) {
        mPreferences.edit().putBoolean(KEY_NOTIFICATION_SETTING, enabled).commit();
        act();
    }
    
    public boolean getSetting() {
        return mPreferences.getBoolean(KEY_NOTIFICATION_SETTING, true);
    }
    
    public void act() {
        boolean currentSetting = getSetting();
        if (currentSetting) {
            mAction.actInEnabled();
        } else {
            mAction.actInDisabled();
        }
    }
    
}

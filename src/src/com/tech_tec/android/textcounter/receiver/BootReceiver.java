package com.tech_tec.android.textcounter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tech_tec.android.textcounter.setting.NotificationSetting;
import com.tech_tec.android.textcounter.setting.NotificationSettingChangeActionImpl;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationSettingChangeActionImpl action = new NotificationSettingChangeActionImpl(context);
        NotificationSetting notificationSetting = new NotificationSetting(context, action);
        notificationSetting.act();
    }

}

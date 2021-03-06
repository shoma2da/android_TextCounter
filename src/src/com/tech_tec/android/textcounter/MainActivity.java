package com.tech_tec.android.textcounter;

import java.io.Flushable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;
import com.google.analytics.tracking.android.EasyTracker;
import com.tech_tec.android.textcounter.clipboard.ClipboardTextGetter;
import com.tech_tec.android.textcounter.setting.NotificationSetting;
import com.tech_tec.android.textcounter.setting.NotificationSettingChangeActionImpl;
import com.tech_tec.android.textcounter.tutorial.InitialDescriptionView;

public class MainActivity extends Activity {
    
    private TextView mCopiedText;
    private TextView mLengthText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        
        new InitialDescriptionView(this).showIfInitialLaunch(); //チュートリアル
        
        mCopiedText = (TextView)findViewById(R.id.text_copied);
        mLengthText = (TextView)findViewById(R.id.text_text_length);

        NotificationSettingChangeActionImpl action = new NotificationSettingChangeActionImpl(this);
        final NotificationSetting notificationSetting = new NotificationSetting(this, action);

        CheckBox notificationSettingCheckBox = (CheckBox)findViewById(R.id.checkBox_notification_setting);
        notificationSettingCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notificationSetting.setSetting(isChecked);
            }
        });
        
        notificationSettingCheckBox.setChecked(notificationSetting.getSetting());
        notificationSetting.act();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        ClipboardTextGetter textGetter = new ClipboardTextGetter(this);
        mLengthText.setText("" + textGetter.getTextLength());
        if (textGetter.getText() == null) {
            mCopiedText.setText(R.string.hint);
        } else {
            mCopiedText.setText(textGetter.getText());
        }
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
        FlurryAgent.onStartSession(this, "NVQZJFV4C8WGFFSKQ25X");
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
        FlurryAgent.onEndSession(this);
    }

}

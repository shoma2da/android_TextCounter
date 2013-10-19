package com.tech_tec.android.textcounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.tech_tec.android.textcounter.clipboard.ClipboardTextGetter;
import com.tech_tec.android.textcounter.setting.NotificationSetting;
import com.tech_tec.android.textcounter.setting.NotificationSettingChangeActionImpl;

public class MainActivity extends Activity {
    
    private TextView mCopiedText;
    private TextView mLengthText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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

}

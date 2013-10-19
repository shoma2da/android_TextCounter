package com.tech_tec.android.textcounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.tech_tec.android.textcounter.clipboard.ClipboardTextGetter;

public class MainActivity extends Activity {
    
    private TextView mCopiedText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mCopiedText = (TextView)findViewById(R.id.text_copied);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        ClipboardTextGetter textGetter = new ClipboardTextGetter(this);
        mCopiedText.setText(textGetter.getText());
    }

}

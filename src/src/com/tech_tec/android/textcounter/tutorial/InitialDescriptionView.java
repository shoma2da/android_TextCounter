package com.tech_tec.android.textcounter.tutorial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;

import com.tech_tec.android.textcounter.R;

public class InitialDescriptionView {
    
    static final String FILE_NAME = "tutorial";
    static final String INITIAL_LAUNCH_KEY = "initial_launch";
    
    private Context mContext;
    
    public InitialDescriptionView(Context context) {
        mContext = context;
    }
    
    public void showIfInitialLaunch() {
        //データ確認と書き込み
        SharedPreferences preferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (preferences.getBoolean(INITIAL_LAUNCH_KEY, false)) {
            return;//２度目以降は表示しない
        }
        preferences.edit().putBoolean(INITIAL_LAUNCH_KEY, true).commit();
        
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.tutorial_title);
        builder.setMessage(R.string.tutorial_message);
        builder.setPositiveButton("OK", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    
}

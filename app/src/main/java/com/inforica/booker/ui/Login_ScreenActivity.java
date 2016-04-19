package com.inforica.booker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.inforica.booker.R;


/**
 * Created by user on 4/14/2016.
 */
public class Login_ScreenActivity extends Activity implements View.OnClickListener {
    TextView whatsthis;
    Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_activity);
        whatsthis = (TextView) findViewById(R.id.whatsthis);
        whatsthis.setOnClickListener(this);
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.whatsthis:
                Intent i = new Intent(Login_ScreenActivity.this, WhatIsOfficePage_Activity.class);
                startActivity(i);
                break;
            case R.id.sign_in:
                Intent intent = new Intent(Login_ScreenActivity.this, CalenderPickerListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
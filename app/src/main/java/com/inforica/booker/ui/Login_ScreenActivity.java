package com.inforica.booker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.service.SignInAsyncTask;
import com.inforica.booker.utils.SuperclassActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by user on 4/14/2016.
 */
public class Login_ScreenActivity extends SuperclassActivity implements View.OnClickListener {
    TextView whatsthis;
    Button sign_in;
    SignInAsyncTask signInAsyncTask;
    EditText email_text;
    public final Pattern pattern = Pattern
            .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_activity);
        whatsthis = (TextView) findViewById(R.id.whatsthis);
        whatsthis.setOnClickListener(this);
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(this);
        email_text = (EditText) findViewById(R.id.email_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.whatsthis:
                Intent i = new Intent(Login_ScreenActivity.this, WhatIsOfficePage_Activity.class);
                startActivity(i);
                break;
            case R.id.sign_in:

                    Matcher m = pattern.matcher(email_text.getText().toString().trim());
                    boolean emailFormat = m.matches();
                    if (email_text.getText().toString().trim().length() == 0) {
                        email_text.setError(Html.fromHtml("<font color='white'>Please enter E-mail ID</font>"));
                        email_text.requestFocus();
                        showKeyboard();

                    } else if (emailFormat == false) {
                        email_text.setError(Html.fromHtml("<font color='white'>Please enter valid E-mail ID</font>"));
                        email_text.requestFocus();
                        showKeyboard();
                    } else {
//                        if (email_text.getText().toString().length() != 0) {}
                        signInAsyncTask = new SignInAsyncTask(Login_ScreenActivity.this);
                        signInAsyncTask.execute();
                    }

                break;
        }
    }
}

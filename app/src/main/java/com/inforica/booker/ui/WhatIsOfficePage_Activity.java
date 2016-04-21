package com.inforica.booker.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andexert.library.RippleView;
import com.inforica.booker.R;
import com.inforica.booker.utils.SuperclassActivity;


/**
 * Created by user on 4/14/2016.
 */
public class WhatIsOfficePage_Activity extends SuperclassActivity implements View.OnClickListener {
    //    LinearLayout back_button;
    RippleView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_is_office_activity);
        back_button = (RippleView) findViewById(R.id.back_button);
//        ripple_layout_1 = (Button) findViewById(R.id.ripple_layout_1);
        back_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
          /*      Intent i = new Intent(WhatIsOfficePage_Activity.this, Login_ScreenActivity.class);
                startActivity(i);*/
                onBackPressed();
                break;

        }
    }
}

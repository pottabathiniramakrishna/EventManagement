package com.inforica.booker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.inforica.booker.R;
import com.inforica.booker.slidingmenu.DrawlayoutHolder;

/**
 * Created by user on 5/4/2016.
 */
public class GiveFeedBackActivity extends DrawlayoutHolder {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.give_feedback_activity);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

       /* mdrawlayoutEnable = false;
        mActionbarEnable = false;
        Drawlayout_views(GiveFeedBackActivity.this, 1, "GiveFeedBackActivity");
        if (mDrawerLayout.isDrawerVisible(leftRL)) {
            mDrawerLayout.closeDrawer(leftRL);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        getclassname = getClass().getSimpleName().toString();
    }
}

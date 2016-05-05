package com.inforica.booker.ui;

import android.os.Bundle;

import com.inforica.booker.R;
import com.inforica.booker.slidingmenu.DrawlayoutHolder;
import com.inforica.booker.utils.SuperclassActivity;

/**
 * Created by user on 5/4/2016.
 */
public class SuggestFeatureActivity extends DrawlayoutHolder {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest_feature_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getclassname = getClass().getSimpleName().toString();
    }
}

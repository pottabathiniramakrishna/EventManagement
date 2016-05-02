package com.inforica.booker.activities;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by user on 4/18/2016.
 */
public class SuperActivity extends FragmentActivity {


    public void showKeyboard() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

}

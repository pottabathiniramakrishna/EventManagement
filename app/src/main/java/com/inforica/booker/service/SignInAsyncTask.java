package com.inforica.booker.service;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.inforica.booker.ui.CalenderPickerListViewActivity;
import com.inforica.booker.ui.CalenderPickerRecyclerViewActivity;
import com.inforica.booker.utils.MyCustomProgressDialog;

/**
 * Created by user on 4/18/2016.
 */
public class SignInAsyncTask extends AsyncTask {
    MyCustomProgressDialog routeProgressDialog;
    Activity mActivity;

    public SignInAsyncTask(Activity mActivity) {
        this.mActivity = mActivity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        routeProgressDialog = (MyCustomProgressDialog) MyCustomProgressDialog.ctor(mActivity);
        routeProgressDialog.setCancelable(false);
//            dilogListener();
        routeProgressDialog.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            //Do something...
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (routeProgressDialog != null && routeProgressDialog.isShowing()) {
            routeProgressDialog.dismiss();
            Intent intent = new Intent(mActivity,CalenderPickerRecyclerViewActivity.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    }
}

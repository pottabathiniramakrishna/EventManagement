package com.inforica.booker.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerListAsyncTask extends AsyncTask {
    JSONObject reader;
    List<NameValuePair> postDataParams;
    String status = "";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {

//            reader = JSONParser.makeHttpRequest(ServiceConstants.URL_LOCATION, HttpMethods.GET, postDataParams);
            Log.v("Tag", "reader" + reader);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reader;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        try {
            if (reader != null) {
                status = reader.getString("Status").toString();
                if (status.equals("Success")) {
                }
            }
        } catch (Exception e) {

        }
    }
}
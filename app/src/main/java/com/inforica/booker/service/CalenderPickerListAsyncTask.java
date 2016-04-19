package com.inforica.booker.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inforica.booker.adapter.CalenderPickerRecyclerAdapter;
import com.inforica.booker.model.CalenderPickerServiceResponce;

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
    private RecyclerView.Adapter mAdapter;
    Activity mActivity;
    RecyclerView mRecyclerView;
    ArrayList<CalenderPickerServiceResponce> calenderviewlist;

    public CalenderPickerListAsyncTask(Activity mActivity, RecyclerView mRecyclerView) {
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
  /*      try {

//            reader = JSONParser.makeHttpRequest(ServiceConstants.URL_LOCATION, HttpMethods.GET, postDataParams);
            Log.v("Tag", "reader" + reader);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reader;*/
        calenderviewlist = new ArrayList<CalenderPickerServiceResponce>();
        CalenderPickerServiceResponce sr = new CalenderPickerServiceResponce();
        sr.setCalender_name("Batman Begins");
        sr.setCalender_desc("Released in 2006");
        sr.setCalender_name("Paul");
        sr.setCalender_desc("Released in 2011");
        sr.setCalender_name("Sapphires");
        sr.setCalender_desc("Released in 2012");
        sr.setCalender_name("Olympus");
        sr.setCalender_desc("Released in 2012");
        calenderviewlist.add(sr);


        return reader;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        try {
            mAdapter = new CalenderPickerRecyclerAdapter(mActivity, calenderviewlist);
            mRecyclerView.setAdapter(mAdapter);
        } catch (Exception e) {

        }
    }
}
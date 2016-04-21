package com.inforica.booker.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inforica.booker.adapter.CalenderPickerRecyclerAdapter;
import com.inforica.booker.model.CalenderPickerServiceResponce;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerRecyclerViewAsyncTask extends AsyncTask {
    JSONObject reader, jsonObj;
    JSONArray jsonArray;
    List<NameValuePair> postDataParams;
    String status = "";
    // private RecyclerView.Adapter mAdapter;
    Activity mActivity;
    RecyclerView mRecyclerView;
    CalenderPickerRecyclerAdapter calenderPickerRecyclerAdapter;
    ArrayList<CalenderPickerServiceResponce> calenderviewlist = new ArrayList<CalenderPickerServiceResponce>();
    ;
    String Calender_Url, json;
    JSONParser jsonParser;

    public CalenderPickerRecyclerViewAsyncTask(Activity mActivity, RecyclerView mRecyclerView, String Calender_Url) {
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
        this.Calender_Url = Calender_Url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {
            jsonParser = new JSONParser();
            json = jsonParser.makeServiceCall(Calender_Url, HttpMethods.POST);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return reader;
       /* String[] Name = new String[]{"The Business", "The Business", "Leob Consulting", "Soma Pilates", "ZenRock Fitness"};
        String Mail[] = new String[]{"The Business", "The Business", "Leob Consulting", "Soma Pilates", "ZenRock Fitness"};
        calenderviewlist = new ArrayList<CalenderPickerServiceResponce>();

        for (int i = 0; i < Name.length; i++) {
            CalenderPickerServiceResponce sr = new CalenderPickerServiceResponce();
            sr.setCalender_name(Name[i]);
            sr.setCalender_desc(Mail[i]);

            calenderviewlist.add(sr);
            Log.v("Tag", "name" + Name[i]);
            Log.v("Tag", "mail" + Mail[i]);
            Log.v("Tag", "list" + calenderviewlist.size());
            Log.v("Tag", "list" + calenderviewlist.get(0).getCalender_name());
    } return reader;*/

    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        if (json != null) {
            try {
                Log.d("try", "in the try");
                jsonObj = new JSONObject(json);
                jsonArray = jsonObj.getJSONArray("actors");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);
                    CalenderPickerServiceResponce calenderPickerServiceResponce = new CalenderPickerServiceResponce();
                    calenderPickerServiceResponce.setCalender_name(c.getString("name").toString());
                    calenderPickerServiceResponce.setCalender_desc(c.getString("country").toString());
                    //      Log.v("Tag", "country" + c.getString("country").toString());
                    calenderviewlist.add(calenderPickerServiceResponce);

                    //      Log.v("Tag", "list" + calenderviewlist.size());
                    //    Log.v("Tag", "list" + calenderviewlist.get(0).getCalender_name());

                }
            } catch (JSONException e) {
                Log.d("catch", "in the catch");
                e.printStackTrace();
            }
        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }
        try {
            calenderPickerRecyclerAdapter = new CalenderPickerRecyclerAdapter(mActivity, calenderviewlist);
            Log.v("Tag", "list" + calenderviewlist);
            mRecyclerView.setAdapter(calenderPickerRecyclerAdapter);
            calenderPickerRecyclerAdapter.notifyDataSetChanged();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        } catch (Exception e) {

        }
    }
}

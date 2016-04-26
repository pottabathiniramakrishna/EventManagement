package com.inforica.booker.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.inforica.booker.R;
import com.inforica.booker.adapter.CalenderPickerListViewAdapter;
import com.inforica.booker.model.CalenderPickerServiceResponce;
import com.inforica.booker.service.CalenderPickerRecyclerViewAsyncTask;
import com.inforica.booker.service.HttpMethods;
import com.inforica.booker.service.JSONParser;
import com.inforica.booker.service.ServiceConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guest1 on 4/21/2016.
 */
public class CalenderPickerListViewActivity extends Activity {
    public ListView calenderListView;
    //    ListviewAsyncTask listviewAsyncTask;
    CalenderPickerRecyclerViewAsyncTask calender_Picker_List_AsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_picker_listview_activity);
        calenderListView = (ListView) findViewById(R.id.calenders_list);


//        calender_Picker_List_AsyncTask = new CalenderPickerRecyclerViewAsyncTask(CalenderPickerListViewActivity.this,Url,calenderListView);
       /* calender_Picker_List_AsyncTask = new CalenderPickerRecyclerViewAsyncTask();
        calender_Picker_List_AsyncTask.execute();*/
    }
}
/*
 class ListviewAsyncTask extends AsyncTask {
    Activity activity;
    String Url, json, POST;
    JSONObject reader;
    JSONObject jsonObj;
    JSONArray jsonArray;
    CalenderPickerListViewAdapter listviewAdapter;
    ListView listView;
    ArrayList<CalenderPickerServiceResponce> calenderList = new ArrayList<>();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public ListviewAsyncTask(Activity activity, String Url,ListView listView) {
        this.activity = activity;
        this.Url = Url;
        this.listView = listView;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        JSONParser jsonParser = new JSONParser();
        json = jsonParser.makeServiceCall(Url, HttpMethods.POST);
        Log.v("Tag", "url" + Url);
        Log.v("Tag", "reader" + json);
        return reader;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (json != null) {
            try {
                Log.d("try", "in the try");
                jsonObj = new JSONObject(json);
                jsonArray = jsonObj.getJSONArray("actors");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject RealObject = jsonArray.getJSONObject(i);
                    CalenderPickerServiceResponce listviewfromServiceGetterSetter = new CalenderPickerServiceResponce();
                    listviewfromServiceGetterSetter.setCalender_name(RealObject.getString("name").toString());
                    Log.v("Tag", "name" + RealObject.getString("name").toString());
                    listviewfromServiceGetterSetter.setCalender_desc(RealObject.getString("country").toString());
                    Log.v("Tag", "country" + RealObject.getString("country").toString());
                    calenderList.add(listviewfromServiceGetterSetter);

                    Log.v("Tag", "list" + calenderList.size());
                    Log.v("Tag", "list" + calenderList.get(0).getCalender_name());

                }
            } catch (JSONException e) {
                Log.d("catch", "in the catch");
                e.printStackTrace();
            }
        } else {
            Log.e("JSON Data", "Didn't receive any data from server!");
        }
        try {
            listviewAdapter = new CalenderPickerListViewAdapter(activity, calenderList);
            Log.v("Tag", "calender" + calenderList);
            listView.setAdapter(listviewAdapter);
            listviewAdapter.notifyDataSetChanged();

        } catch (Exception e) {

        }
    }
}*/

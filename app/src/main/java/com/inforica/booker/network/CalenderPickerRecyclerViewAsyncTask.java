package com.inforica.booker.network;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inforica.booker.R;
import com.inforica.booker.views.MyCustomProgressDialog;
import com.inforica.booker.views.adapters.CalenderPickerRecyclerAdapter;
import com.inforica.booker.model.CalenderPickerServiceResponce;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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
    MyCustomProgressDialog routeProgressDialog;

    String Calender_Url, json;
    JSONParser jsonParser;

        public CalenderPickerRecyclerViewAsyncTask(Activity mActivity, RecyclerView mRecyclerView) {
            this.mActivity = mActivity;
            this.mRecyclerView = mRecyclerView;
            routeProgressDialog = (MyCustomProgressDialog) MyCustomProgressDialog.ctor(mActivity);
            this.Calender_Url = Calender_Url;
        }
/*    public CalenderPickerRecyclerViewAsyncTask() {

    }*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // routeProgressDialog.textView.setText("Loading Data...");
        routeProgressDialog.show();


    }

    @Override
    protected Object doInBackground(Object[] params) {

/*        try {
            jsonParser = new JSONParser();
            json = jsonParser.makeServiceCall(Calender_Url, HttpMethods.POST);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        try {
            String[] Name = new String[]{"The Business", "The Business", "Leob Consulting", "Soma Pilates", "ZenRock Fitness"};
            String Mail[] = new String[]{"The Business", "The Business", "Leob Consulting", "Soma Pilates", "ZenRock Fitness"};

            for (int i = 0; i < Name.length; i++) {
                CalenderPickerServiceResponce calender_picker_service_responce = new CalenderPickerServiceResponce();
                calender_picker_service_responce.setCalender_name(Name[i]);
                calender_picker_service_responce.setCalender_desc(Mail[i]);

                calenderviewlist.add(calender_picker_service_responce);
                Log.v("Tag", "name" + Name[i]);
                Log.v("Tag", "mail" + Mail[i]);
                Log.v("Tag", "list" + calenderviewlist.size());
                Log.v("Tag", "list" + calenderviewlist.get(0).getCalender_name());
            }
        } catch (Exception e) {

        }


        return reader;

    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

 /*       if (json != null) {
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
        }*/
        try {
            calenderPickerRecyclerAdapter = new CalenderPickerRecyclerAdapter(mActivity, calenderviewlist);
            Log.v("Tag", "list" + calenderviewlist);
            mRecyclerView.setAdapter(calenderPickerRecyclerAdapter);
            routeProgressDialog.dismiss();
        } catch (Exception e) {

        }
    }
}

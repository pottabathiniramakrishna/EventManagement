package com.inforica.booker.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.inforica.booker.adapter.CalenderAgendaRecyclerAdapter;
import com.inforica.booker.adapter.CalenderPickerRecyclerAdapter;
import com.inforica.booker.model.CalenderAgendaServiceResponce;
import com.inforica.booker.model.CalenderPickerServiceResponce;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 4/22/2016.
 */
public class CalenderAgendaRecyclerViewAsyncTask extends AsyncTask{
    JSONObject reader, jsonObj;
    JSONArray jsonArray;
    List<NameValuePair> postDataParams;
    String status = "";
    // private RecyclerView.Adapter mAdapter;
    Activity mActivity;
    RecyclerView mRecyclerView;
    CalenderAgendaRecyclerAdapter calenderAgendaRecyclerAdapter;
    ArrayList<CalenderAgendaServiceResponce> calenderagendaviewlist = new ArrayList<CalenderAgendaServiceResponce>();

    String Calender_Url, json;
    JSONParser jsonParser;

    public CalenderAgendaRecyclerViewAsyncTask(Activity mActivity, RecyclerView mRecyclerView) {
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
        this.Calender_Url = Calender_Url;
    }
/*    public CalenderPickerRecyclerViewAsyncTask() {

    }*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
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
            String[] StartTime = new String[]{"9.00AM", "11.00AM", "2.00PM"};
            String EndTime[] = new String[]{"10.00AM", "1.00PM", "3.00PM"};
            String ServiceName[]=new String[]{"Colour Treatment","Hair Cutting","Facials"};
            String StaffName[]=new String[]{"Laura Palmer","Jenny","Francis"};
            String Address[]=new String[]{"United States","WestIndies","SouthAfrica"};

            calenderagendaviewlist = new ArrayList<CalenderAgendaServiceResponce>();

            for (int i = 0; i < StartTime.length; i++) {
                CalenderAgendaServiceResponce calender_agenda_service_response = new CalenderAgendaServiceResponce();
                calender_agenda_service_response.setStart_time(StartTime[i]);
                calender_agenda_service_response.setEnd_time(EndTime[i]);
                calender_agenda_service_response.setService_name(ServiceName[i]);
                calender_agenda_service_response.setCustomer_name(StaffName[i]);
                calender_agenda_service_response.setService_location(Address[i]);

                calenderagendaviewlist.add(calender_agenda_service_response);
                Log.v("Tag", "list" + calenderagendaviewlist.size());
                Log.v("Tag", "list" + calenderagendaviewlist.get(0).getStart_time());
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
            calenderAgendaRecyclerAdapter = new CalenderAgendaRecyclerAdapter(mActivity, calenderagendaviewlist);
            Log.v("Tag", "list" + calenderagendaviewlist);
            mRecyclerView.setAdapter(calenderAgendaRecyclerAdapter);
        } catch (Exception e) {

        }
    }
}

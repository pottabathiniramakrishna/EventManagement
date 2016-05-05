package com.inforica.booker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.inforica.booker.R;
import com.inforica.booker.model.CalenderPickerModel;
import com.inforica.booker.network.CalenderPickerRecyclerViewAsyncTask;
import com.inforica.booker.network.VolleyUtil;
import com.inforica.booker.views.MyCustomProgressDialog;

import org.json.JSONArray;

/**
 * Created by ranjith on 01-05-2016.
 */
public class CalenderPicker extends Fragment {
    private static final String TAG = "CalenderPicker";
    View rootView;

    private RecyclerView calenderRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    CalenderPickerRecyclerViewAsyncTask calender_Picker_List_AsyncTask;

    public CalenderPicker() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.calender_picker_recyclerview_activity, container, false);
        calenderRecyclerView = (RecyclerView) rootView.findViewById(R.id.calender_recycler_list);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        calenderRecyclerView.setLayoutManager(mLayoutManager);
        calender_Picker_List_AsyncTask = new CalenderPickerRecyclerViewAsyncTask(getActivity(), calenderRecyclerView);
        calender_Picker_List_AsyncTask.execute();
        loadData();
        return rootView;
    }

    private void loadData() {


        final MyCustomProgressDialog routeProgressDialog =  (MyCustomProgressDialog) MyCustomProgressDialog.ctor(getContext());
        String tag_json_obj = "json_calender";
        // used for debuging purpose
        String url = "http://vainavisolutions.com/clalenders.json";
        routeProgressDialog.show();
        JsonArrayRequest calenderPickerReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        // Parsing json
                        Gson gson = new Gson();
                        CalenderPickerModel[] myTypes = gson.fromJson(response.toString(), CalenderPickerModel[].class);


                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                    //    adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                routeProgressDialog.dismiss();

            }
        });

        // Adding request to request queue
        VolleyUtil.getInstance().addToRequestQueue(calenderPickerReq, tag_json_obj);
    }


    @Override
    public void onResume () {
        super.onResume ();
    }
}
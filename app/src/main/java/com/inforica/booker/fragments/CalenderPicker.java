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
import com.inforica.booker.R;
import com.inforica.booker.model.CalenderPickerModel;
import com.inforica.booker.network.GsonRequest;
import com.inforica.booker.network.VolleyUtil;
import com.inforica.booker.views.MyCustomProgressDialog;
import com.inforica.booker.views.adapters.CalenderPickerRecyclerAdapter;

/**
 * Created by ranjith on 01-05-2016.
 */
public class CalenderPicker extends Fragment {
    private static final String TAG = "CalenderPicker";
    View rootView;

    private RecyclerView calenderRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    MyCustomProgressDialog routeProgressDialog;

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
        routeProgressDialog =   MyCustomProgressDialog.ctor(getActivity());
      /*  calender_Picker_List_AsyncTask = new CalenderPickerRecyclerViewAsyncTask(getActivity(), calenderRecyclerView);
        calender_Picker_List_AsyncTask.execute();*/
        loadData();
        return rootView;
    }

    private void loadData() {
        Log.d(TAG, "in load adata");

        String tag_json_obj = "json_calender";
        // used for debuging purpose
        String url = "http://vainavisolutions.com/clalenders.json";

        routeProgressDialog.show();
        routeProgressDialog.textView.setText("Loading Data");
        GsonRequest<CalenderPickerModel[]> myGsonReq  = new GsonRequest<CalenderPickerModel[]>(url,CalenderPickerModel[].class,null,
                new Response.Listener<CalenderPickerModel[]>() {
                    @Override
                    public void onResponse(CalenderPickerModel[] response) {
                        Log.d(TAG, response.toString());

                        //populate Data
                        CalenderPickerRecyclerAdapter calenderPickerRecyclerAdapter = new CalenderPickerRecyclerAdapter(getActivity(), response);
                        Log.v("Tag", "list" + response);
                        calenderRecyclerView.setAdapter(calenderPickerRecyclerAdapter);
                        routeProgressDialog.dismiss();
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
        VolleyUtil.getInstance().addToRequestQueue(myGsonReq, tag_json_obj);
    }


    @Override
    public void onResume () {
        super.onResume ();
    }
}
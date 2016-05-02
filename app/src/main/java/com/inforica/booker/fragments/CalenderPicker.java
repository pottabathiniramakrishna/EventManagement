package com.inforica.booker.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.activities.CalenderPickerRecyclerViewActivity;
import com.inforica.booker.network.CalenderPickerRecyclerViewAsyncTask;

/**
 * Created by sbouvanasi on 10-02-2016.
 */
public class CalenderPicker extends Fragment {
    View rootView;
    private LinearLayout home_today_match_layout, home_next_match_layout;
    private Handler handler;
    private Runnable runnable;
    private LinearLayout countDownTimerLayout;
    /**
     * Holds the network connection status.
     */
    private boolean network_status = false;

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
        return rootView;
    }

    @Override
    public void onResume () {
        super.onResume ();
    }
}
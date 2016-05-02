package com.inforica.booker.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inforica.booker.R;

import com.inforica.booker.network.CalenderPickerRecyclerViewAsyncTask;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerRecyclerViewActivity extends SuperActivity {

    private RecyclerView calenderRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    CalenderPickerRecyclerViewAsyncTask calender_Picker_List_AsyncTask;
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_picker_recyclerview_activity);
        calenderRecyclerView = (RecyclerView) findViewById(R.id.calender_recycler_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        calenderRecyclerView.setLayoutManager(mLayoutManager);
        calender_Picker_List_AsyncTask = new CalenderPickerRecyclerViewAsyncTask(CalenderPickerRecyclerViewActivity.this, calenderRecyclerView);
        calender_Picker_List_AsyncTask.execute();
    }
}


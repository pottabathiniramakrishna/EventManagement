package com.inforica.booker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inforica.booker.R;
import com.inforica.booker.adapter.CalenderPickerRecyclerAdapter;
import com.inforica.booker.service.CalenderPickerListAsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerListActivity extends Activity {
    private RecyclerView calenderRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    ArrayList<String> calenderviewlist;
    CalenderPickerListAsyncTask calender_Picker_List_AsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_picker_list_activity);
        calenderRecyclerView = (RecyclerView)findViewById(R.id.calenders_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        calenderRecyclerView.setLayoutManager(mLayoutManager);
        /*mAdapter = new CalenderPickerRecyclerAdapter(this,calenderviewlist);
        calenderRecyclerView.setAdapter(mAdapter);*/
        calender_Picker_List_AsyncTask=  new CalenderPickerListAsyncTask();
        calender_Picker_List_AsyncTask.execute();
    }
}

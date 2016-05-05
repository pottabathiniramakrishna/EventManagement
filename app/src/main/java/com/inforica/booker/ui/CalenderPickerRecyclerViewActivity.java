package com.inforica.booker.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import com.inforica.booker.R;

import com.inforica.booker.service.CalenderPickerRecyclerViewAsyncTask;
import com.inforica.booker.service.ServiceConstants;
import com.inforica.booker.slidingmenu.DrawlayoutHolder;
import com.inforica.booker.utils.SuperclassActivity;

import java.util.ArrayList;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerRecyclerViewActivity extends Activity {

    private RecyclerView calenderRecyclerView;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    CalenderPickerRecyclerViewAsyncTask calender_Picker_List_AsyncTask;
    String Url;
    TextView screen_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_picker_recyclerview_activity);
        screen_name = (TextView) findViewById(R.id.screen_name);
        screen_name.setText("CHOOSE A MAILBOX");
        calenderRecyclerView = (RecyclerView) findViewById(R.id.calender_recycler_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        calenderRecyclerView.setLayoutManager(mLayoutManager);
        calender_Picker_List_AsyncTask = new CalenderPickerRecyclerViewAsyncTask(CalenderPickerRecyclerViewActivity.this, calenderRecyclerView);
        calender_Picker_List_AsyncTask.execute();
    }
}


package com.inforica.booker.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inforica.booker.R;
import com.inforica.booker.adapter.CalenderPickerRecyclerAdapter;
import com.inforica.booker.service.CalenderPickerListAsyncTask;
import com.inforica.booker.service.ServiceConstants;

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
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_picker_list_activity);
        calenderRecyclerView = (RecyclerView) findViewById(R.id.calenders_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        calenderRecyclerView.setLayoutManager(mLayoutManager);
        calenderRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        Url= ServiceConstants.URL_CalenderListView;
        /*mAdapter = new CalenderPickerRecyclerAdapter(this,calenderviewlist);
        calenderRecyclerView.setAdapter(mAdapter);*/
        calender_Picker_List_AsyncTask = new CalenderPickerListAsyncTask(CalenderPickerListActivity.this, calenderRecyclerView,Url);
        calender_Picker_List_AsyncTask.execute();
    }
}
class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;

    public SimpleDividerItemDecoration(Resources resources) {
        mDivider = resources.getDrawable(R.drawable.line_divider);
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            //   int bottom =  mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
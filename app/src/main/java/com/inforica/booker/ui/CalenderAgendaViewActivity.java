package com.inforica.booker.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.service.CalenderAgendaRecyclerViewAsyncTask;
import com.inforica.booker.service.CalenderPickerRecyclerViewAsyncTask;
import com.inforica.booker.slidingmenu.DrawlayoutHolder;
import com.inforica.booker.utils.SuperclassActivity;
import com.wefika.calendar.CollapseCalendarView;
import com.wefika.calendar.manager.CalendarManager;

import org.joda.time.LocalDate;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 4/22/2016.
 */
public class CalenderAgendaViewActivity extends DrawlayoutHolder {
    private CollapseCalendarView mCalendarView;
    TextView current_date;
    private RecyclerView calender_agenda_recycler_view;
    LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    CalenderAgendaRecyclerViewAsyncTask calender_Picker_List_AsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_agenda_view_activity);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mdrawlayoutEnable = true;
        mActionbarEnable = true;
        Drawlayout_views(CalenderAgendaViewActivity.this, 0, "CalenderAgendaViewActivity");
        if (mDrawerLayout.isDrawerVisible(leftRL)) {
            mDrawerLayout.closeDrawer(leftRL);
        }


        CalendarManager manager = new CalendarManager(LocalDate.now(), CalendarManager.State.MONTH, LocalDate.now(), LocalDate.now().plusYears(1));

        mCalendarView = (CollapseCalendarView) findViewById(R.id.calendar);
        mCalendarView.init(manager);
        current_date = (TextView) findViewById(R.id.current_date);
        Format formatter = new SimpleDateFormat("EEEE,  MMMM dd");
        String today = formatter.format(new Date());
        current_date.setText(today);
        calender_agenda_recycler_view = (RecyclerView) findViewById(R.id.calender_agenda_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        calender_agenda_recycler_view.setLayoutManager(mLayoutManager);
        calender_Picker_List_AsyncTask = new CalenderAgendaRecyclerViewAsyncTask(CalenderAgendaViewActivity.this, calender_agenda_recycler_view);
        calender_Picker_List_AsyncTask.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getclassname = getClass().getSimpleName().toString();
    }
}

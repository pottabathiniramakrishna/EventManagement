package com.inforica.booker.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.model.CalenderPickerServiceResponce;
import com.inforica.booker.activities.CalenderAgendaViewActivity;

import java.util.ArrayList;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mActivity;
    //    ArrayList<String> contact_names;
    ArrayList<CalenderPickerServiceResponce> calenderviewlist;

    public CalenderPickerRecyclerAdapter(Activity mContext, ArrayList<CalenderPickerServiceResponce> calenderviewlist) {
        this.mActivity = mContext;
        this.calenderviewlist = calenderviewlist;
    }

    public class CalenderlistViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView description;

        public CalenderlistViewHolder(View v) {
            super(v);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent calender_agenda_intent = new Intent(mActivity, CalenderAgendaViewActivity.class);
                    calender_agenda_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mActivity.startActivity(calender_agenda_intent);

                  /*  mActivity.getSupportFragmentManager().beginTransaction ()
                            .setCustomAnimations (R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                            .replace (R.id.container_body, fragment)
                            .addToBackStack (null)
                            .commit ();*/
                }
            });

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_picker_list_inflator, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CalenderlistViewHolder vh = new CalenderlistViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CalenderlistViewHolder) {
            CalenderlistViewHolder calender_view_holder = (CalenderlistViewHolder) holder;
            calender_view_holder.title.setText(calenderviewlist.get(position).getCalender_name());
            calender_view_holder.description.setText(calenderviewlist.get(position).getCalender_desc());
//            dataitems.get calenderviewlist.get(position);
        }
    }

    @Override
    public int getItemCount() {
        Log.v("Tag", "list_size_in adapter" + calenderviewlist.size());

        return calenderviewlist.size();
    }
}
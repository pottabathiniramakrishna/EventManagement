package com.inforica.booker.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.model.CalenderAgendaServiceResponce;
import com.inforica.booker.model.CalenderPickerServiceResponce;
import com.inforica.booker.ui.BookerDetailsActivity;
import com.inforica.booker.ui.CalenderAgendaViewActivity;

import java.util.ArrayList;

/**
 * Created by user on 4/22/2016.
 */
public class CalenderAgendaRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mActivity;
    ArrayList<CalenderAgendaServiceResponce> calenderagendaviewlist;

    public CalenderAgendaRecyclerAdapter(Activity mContext, ArrayList<CalenderAgendaServiceResponce> calenderagendaviewlist) {
        this.mActivity = mContext;
        this.calenderagendaviewlist = calenderagendaviewlist;
    }

    public class CalenderAgendalistViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView start_time;
        public TextView end_time;
        public TextView service_name;
        public TextView customer_name;
        public TextView service_location;


        public CalenderAgendalistViewHolder(View v) {
            super(v);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            end_time = (TextView) itemView.findViewById(R.id.end_time);
            service_name = (TextView) itemView.findViewById(R.id.service_name);
            customer_name = (TextView) itemView.findViewById(R.id.customer_name);
            service_location = (TextView) itemView.findViewById(R.id.service_location);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent booikng_details_intent=new Intent(mActivity,BookerDetailsActivity.class);
                    booikng_details_intent.putExtra("Customer_Name", calenderagendaviewlist.get(getPosition()).getCustomer_name());
                    booikng_details_intent.putExtra("Start_Time", calenderagendaviewlist.get(getPosition()).getStart_time());
                    booikng_details_intent.putExtra("End_Time", calenderagendaviewlist.get(getPosition()).getEnd_time());
                    booikng_details_intent.putExtra("Service_Name", calenderagendaviewlist.get(getPosition()).getService_name());
                    booikng_details_intent.putExtra("Service_Location", calenderagendaviewlist.get(getPosition()).getService_location());
//                    booikng_details_intent.putExtra("Customer_Name",calenderagendaviewlist.get(getAdapterPosition()).getCustomer_name());
                    booikng_details_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mActivity.startActivity(booikng_details_intent);

                }
            });

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calende_agenda_recycler_inflator, parent, false);
        // set the view's size, margins, paddings and layout parameters
        CalenderAgendalistViewHolder vh = new CalenderAgendalistViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof CalenderAgendalistViewHolder) {
            CalenderAgendalistViewHolder calender_agenda_view_holder = (CalenderAgendalistViewHolder) holder;
            calender_agenda_view_holder.start_time.setText(calenderagendaviewlist.get(position).getStart_time().toString());
            calender_agenda_view_holder.end_time.setText(calenderagendaviewlist.get(position).getEnd_time().toString());
            calender_agenda_view_holder.service_name.setText(calenderagendaviewlist.get(position).getService_name().toString());
            calender_agenda_view_holder.customer_name.setText(calenderagendaviewlist.get(position).getCustomer_name().toString());
            calender_agenda_view_holder.service_location.setText(calenderagendaviewlist.get(position).getService_location().toString());
        }
    }

    @Override
    public int getItemCount() {
        Log.v("Tag", "list_size_in adapter" + calenderagendaviewlist.size());

        return calenderagendaviewlist.size();
    }
}

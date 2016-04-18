package com.inforica.booker.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.inforica.booker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 4/18/2016.
 */
public class CalenderPickerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mContext;
    //    ArrayList<String> contact_names;
    ArrayList<String> contacts_list;

    public CalenderPickerRecyclerAdapter(Activity mContext, ArrayList<String> contacts_list) {
        this.mContext = mContext;
        this.contacts_list = contacts_list;
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
            //     allTotalItems_total_cost = totalItems_fare();
            CalenderlistViewHolder contact_view_holder = (CalenderlistViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return contacts_list.size();
    }
}
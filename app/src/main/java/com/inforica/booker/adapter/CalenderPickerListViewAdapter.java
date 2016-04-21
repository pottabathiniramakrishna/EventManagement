package com.inforica.booker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.model.CalenderPickerServiceResponce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guest1 on 4/21/2016.
 */
public class CalenderPickerListViewAdapter extends BaseAdapter {
    private Context context;
    ArrayList<CalenderPickerServiceResponce> calenderviewlist;


    public CalenderPickerListViewAdapter(Context context, ArrayList<CalenderPickerServiceResponce> calenderviewlist) {
        this.context = context;
        this.calenderviewlist = calenderviewlist;
    }


    @Override
    public int getCount() {
        return calenderviewlist.size();
    }

    @Override
    public Object getItem(int position) {
        return calenderviewlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return calenderviewlist.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.calender_picker_list_inflator, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(calenderviewlist.get(position).getCalender_name());
        holder.description.setText(calenderviewlist.get(position).getCalender_desc());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView description;
    }
}

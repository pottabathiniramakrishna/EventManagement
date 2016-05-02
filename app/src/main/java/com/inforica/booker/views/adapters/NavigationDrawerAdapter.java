package com.inforica.booker.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.model.NavDrawerItem;

import java.util.Collections;
import java.util.List;

/**
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList ();
    private LayoutInflater inflater;
    private Context context;

    int[] nav_drawer_imgs = new int[]{R.drawable.feedback,
            R.drawable.feedback,
            R.drawable.suggestion,
            R.drawable.close};

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from (context);
        this.data = data;
    }

    public void delete (int position) {
        data.remove (position);
        notifyItemRemoved (position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0: {
                View view = inflater.inflate(R.layout.nav_drawer_email_row, parent, false);
                MyEmailViewHolder holder = new MyEmailViewHolder(view);
                return holder;
                }

            default:
                View view = inflater.inflate (R.layout.nav_drawer_row, parent, false);
                MyViewHolder holder = new MyViewHolder (view);
                return holder;

        }


    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        Log.i("Ranjith","Viewholder pos:"+position);

        if(holder instanceof MyEmailViewHolder){
            MyEmailViewHolder mHolder = (MyEmailViewHolder)holder;
            NavDrawerItem current = data.get(position);
            mHolder.name.setText("ZenRock Fitness");
            mHolder.email.setText("zenrock@microsoft.com");
        } else {
            MyViewHolder mHolder = (MyViewHolder)holder;
            NavDrawerItem current = data.get(position);
            mHolder.title.setText(current.getTitle());
            mHolder.navImgView.setImageResource(nav_drawer_imgs[position]);
        }
    }

    @Override
    public int getItemCount () {
        return data.size ();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView navImgView;
        TextView title;

        public MyViewHolder (View itemView) {
            super (itemView);
            title = (TextView) itemView.findViewById (R.id.navigationdrawer_title);
            navImgView = (ImageView) itemView.findViewById (R.id.nav_img_view);
        }
    }

    class MyEmailViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView name;

        public MyEmailViewHolder (View itemView) {
            super (itemView);
            name = (TextView) itemView.findViewById (R.id.navigationdrawer_email_name);
            email = (TextView) itemView.findViewById (R.id.navigationdrawer_email);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }
}
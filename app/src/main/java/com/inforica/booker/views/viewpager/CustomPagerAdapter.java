package com.inforica.booker.views.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.inforica.booker.R;

import java.util.ArrayList;


/**
 * Created by user on 2/29/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    ArrayList<String> mResources = new ArrayList<>();
    Context mContext;
    LayoutInflater mLayoutInflater;
    ImageView custompage_imageView;
    public CustomPagerAdapter(Context context, ArrayList<String> mResources) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item,
                container, false);


        try {
            custompage_imageView = (ImageView) itemView
                    .findViewById(R.id.imageView);

            Glide.with(container.getContext()).load(mResources.get(position).toString()).placeholder(R.drawable.whitelogo).into(custompage_imageView);
        } catch (Exception e) {

        }

        container.addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

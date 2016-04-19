package com.inforica.booker.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.viewpager.CirclePageIndicator;
import com.inforica.booker.viewpager.CustomPagerAdapter;
import com.inforica.booker.viewpager.PageIndicator;

/**
 * Created by user on 4/19/2016.
 */
public class StartUpPageInstructionsActivity extends Activity {
    static int[] mResources = {
            R.drawable.whitelogo,
            R.drawable.whitelogo,
            R.drawable.whitelogo
    };
    private String[] mResourceTitles;
    private String[] mResourceDescs;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    ImageView imageView;
    TextView page_title, page_description;

    private Runnable animateViewPager;
    private static final long ANIM_VIEWPAGER_DELAY = 3000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 1000;

    private Handler handler;
    PageIndicator mIndicator;
    LinearLayout child_frame_LinearLayout;
    Button skip_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.startup_page_instructions_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCustomPagerAdapter = new CustomPagerAdapter(this);
        mResourceTitles = getResources().getStringArray(R.array.page_titles_list);
        mResourceDescs = getResources().getStringArray(R.array.page_dscriptions_list);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);

        child_frame_LinearLayout = (LinearLayout) findViewById(R.id.child_drawer_frame);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        // calls when touch release on ViewPager
                        if (mResources != null && mResources.length != 0) {
                            runnable(mResources.length);
                            handler.postDelayed(animateViewPager,
                                    ANIM_VIEWPAGER_DELAY_USER_VIEW);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        // calls when ViewPager touch
                        if (handler != null) {
                            handler.removeCallbacks(animateViewPager);
                        }
                        break;
                }
                return false;
            }
        });
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            Log.v("tag", "page adapter");
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.startuppager_item, container, false);

            page_title = (TextView) itemView.findViewById(R.id.page_title);
            page_description = (TextView) itemView.findViewById(R.id.page_description);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);
            page_title.setText(mResourceTitles[position]);
            page_description.setText(mResourceDescs[position]);

            container.addView(itemView);


            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }


    public void runnable(final int size) {
        handler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (mViewPager.getCurrentItem() == size - 1) {
                    mViewPager.setCurrentItem(size - 1);
                } else {
                    mViewPager.setCurrentItem(
                            mViewPager.getCurrentItem() + 0, true);
                }
                handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);

            }
        };
    }

    @Override
    public void onPause() {

        if (handler != null) {
            //Remove callback
            handler.removeCallbacks(animateViewPager);
        }
        super.onPause();
    }
}

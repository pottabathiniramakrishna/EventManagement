package com.inforica.booker;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.localytics.android.LocalyticsActivityLifecycleCallbacks;

/**
 * Created by ranjith on 29-04-2016.
 */
public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        // Register LocalyticsActivityLifecycleCallbacks
        registerActivityLifecycleCallbacks(
                new LocalyticsActivityLifecycleCallbacks(this));

        mInstance = this;
    }

    /**
     * Initiates only single object of MyApplication
     * @return mInstance, instance of the application
     */
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
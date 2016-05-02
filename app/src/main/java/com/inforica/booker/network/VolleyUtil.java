package com.inforica.booker.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.inforica.booker.MyApplication;

/**
 * Created by ranjith on 01-05-2016.
 */
public class VolleyUtil {
    public static final String TAG = MyApplication.class.getSimpleName();
    private static VolleyUtil mInstance;
    private  RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    /* A private Constructor prevents any other
    * class from instantiating.
    */
    private VolleyUtil() {    }

    public static synchronized VolleyUtil getInstance() {
        if(mInstance == null) {
            mInstance = new VolleyUtil();
        }
        return mInstance;
    }

    public   RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(MyApplication.getInstance().getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * Checks the internet connection availability
     *
     * @param _activity instance of the activity
     * @return boolean true-if internet is present in the device, false-otherwise
     */
    public static boolean hasNetworkConnection (Context _activity) {

        ConnectivityManager conMgr = (ConnectivityManager) _activity.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo () != null
                && conMgr.getActiveNetworkInfo ().isAvailable ()
                && conMgr.getActiveNetworkInfo ().isConnected ())
            return true;
        else
            return false;
    }//checkInternetConnection()
}

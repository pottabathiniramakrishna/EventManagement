package com.inforica.booker.slidingmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.inforica.booker.R;
import com.inforica.booker.ui.GiveFeedBackActivity;
import com.inforica.booker.ui.SuggestFeatureActivity;

import java.lang.reflect.Method;

/**
 * Created by user on 5/4/2016.
 */
public class DrawlayoutHolder extends ActionBarActivity {
    public static TextView tool_bar_title_TextView;
    public static RippleView back_button, edit_button;
    ImageView logoutimage;
    Activity activity;
    public DrawerLayout mDrawerLayout;
    public LinearLayout leftRL;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MenuItem menu_searchitem, menu_cartitem, menu_home;
    private ListView categories_expandable_list;
    private ListviewBaseadapter expandabledraweradapter;
    public int clickedPosition = 0;
    private String className = "";
    View action_logo;
    /*check to enable Actionbar*/
    public static boolean mActionbarEnable = true;

    /*check to enable drawlayout*/
    public static boolean mdrawlayoutEnable = true;
    /*get current activity name*/
    public static String getclassname;
    RelativeLayout drawerView;
    RelativeLayout mainView;

    public void Drawlayout_views(Activity activity, int clickedPosition, String className) {

        this.activity = activity;
        this.clickedPosition = clickedPosition;
        this.className = className;
        Log.v("menupanel", "activity=" + activity);
        Log.v("menupanel", "clickedPosition=" + clickedPosition);
        Log.v("menupanel", "className=" + className);


//
//        BaseClass.isMainActivity = (Application_holder.getclassname.equalsIgnoreCase("MainActivity")) ? true : false;//is search activity


        if (mActionbarEnable) {
            actionbar_attach();
        }


        if (mdrawlayoutEnable) {
            drawerLayout_attach();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        if (BaseClass.isMainActivity)
        if (className.equals("CalenderAgendaViewActivity"))
            actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
//        if (BaseClass.isMainActivity)
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    Log.e("TAG", "onMenuOpened", e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_MENU:

                    // mainMenu.performIdentifierAction(R.id.more, 0);

                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void asign_Toggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * categories_expandable_list.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };


        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void actionbar_attach() {

        /****** Add toolbar ********/
        mToolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        tool_bar_title_TextView = (TextView) findViewById(R.id.screen_name);
        back_button = (RippleView) findViewById(R.id.back_button);
        back_button.setVisibility(View.GONE);
        edit_button = (RippleView) findViewById(R.id.edit_button);
        edit_button.setVisibility(View.GONE);
//        logoutimage = (ImageView) findViewById(R.id.logoutimage);
    }


    private void drawerLayout_attach() {
        mainView = (RelativeLayout) findViewById(R.id.mainView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (className.equalsIgnoreCase("CalenderAgendaViewActivity")) //main activity
        {

            asign_Toggle();//asign toggle to the navigation drawer

        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        leftRL = (LinearLayout) findViewById(R.id.LeftDrawer);
        categories_expandable_list = (ListView) findViewById(R.id.drawer_list);

        listview();

    }

    public void listview() {
        expandabledraweradapter = new ListviewBaseadapter(this, clickedPosition);
        categories_expandable_list.setAdapter(expandabledraweradapter);
        categories_expandable_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("menupanel", "classNameinlistview=" + className);
                Log.v("menupanel", "clickedPositioninlistview=" + clickedPosition);
                Log.v("menupanel", "position=" + position);
                String[] nameslist = new String[]{"Home", "Shared with me", "Recent History", "Profile", "Settings"};
                if (clickedPosition == 0 && className.equals("CalenderAgendaViewActivity")) {
                    mDrawerLayout.closeDrawer(leftRL);
                }
                if (position == 0) {
                    Intent homeIntent = new Intent(activity, GiveFeedBackActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(homeIntent);
                    mDrawerLayout.closeDrawer(leftRL);
                } else if (position == 1) {
                    mDrawerLayout.closeDrawer(leftRL);
                    Intent faqIntent = new Intent(activity, SuggestFeatureActivity.class);
                    faqIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(faqIntent);
                } else if (position == 2) {
                    mDrawerLayout.closeDrawer(leftRL);
                } else {
                    mDrawerLayout.closeDrawer(leftRL);

                }
            }
        });

    }

    //////////List view adapter
    public class ListviewBaseadapter extends BaseAdapter {
        Context context;
        int clickedPosition = 0;

        ListviewBaseadapter(Context context, int clickedPosition) {
            this.context = context;
            this.clickedPosition = clickedPosition;
        }

        String[] nameslist = new String[]{"Give feedback", "Suggest a feature", "Logout"};
        private Integer[] Imageslist = {
                R.drawable.ic_email_white_18dp, R.drawable.whitelogo,
                R.drawable.ic_cancel_white_18dp


        };
    /*    private Integer[] selectedImageslist = {
                R.drawable.home_selected, R.drawable.share2,
                R.drawable.history_selected, R.drawable.profile_selected,
                R.drawable.settings_selected


        };*/

        public int getCount() {

            return nameslist.length;
        }

        public Object getItem(int position) {

            return nameslist[position];
        }


        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.menuicon_listadapter, null);
            v.setId(position);
            LinearLayout l = (LinearLayout) v.findViewById(R.id.LinearLayout01);
            final TextView classname = (TextView) v.findViewById(R.id.textView1);
            final ImageView imageforclass = (ImageView) v.findViewById(R.id.imageView1);
            classname.setText(nameslist[position].toString());
            imageforclass.setImageResource(Imageslist[position]);
//            imageforclass.setImageResource(Imageslist[position]);
        /*    if (clickedPosition == position) {
                classname.setTextColor(context.getResources().getColor(R.color.button_color));
                imageforclass.setImageResource(selectedImageslist[position]);
            } else {
                imageforclass.setImageResource(Imageslist[position]);
            }*/

            return v;

        }


    }

    // Method to check the internet connection..
    public boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();


    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}



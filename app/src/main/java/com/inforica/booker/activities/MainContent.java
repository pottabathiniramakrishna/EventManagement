package com.inforica.booker.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.inforica.booker.R;
import com.inforica.booker.fragments.FragmentDrawer;
import com.inforica.booker.fragments.CalenderPicker;

/**
 * Created by ranjith on 01-05-2016.
 */
public class MainContent extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainContent.class.getSimpleName();
    private boolean network_status = false;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled (true);
        getSupportActionBar().setDisplayHomeAsUpEnabled (true);
        drawerFragment = (FragmentDrawer) getSupportFragmentManager ().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);
        drawerFragment.setDrawerListener(this);
        ((TextView) mToolbar.getRootView().findViewById(R.id.screen_name)).setText("CHOOSE A MAILBOX");
        // display the first navigation drawer view on app launch
        displayView(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate (R.menu.menu, menu);
        MenuItem item = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(item.isChecked())
                item.setChecked(false);
            else
                item.setChecked(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView (position + 1);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CalenderPicker();
                break;
            case 1:
                fragment = new CalenderPicker();
                break;
            case 2:
                fragment = new CalenderPicker();
                break;
            case 3:
                fragment = new CalenderPicker();
                break;

            default:
                break;
        } // switch case end
        if (fragment != null) {
            getSupportFragmentManager ().beginTransaction ()
                    .setCustomAnimations (R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                    .replace (R.id.container_body, fragment)
                    .addToBackStack (null)
                    .commit ();
        } // fragment null check end
    } // displayView end

}

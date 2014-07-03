package com.andrewq.planets;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Andrew Quebe on 3/12/2014.
 */
public class Main extends FragmentActivity {

    //Variables
    private DrawerLayout mDrawerLayout;
    private ListView mainItemsList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mFragmentTitles;

    private ActionBar mActionBar;

    //Called when the user first opens the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the layout
        setContentView(R.layout.main);

        //Enable the wakelock
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Initialize variables
        mTitle = mDrawerTitle = getTitle();
        mFragmentTitles = getResources().getStringArray(R.array.fragments);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mainItemsList = (ListView) findViewById(R.id.main_items_drawer);

        mainItemsList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mFragmentTitles));
        mainItemsList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mainItemsList.setClipToPadding(false);
        setInsets(this, mainItemsList);

        //Handle the drawer toggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer_dark, R.string.drawer_open,
                R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View v) {
                getActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View v) {
                getActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }

        //Start the application with the drawer open if it is the first time they have opened it
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("key", true);

                if (isFirstStart) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("key", false);
                    e.commit();
                }
            }
        });

        t.start();
    }

    //Handle theme changer options
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs.getString("prefSetTheme", "3"));
        //Get an instance of the ActionBar
        mActionBar = getActionBar();
        final SystemBarTintManager tintManager = new SystemBarTintManager(this);

        tintManager.setStatusBarTintEnabled(true);

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (theme_chooser == 1) {
            //Red
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cc0202")));

            int actionBarColor = Color.parseColor("#cc0202");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 2) {
            //Orange
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8801")));

            int actionBarColor = Color.parseColor("#ff8801");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 3) {
            //Blue
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0497c9")));

            int actionBarColor = Color.parseColor("#0497c9");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 4) {
            //Green
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#679a03")));

            int actionBarColor = Color.parseColor("#679a03");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 5) {
            //Purple
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9832cb")));

            int actionBarColor = Color.parseColor("#9832cb");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 6) {
            //Black
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#292929")));

            int actionBarColor = Color.parseColor("#292929");
            tintManager.setStatusBarTintColor(actionBarColor);
        }
    }

    //Called when the application is reopened
    @Override
    protected void onResume() {
        super.onResume();

        //Resume wakelock
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //Called when the application is cleared in the recent apps section of the Android System
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Clear wakelock
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    //Called when the user presses the home button or goes to another application
    @Override
    protected void onStop() {
        super.onStop();

        //Close Google Analytics
        EasyTracker.getInstance(this).activityStop(this);

        //Clear wakelock
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //Called when the user leaves the application
    @Override
    protected void onPause() {
        super.onPause();
    }

    //Handle drawer toggle
    @SuppressWarnings("unused")
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mainItemsList);
        return super.onPrepareOptionsMenu(menu);
    }

    //Inflate the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Handle what happens when an action bar item is pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(mainItemsList)) {
                    mDrawerLayout.closeDrawer(mainItemsList);
                } else {
                    mDrawerLayout.openDrawer(mainItemsList);
                }
                return true;
        }

        return true;
    }

    //Handle what happens when each nav drawer item is pressed
    private void selectItem(int position) {
        Fragment newFragment = new FragmentPlanets();
        FragmentManager fm = getSupportFragmentManager();
        switch (position) {
            case 0:
                newFragment = new FragmentPlanets();
                break;
            case 1:
                newFragment = new OtherBodiesFragment();
                break;
            case 2:
                newFragment = new ImageGallery();
                break;
            case 3:
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }

        fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();

        mainItemsList.setItemChecked(position, true);
        if (position == 0 || position == 3) {
            setTitle("Milky Way");
        } else {
            setTitle(mFragmentTitles[position]);
        }
        mDrawerLayout.closeDrawer(mainItemsList);
    }

    //Set the title of the nav drawer
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    public void setInsets(Activity context, View transView) {

        SystemBarTintManager tintManager = new SystemBarTintManager(context);

        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        transView.setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,
                                long id) {
            selectItem(position);
        }
    }

}
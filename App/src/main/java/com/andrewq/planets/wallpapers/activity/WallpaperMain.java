package com.andrewq.planets.wallpapers.activity;

/**
 * Created by andrew on 8/1/15.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.andrewq.planets.R;
import com.andrewq.planets.util.SlidingTabLayoutScrollable;
import com.andrewq.planets.wallpapers.util.ViewPagerAdapter;

public class WallpaperMain extends ActionBarActivity {

    // Declaring Your View and Variables
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayoutScrollable tabs;
    CharSequence Titles[] = {"David Ratkovic", "Anas Khan", "Adrien Zier", "Person 4", "Person 5"};
    int Numboftabs = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper_main_activity);

        // Create The Toolbar and setting it as the Toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Create The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assigning the Sliding Tab Layout View
        tabs = (SlidingTabLayoutScrollable) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(false); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayoutScrollable.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.parseColor("#ffffff");
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wallpaper_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.infoButton) {
            new MaterialDialog.Builder(this)
                    .title("About This Feature")
                    .content("This feature is still in testing. If you're on a tablet, please refrain from using phone wallpapers " +
                            "and vice versa." +
                            "This will be addressed in future updates." +
                            "\n\n" +
                            "All wallpapers are given to the app developer (Andrew Quebe) and credit is shown below each wallpaper. " +
                            "If you'd like to submit your own wallpapers, please contact the developer. Wallpapers must be space " +
                            "related and made by you. Please " +
                            "don't send photos that you found on Google. Be creative :)\n\n" +
                            "Enjoy!")
                    .neutralText("Close")
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

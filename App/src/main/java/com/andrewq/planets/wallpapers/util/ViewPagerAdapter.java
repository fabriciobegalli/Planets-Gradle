package com.andrewq.planets.wallpapers.util;

/**
 * Created by andrew on 8/1/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andrewq.planets.wallpapers.tabs.Tab1;
import com.andrewq.planets.wallpapers.tabs.Tab2;
import com.andrewq.planets.wallpapers.tabs.Tab3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) { // if the position is 0 we are returning the First tab
            Tab1 tab1 = new Tab1();
            return tab1;
        } else if (position == 1) {
            Tab2 tab2 = new Tab2();
            return tab2;
        } else {
            Tab3 tab3 = new Tab3();
            return tab3;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}

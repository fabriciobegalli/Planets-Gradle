package com.andrewq.planets;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.analytics.tracking.android.EasyTracker;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Andrew Quebe on 5/18/2014.
 */
public class OtherBodiesFragment extends Fragment {

    private ViewPager viewPager;
    private ViewPager viewPagerTrans;
    private PagerTitleStrip pagerTitleStrip;

    public OtherBodiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerTrans = (ViewPager) getView().findViewById(R.id.pager_bodies);

        viewPagerTrans.setClipToPadding(false);
        setInsets(getActivity(), viewPagerTrans);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(getActivity()).activityStart(getActivity());  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(getActivity()).activityStop(getActivity());  // Add this method.
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager = (ViewPager) getView().findViewById(R.id.pager_bodies);
        viewPager.setAdapter(new BodiesAdapter(getFragmentManager()));

        pagerTitleStrip = (PagerTitleStrip) getView().findViewById(R.id.pager_title_strip_bodies);

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getActivity());
        int theme_chooser = Integer.parseInt(getPrefs.getString("prefSetTheme", "1"));
        pagerTitleStrip.setTextColor(Color.parseColor("#ffffff"));

        if (theme_chooser == 1) {
            //Red
            pagerTitleStrip.setBackgroundColor(Color.parseColor("#ff4646"));
        } else if (theme_chooser == 2) {
            //Orange
            pagerTitleStrip.setBackgroundColor(Color.parseColor("#fdba35"));
        } else if (theme_chooser == 3) {
            //Blue
            pagerTitleStrip.setBackgroundColor(Color.parseColor("#33b5e5"));
        } else if (theme_chooser == 4) {
            //Green
            pagerTitleStrip.setBackgroundColor(Color.parseColor("#9acc04"));
        } else if (theme_chooser == 5) {
            //Purple
            pagerTitleStrip.setBackgroundColor(Color.parseColor("#aa65cc"));
        } else {
            //Black
            pagerTitleStrip.setBackgroundColor(Color.parseColor("#767676"));
        }
    }

    public void setInsets(Activity context, View transView) {

        SystemBarTintManager tintManager = new SystemBarTintManager(context);

        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        transView.setPadding(0, config.getPixelInsetTop(true), 0, config.getPixelInsetBottom());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bodies, container, false);
    }
}

class BodiesAdapter extends FragmentStatePagerAdapter {

    public BodiesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment fragment = null;
        switch (arg0) {
            case 0:
                fragment = new PlutoFragment();
                break;
            case 1:
                fragment = new HaleysCometFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Pluto";
            case 1:
                return "Halley's Comet";
        }
        return null;
    }
}
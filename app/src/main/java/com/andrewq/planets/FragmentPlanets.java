package com.andrewq.planets;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.analytics.tracking.android.EasyTracker;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class FragmentPlanets extends Fragment {

    ViewPager viewPagerTrans;
    ViewPager mPager;
    PageIndicator mIndicator;

    public FragmentPlanets() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerTrans = (ViewPager) getView().findViewById(R.id.pager);

        viewPagerTrans.setPageTransformer(true, new ZoomOutPageTransformer());

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

        ViewPager.PageTransformer transformer = new ZoomOutPageTransformer();

        mPager = (ViewPager) getView().findViewById(R.id.pager);
        mPager.setAdapter(new PlanetsAdapter(getFragmentManager()));

        mIndicator = (LinePageIndicator) getView().findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
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
        return inflater.inflate(R.layout.fragment_planets, container, false);
    }
}

class PlanetsAdapter extends FragmentStatePagerAdapter {

    public PlanetsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment fragment = null;
        switch (arg0) {
            case 0:
                fragment = new FragmentA();
                break;
            case 1:
                fragment = new FragmentB();
                break;
            case 2:
                fragment = new FragmentC();
                break;
            case 3:
                fragment = new FragmentD();
                break;
            case 4:
                fragment = new FragmentE();
                break;
            case 5:
                fragment = new FragmentF();
                break;
            case 6:
                fragment = new FragmentG();
                break;
            case 7:
                fragment = new FragmentH();
                break;
            case 8:
                fragment = new FragmentI();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 9;
    }
}
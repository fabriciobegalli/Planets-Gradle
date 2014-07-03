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

/**
 * Created by Andrew Quebe on 5/18/2014.
 */
public class OtherBodiesFragment extends Fragment {

    ViewPager viewPagerTransB;
    ViewPager mPagerB;
    PageIndicator mIndicatorB;

    public OtherBodiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerTransB = (ViewPager) getView().findViewById(R.id.pagerB);

        viewPagerTransB.setPageTransformer(true, new ZoomOutPageTransformer());

        viewPagerTransB.setClipToPadding(false);
        setInsets(getActivity(), viewPagerTransB);
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

        mPagerB = (ViewPager) getView().findViewById(R.id.pagerB);
        mPagerB.setAdapter(new BodiesAdapter(getFragmentManager()));

        mIndicatorB = (LinePageIndicator) getView().findViewById(R.id.indicatorB);
        mIndicatorB.setViewPager(mPagerB);
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
}
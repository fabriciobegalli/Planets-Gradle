package com.andrewq.planets.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewq.planets.R;
import com.andrewq.planets.moons.DeimosActivity;
import com.andrewq.planets.moons.EuropaActivity;
import com.andrewq.planets.moons.MoonActivity;
import com.andrewq.planets.moons.PhobosActivity;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2/27/15.
 */
public class FragmentMoons extends Fragment {

    public static GridView gridView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getActivity().findViewById(R.id.gridview);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

        //Give sort_chooser the preference key defined in XML
        int sort_chooser = Integer.parseInt(getPrefs2.getString("prefSortBy", "1"));

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (sort_chooser == 1) {
            //Traditional
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 2) {
            //A - Z
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 3) {
            //Z - A
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 4) {
            //Smallest to Largest
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 5) {
            //Largest to Smallest
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }
                }
            });
        } else {
            //Traditional
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        }
    }

    public FragmentMoons() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        gridView = (GridView) getActivity().findViewById(R.id.gridview);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

        //Give sort_chooser the preference key defined in XML
        int sort_chooser = Integer.parseInt(getPrefs2.getString("prefSortBy", "1"));

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (sort_chooser == 1) {
            //Traditional
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 2) {
            //A - Z
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 3) {
            //Z - A
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 4) {
            //Smallest to Largest
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        } else if (sort_chooser == 5) {
            //Largest to Smallest
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }
                }
            });
        } else {
            //Traditional
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    switch (position) {
                        case 0:
                            Intent i2 = new Intent(getActivity().getApplicationContext(),
                                    MoonActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i2, scaleBundle2);
                            break;
                        case 1:
                            Intent i3 = new Intent(getActivity().getApplicationContext(),
                                    PhobosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i3, scaleBundle3);
                            break;
                        case 2:
                            Intent i4 = new Intent(getActivity().getApplicationContext(),
                                    DeimosActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i4, scaleBundle4);
                            break;
                        case 3:
                            Intent i5 = new Intent(getActivity().getApplicationContext(),
                                    EuropaActivity.class);
                            //Scale animation is sent as a bundle to the next activity.
                            Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                    v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                            //then start the activity, and send the bundle
                            startActivity(i5, scaleBundle5);
                            break;
                    }

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gallery_main, container, false);
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;


        public MyAdapter() {
            inflater = LayoutInflater.from(getActivity().getApplicationContext());

            //Set getPrefs to a preference manager
            SharedPreferences getPrefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

            //Give sort_chooser the preference key defined in XML
            int sort_chooser = Integer.parseInt(getPrefs2.getString("prefSortBy", "1"));

            //Set the action bar colors to whatever the user selects from the ListPreference
            if (sort_chooser == 1) {
                //Traditional
                items.add(new Item("Our Moon", R.drawable.moon, "Orbits Earth"));
                items.add(new Item("Phobos", R.drawable.phobos, "Orbits Mars"));
                items.add(new Item("Deimos", R.drawable.deimos, "Orbits Mars"));
                items.add(new Item("Europa", R.drawable.europa, "Orbits Jupiter"));
            } else if (sort_chooser == 2) {
                //A - Z
                items.add(new Item("Deimos", R.drawable.deimos, "Orbits Mars"));
                items.add(new Item("Europa", R.drawable.europa, "Orbits Jupiter"));
                items.add(new Item("Our Moon", R.drawable.moon, "Orbits Earth"));
                items.add(new Item("Phobos", R.drawable.phobos, "Orbits Mars"));
            } else if (sort_chooser == 3) {
                //Z - A
                items.add(new Item("Phobos", R.drawable.phobos, "Orbits Mars"));
                items.add(new Item("Our Moon", R.drawable.moon, "Orbits Earth"));
                items.add(new Item("Europa", R.drawable.europa, "Orbits Jupiter"));
                items.add(new Item("Deimos", R.drawable.deimos, "Orbits Mars"));
            } else if (sort_chooser == 4) {
                //Smallest to Largest
                items.add(new Item("Deimos", R.drawable.deimos, "Orbits Mars"));
                items.add(new Item("Phobos", R.drawable.phobos, "Orbits Mars"));
                items.add(new Item("Europa", R.drawable.europa, "Orbits Jupiter"));
                items.add(new Item("Our Moon", R.drawable.moon, "Orbits Earth"));
            } else if (sort_chooser == 5) {
                //Largest to Smallest
                items.add(new Item("Our Moon", R.drawable.moon, "Orbits Earth"));
                items.add(new Item("Europa", R.drawable.europa, "Orbits Jupiter"));
                items.add(new Item("Phobos", R.drawable.phobos, "Orbits Mars"));
                items.add(new Item("Deimos", R.drawable.deimos, "Orbits Mars"));
            } else {
                //Traditional
                items.add(new Item("Our Moon", R.drawable.moon, "Orbits Earth"));
                items.add(new Item("Phobos", R.drawable.phobos, "Orbits Mars"));
                items.add(new Item("Deimos", R.drawable.deimos, "Orbits Mars"));
                items.add(new Item("Europa", R.drawable.europa, "Orbits Jupiter"));
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture;
            TextView name;
            TextView planet;
            View textContainer;

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_item_moons, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.planet, v.findViewById(R.id.planet));
                v.setTag(R.id.textContainer, v.findViewById(R.id.textContainer));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            name = (TextView) v.getTag(R.id.text);
            planet = (TextView) v.getTag(R.id.planet);
            textContainer = (View) v.getTag(R.id.textContainer);

            Item item = (Item) getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);
            planet.setText(item.planet);

            Palette.Builder builder = new Palette.Builder(BitmapFactory.decodeResource(getResources(), item.drawableId));
            builder.generate(new PaletteListener(name, planet, textContainer));
            return v;
        }

        private class PaletteListener implements Palette.PaletteAsyncListener {

            private TextView name;
            private TextView planet;
            private View textContainer;

            private PaletteListener(TextView name, TextView planet, View textContainer) {
                this.name = name;
                this.planet = planet;
                this.textContainer = textContainer;
            }

            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                if (swatch == null) {
                    swatch = palette.getDarkMutedSwatch();
                }
                if (swatch != null) {
                    int bodyTextColor = swatch.getBodyTextColor();
                    int rgb = swatch.getRgb();
                    planet.setTextColor(bodyTextColor);
                    name.setTextColor(bodyTextColor);

                    textContainer.setBackgroundColor(rgb);
                    textContainer.getBackground().setAlpha(127);
                }
            }
        }

        private class Item {
            final String name;
            final int drawableId;
            final String planet;

            Item(String name, int drawableId, String planetName) {
                this.name = name;
                this.drawableId = drawableId;
                this.planet = planetName;
            }
        }
    }

}

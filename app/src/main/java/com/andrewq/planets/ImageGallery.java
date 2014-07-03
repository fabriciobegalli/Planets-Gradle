package com.andrewq.planets;

/**
 * Created by Andrew Quebe on 5/10/2014.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class ImageGallery extends Fragment {

    private ActionBar mActionBar;

    private FrameLayout galleryView;

    public ImageGallery() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        galleryView = (FrameLayout) getView().findViewById(R.id.gallery_main);

        galleryView.setClipToPadding(false);
        setInsets(getActivity(), galleryView);

        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

        GridView gridView = (GridView) getView().findViewById(R.id.gridview);
        gridView.setAdapter(new MyAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i1 = new Intent(getActivity().getApplicationContext(),
                                SunImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i1, scaleBundle);
                        break;
                    case 1:
                        Intent i2 = new Intent(getActivity().getApplicationContext(),
                                MercuryImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i2, scaleBundle2);
                        break;
                    case 2:
                        Intent i3 = new Intent(getActivity().getApplicationContext(),
                                VenusImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i3, scaleBundle3);
                        break;
                    case 3:
                        Intent i4 = new Intent(getActivity().getApplicationContext(),
                                EarthImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i4, scaleBundle4);
                        break;
                    case 4:
                        Intent i5 = new Intent(getActivity().getApplicationContext(),
                                MarsImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i5, scaleBundle5);
                        break;
                    case 5:
                        Intent i6 = new Intent(getActivity().getApplicationContext(),
                                JupiterImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle6 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i6, scaleBundle6);
                        break;
                    case 6:
                        Intent i7 = new Intent(getActivity().getApplicationContext(),
                                SaturnImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle7 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i7, scaleBundle7);
                        break;
                    case 7:
                        Intent i8 = new Intent(getActivity().getApplicationContext(),
                                UranusImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle8 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i8, scaleBundle8);
                        break;
                    case 8:
                        Intent i9 = new Intent(getActivity().getApplicationContext(),
                                NeptuneImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle9 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i9, scaleBundle9);
                        break;
                    case 9:
                        Intent i10 = new Intent(getActivity().getApplicationContext(),
                                PlutoImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle10 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i10, scaleBundle10);
                        break;
                    case 10:
                        Intent i11 = new Intent(getActivity().getApplicationContext(),
                                HCometImageView.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle11 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        getActivity().startActivity(i11, scaleBundle11);
                        break;
                }
            }
        });
    }

    public void setInsets(Activity context, View transView) {

        SystemBarTintManager tintManager = new SystemBarTintManager(context);

        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        transView.setPadding(0, config.getPixelInsetTop(true), 0, config.getPixelInsetBottom());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                return true;
        }

        return true;
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(context);

            items.add(new Item("The Sun", R.drawable.sun));
            items.add(new Item("Mercury", R.drawable.mercury));
            items.add(new Item("Venus", R.drawable.venus));
            items.add(new Item("Earth", R.drawable.earth));
            items.add(new Item("Mars", R.drawable.mars));
            items.add(new Item("Jupiter", R.drawable.jupiter));
            items.add(new Item("Saturn", R.drawable.saturn));
            items.add(new Item("Uranus", R.drawable.uranus));
            items.add(new Item("Neptune", R.drawable.neptune));
            items.add(new Item("Pluto", R.drawable.pluto));
            items.add(new Item("Halley's Comet", R.drawable.haleys_comet));
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

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            name = (TextView) v.getTag(R.id.text);

            Item item = (Item) getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);

            return v;
        }

        private class Item {
            final String name;
            final int drawableId;

            Item(String name, int drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}

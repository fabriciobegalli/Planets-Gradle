package com.andrewq.planets.tv;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewq.planets.R;
import com.andrewq.planets.other_bodies.PlutoActivity;
import com.andrewq.planets.other_bodies.SunActivity;
import com.andrewq.planets.planets.EarthActivity;
import com.andrewq.planets.planets.JupiterActivity;
import com.andrewq.planets.planets.MarsActivity;
import com.andrewq.planets.planets.MercuryActivity;
import com.andrewq.planets.planets.NeptuneActivity;
import com.andrewq.planets.planets.SaturnActivity;
import com.andrewq.planets.planets.UranusActivity;
import com.andrewq.planets.planets.VenusActivity;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2/1/15.
 */
public class TVActivity extends Activity {

    private TextView tv_title;

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tv_activity);

        tv_title = (TextView) findViewById(R.id.planets_title);
        tv_title.setText("Planets");

        gridView = (GridView) findViewById(R.id.gridview);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i1 = new Intent(getApplicationContext(),
                                SunActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i1, scaleBundle);
                        break;
                    case 1:
                        Intent i2 = new Intent(getApplicationContext(),
                                MercuryActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i2, scaleBundle2);
                        break;
                    case 2:
                        Intent i3 = new Intent(getApplicationContext(),
                                VenusActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i3, scaleBundle3);
                        break;
                    case 3:
                        Intent i4 = new Intent(getApplicationContext(),
                                EarthActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i4, scaleBundle4);
                        break;
                    case 4:
                        Intent i5 = new Intent(getApplicationContext(),
                                MarsActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i5, scaleBundle5);
                        break;
                    case 5:
                        Intent i6 = new Intent(getApplicationContext(),
                                JupiterActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle6 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i6, scaleBundle6);
                        break;
                    case 6:
                        Intent i7 = new Intent(getApplicationContext(),
                                SaturnActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle7 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i7, scaleBundle7);
                        break;
                    case 7:
                        Intent i8 = new Intent(getApplicationContext(),
                                UranusActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle8 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i8, scaleBundle8);
                        break;
                    case 8:
                        Intent i9 = new Intent(getApplicationContext(),
                                NeptuneActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle9 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i9, scaleBundle9);
                        break;
                    case 9:
                        Intent i10 = new Intent(getApplicationContext(),
                                PlutoActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle10 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i10, scaleBundle10);
                        break;
                }
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;


        public MyAdapter() {
            inflater = LayoutInflater.from(getApplicationContext());

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

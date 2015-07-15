package com.andrewq.planets.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
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
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2/27/15.
 */
public class FragmentOtherBodies extends Fragment {

    public static GridView gridView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getActivity().findViewById(R.id.gridview);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i2 = new Intent(getActivity().getApplicationContext(),
                                PlutoActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i2, scaleBundle2);
                        break;
                }

            }
        });
    }


    public FragmentOtherBodies() {
        // Required empty public constructor
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

            items.add(new Item("Pluto", R.drawable.pluto, "Dwarf Planet"));
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

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_item_moons, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.planet, v.findViewById(R.id.planet));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            name = (TextView) v.getTag(R.id.text);
            planet = (TextView) v.getTag(R.id.planet);

            Item item = (Item) getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);
            planet.setText(item.planet);

            return v;
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
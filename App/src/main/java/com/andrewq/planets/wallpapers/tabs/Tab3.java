package com.andrewq.planets.wallpapers.tabs;

/**
 * Created by andrew on 8/1/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewq.planets.R;
import com.andrewq.planets.wallpapers.previews.phone.PhoneMain;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

public class Tab3 extends Fragment {

    static GridView gridView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getActivity().findViewById(R.id.walls_gridview3);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i1 = new Intent(getActivity(), PhoneMain.class);
                        i1.putExtra("id", "image_1");
                        startActivity(i1);
                        break;
                    case 1:
                        Intent i2 = new Intent(getActivity(), PhoneMain.class);
                        i2.putExtra("id", "image_2");
                        startActivity(i2);
                        break;
                    /*case 2:
                        Intent i3 = new Intent(getActivity(), PhoneMain.class);
                        i3.putExtra("id", "image_3");
                        startActivity(i3);
                        break;
                    case 3:
                        Intent i4 = new Intent(getActivity(), PhoneMain.class);
                        i4.putExtra("id", "image_4");
                        startActivity(i4);
                        break;
                    case 4:
                        Intent i5 = new Intent(getActivity(), PhoneMain.class);
                        i5.putExtra("id", "image_5");
                        startActivity(i5);
                        break;
                    case 5:
                        Intent i6 = new Intent(getActivity(), PhoneMain.class);
                        i6.putExtra("id", "image_6");
                        startActivity(i6);
                        break;
                    case 6:
                        Intent i7 = new Intent(getActivity(), PhoneMain.class);
                        i7.putExtra("id", "image_7");
                        startActivity(i7);
                        break;*/
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.walls_tab_3, container, false);
        return v;
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;

        public MyAdapter() {
            inflater = LayoutInflater.from(getActivity().getApplicationContext());

            items.add(new Item("Orbit", R.drawable.david_wall_1));
            items.add(new Item("Rocket", R.drawable.david_wall_2));
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

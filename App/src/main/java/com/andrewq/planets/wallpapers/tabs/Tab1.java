package com.andrewq.planets.wallpapers.tabs;

/**
 * Created by andrew on 8/1/15.
 */

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
import android.widget.Toast;

import com.andrewq.planets.R;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {

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
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        Toast.makeText(getActivity().getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.walls_tab_1, container, false);
        return v;
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;

        public MyAdapter() {
            inflater = LayoutInflater.from(getActivity().getApplicationContext());

            //Traditional
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_1));
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_2));
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_3));
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_4));
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_5));
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_6));
            items.add(new Item("Credit: David Ratkovic", R.drawable.david_wall_7));
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

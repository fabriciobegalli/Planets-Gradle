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
import com.andrewq.planets.wallpapers.viewers.WallpaperViewerAnas;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.ArrayList;
import java.util.List;

public class Tab2 extends Fragment {

    static GridView gridView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getActivity().findViewById(R.id.walls_gridview2);

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);

        gridView.setAdapter(swingBottomInAnimationAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i1 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i1.putExtra("id", "image_1");
                        startActivity(i1);
                        break;
                    case 1:
                        Intent i2 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i2.putExtra("id", "image_2");
                        startActivity(i2);
                        break;
                    case 2:
                        Intent i3 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i3.putExtra("id", "image_3");
                        startActivity(i3);
                        break;
                    case 3:
                        Intent i4 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i4.putExtra("id", "image_4");
                        startActivity(i4);
                        break;
                    case 4:
                        Intent i5 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i5.putExtra("id", "image_5");
                        startActivity(i5);
                        break;
                    case 5:
                        Intent i6 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i6.putExtra("id", "image_6");
                        startActivity(i6);
                        break;
                    case 6:
                        Intent i7 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i7.putExtra("id", "image_7");
                        startActivity(i7);
                        break;
                    case 7:
                        Intent i8 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i8.putExtra("id", "image_8");
                        startActivity(i8);
                        break;
                    case 8:
                        Intent i9 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i9.putExtra("id", "image_9");
                        startActivity(i9);
                        break;
                    case 9:
                        Intent i10 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i10.putExtra("id", "image_10");
                        startActivity(i10);
                        break;
                    case 10:
                        Intent i11 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i11.putExtra("id", "image_11");
                        startActivity(i11);
                        break;
                    case 11:
                        Intent i12 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i12.putExtra("id", "image_12");
                        startActivity(i12);
                        break;
                    case 12:
                        Intent i13 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i13.putExtra("id", "image_13");
                        startActivity(i13);
                        break;
                    case 13:
                        Intent i14 = new Intent(getActivity(), WallpaperViewerAnas.class);
                        i14.putExtra("id", "image_14");
                        startActivity(i14);
                        break;
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.walls_tab_2, container, false);
        return v;
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;

        public MyAdapter() {
            inflater = LayoutInflater.from(getActivity().getApplicationContext());

            items.add(new Item("System", R.drawable.anas_wall_1_small));
            items.add(new Item("Planet (Earth)", R.drawable.anas_wall_2_small));
            items.add(new Item("Comet (Red)", R.drawable.anas_wall_3_small));
            items.add(new Item("Comet (Blue)", R.drawable.anas_wall_4_small));
            items.add(new Item("Comet (Green)", R.drawable.anas_wall_5_small));
            items.add(new Item("Comet (Teal)", R.drawable.anas_wall_6_small));
            items.add(new Item("Comet (Yellow)", R.drawable.anas_wall_7_small));
            items.add(new Item("Comet (Purple)", R.drawable.anas_wall_8_small));
            items.add(new Item("Comet (Pink)", R.drawable.anas_wall_9_small));
            items.add(new Item("Planet (Orange)", R.drawable.anas_wall_10_small));
            items.add(new Item("Planet (Blue)", R.drawable.anas_wall_11_small));
            items.add(new Item("Planet (Pink)", R.drawable.anas_wall_12_small));
            items.add(new Item("Planet (Dark)", R.drawable.anas_wall_13_small));
            items.add(new Item("Planet (Earth)", R.drawable.anas_wall_14_small));
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

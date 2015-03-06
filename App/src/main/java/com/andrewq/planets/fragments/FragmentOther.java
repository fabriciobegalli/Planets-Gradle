package com.andrewq.planets.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrewq.planets.R;
import com.andrewq.planets.misc.Settings;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2/27/15.
 */
public class FragmentOther extends Fragment {

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
                        FeedbackSettings fbs = new FeedbackSettings();
                        fbs.setText("Use this to send feedback, suggestions, and bugs to the developer. " +
                                "All feedback/suggestions are appreciated!");
                        fbs.setYourComments("Your message here...");
                        fbs.setTitle("Feedback Submitter");

                        fbs.setToast("Thanks! Check back later for a reply if applicable.");
                        fbs.setToastDuration(Toast.LENGTH_LONG);

                        fbs.setRadioButtons(true);
                        fbs.setBugLabel("Bug");
                        fbs.setIdeaLabel("Suggestion");
                        fbs.setQuestionLabel("General Feedback");

                        fbs.setOrientation(LinearLayout.VERTICAL);
                        fbs.setGravity(Gravity.LEFT);

                        fbs.setModal(true);

                        FeedbackDialog fdb = new FeedbackDialog(getActivity(), "AF-FD2E2AEF7F0A-27", fbs);
                        fdb.show();
                        break;
                    case 1:
                        Intent i3 = new Intent(getActivity().getApplicationContext(),
                                Settings.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i3, scaleBundle3);
                        break;
                }

            }
        });

    }

    public FragmentOther() {
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

            items.add(new Item("Send Feedback", R.drawable.feedback));
            items.add(new Item("Settings", R.drawable.gear));
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
            picture.setPadding(100, 100, 100, 100);
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

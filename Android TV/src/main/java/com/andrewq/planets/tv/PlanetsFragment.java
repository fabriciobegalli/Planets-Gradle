package com.andrewq.planets.tv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemClickedListener;
import android.support.v17.leanback.widget.OnItemSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class PlanetsFragment extends BrowseFragment {
    private static final String TAG = "PlanetsFragment";

    private static final int NUM_ROWS_SUN = 1;
    private static final int NUM_COLS_SUN = 1;

    private static final int NUM_ROWS_PLANET = 1;
    private static final int NUM_COLS_PLANET = 8;
    private final Handler mHandler = new Handler();
    CardPresenter mCardPresenter;
    private ArrayObjectAdapter mRowsAdapter;
    private Drawable mDefaultBackground;
    private Target mBackgroundTarget;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private URI mBackgroundURI;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onActivityCreated(savedInstanceState);

        prepareBackgroundManager();

        setupUIElements();

        loadRows();

        setupEventListeners();
    }

    private void loadRows() {
        List<Planet> sList = PlanetsList.setupSun();
        List<Planet> pList = PlanetsList.setupPlanets();

        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        mCardPresenter = new CardPresenter();

        int s;
        for (s = 0; s < NUM_ROWS_SUN; s++) {
            if (s != 0) {
                Collections.shuffle(sList);
            }
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(mCardPresenter);
            for (int b = 0; b < NUM_COLS_SUN; b++) {
                listRowAdapter.add(sList.get(b));
            }

            HeaderItem planet = new HeaderItem(s, "Stars", null);
            mRowsAdapter.add(new ListRow(planet, listRowAdapter));
        }

        int i;
        for (i = 0; i < NUM_ROWS_PLANET; i++) {
            if (i != 0) {
                Collections.shuffle(pList);
            }
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(mCardPresenter);
            for (int j = 0; j < NUM_COLS_PLANET; j++) {
                listRowAdapter.add(pList.get(j));
            }

            HeaderItem planet = new HeaderItem(i, "Planets", null);
            mRowsAdapter.add(new ListRow(planet, listRowAdapter));
        }

        HeaderItem gridHeader = new HeaderItem(i, "Other", null);

        GridItemPresenter mGridPresenter = new GridItemPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(mGridPresenter);
        gridRowAdapter.add(getResources().getString(R.string.about));
        gridRowAdapter.add(getResources().getString(R.string.tvFeedback));
        gridRowAdapter.add(getResources().getString(R.string.settings));
        mRowsAdapter.add(new ListRow(gridHeader, gridRowAdapter));

        setAdapter(mRowsAdapter);
    }

    private void prepareBackgroundManager() {

        BackgroundManager backgroundManager = BackgroundManager.getInstance(getActivity());
        backgroundManager.attach(getActivity().getWindow());
        mBackgroundTarget = new PicassoBackgroundManagerTarget(backgroundManager);

        mDefaultBackground = getResources().getDrawable(R.drawable.default_background);

        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(
        // R.drawable.videos_by_google_banner));
        setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent
        // over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        SharedPreferences getPrefs2 = PreferenceManager
                .getDefaultSharedPreferences(getActivity().getBaseContext());

        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));

        if (theme_chooser == 1)
            setBrandColor(getResources().getColor(R.color.fastlane_background_red));
        else if (theme_chooser == 2)
            setBrandColor(getResources().getColor(R.color.fastlane_background_orange));
        else if (theme_chooser == 3)
            setBrandColor(getResources().getColor(R.color.fastlane_background_blue));
        else if (theme_chooser == 4)
            setBrandColor(getResources().getColor(R.color.fastlane_background_green));
        else if (theme_chooser == 5)
            setBrandColor(getResources().getColor(R.color.fastlane_background_purple));
        else if (theme_chooser == 6)
            setBrandColor(getResources().getColor(R.color.fastlane_background_dark));

        // set search icon color
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void setupEventListeners() {
        setOnItemSelectedListener(getDefaultItemSelectedListener());
        setOnItemClickedListener(getDefaultItemClickedListener());
        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Coming soon!", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    protected OnItemSelectedListener getDefaultItemSelectedListener() {
        return new OnItemSelectedListener() {
            @Override
            public void onItemSelected(Object item, Row row) {
                if (item instanceof Planet) {
                    mBackgroundURI = ((Planet) item).getBackgroundImageURI();
                    startBackgroundTimer();
                }
            }
        };
    }

    protected OnItemClickedListener getDefaultItemClickedListener() {
        return new OnItemClickedListener() {
            @Override
            public void onItemClicked(Object item, Row row) {
                if (item instanceof Planet) {
                    Planet planet = (Planet) item;
                    Log.d(TAG, "Item: " + item.toString());
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra(getString(R.string.movie), planet);
                    startActivity(intent);
                } else if (item instanceof String) {
                    if (((String) item).contains(getString(R.string.about))) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("About this App")
                                .setMessage("Developed by:\nAndrew Quebe")
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Nothing happens!
                                    }
                                })
                                .show();
                    } else if (((String) item).contains(getString(R.string.tvFeedback))) {
                        FeedbackSettings fbs = new FeedbackSettings();

                        fbs.setText("Use this to send feedback, suggestions, and bugs to the developer.\n\n" +
                                "All feedback/suggestions is/are appreciated!");
                        fbs.setYourComments("Enter your message here...");

                        fbs.setToast("Thank you!");
                        fbs.setToastDuration(Toast.LENGTH_LONG);

                        fbs.setRadioButtons(true);
                        fbs.setBugLabel("Bug");
                        fbs.setIdeaLabel("Suggestion");
                        fbs.setQuestionLabel("General Feedback");

                        fbs.setOrientation(LinearLayout.VERTICAL);
                        fbs.setGravity(Gravity.LEFT);

                        fbs.setModal(true);

                        FeedbackDialog fdb = new FeedbackDialog(getActivity(), "AF-B8381B12F22D-0D", fbs);
                        fdb.show();
                    } else if (((String) item).contains(getString(R.string.settings))) {
                        Intent i = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                        startActivity(i);
                    }
                }
            }
        };
    }

    protected void setDefaultBackground(Drawable background) {
        mDefaultBackground = background;
    }

    protected void setDefaultBackground(int resourceId) {
        mDefaultBackground = getResources().getDrawable(resourceId);
    }

    protected void updateBackground(URI uri) {
        Picasso.with(getActivity())
                .load(uri.toString())
                .resize(mMetrics.widthPixels, mMetrics.heightPixels)
                .centerCrop()
                .error(mDefaultBackground)
                .into(mBackgroundTarget);
    }

    protected void updateBackground(Drawable drawable) {
        BackgroundManager.getInstance(getActivity()).setDrawable(drawable);
    }

    protected void clearBackground() {
        BackgroundManager.getInstance(getActivity()).setDrawable(mDefaultBackground);
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), 300);
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mBackgroundURI != null) {
                        updateBackground(mBackgroundURI);
                    }
                }
            });

        }
    }

    private class GridItemPresenter extends Presenter {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent) {
            TextView view = new TextView(parent.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.setBackgroundColor(getResources().getColor(R.color.default_background));
            view.setTextColor(Color.WHITE);
            view.setGravity(Gravity.CENTER);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Object item) {
            ((TextView) viewHolder.view).setText((String) item);
        }

        @Override
        public void onUnbindViewHolder(ViewHolder viewHolder) {
        }
    }

}

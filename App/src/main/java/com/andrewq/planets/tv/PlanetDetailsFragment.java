package com.andrewq.planets.tv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.DetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.OnItemClickedListener;
import android.support.v17.leanback.widget.Row;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.andrewq.planets.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class PlanetDetailsFragment extends DetailsFragment {
    private static final String TAG = "VideoDetailsFragment";

    private static final int ACTION_WATCH_TRAILER = 1;
    private static final int ACTION_RENT = 2;
    private static final int ACTION_BUY = 3;

    private static final int DETAIL_THUMB_WIDTH = 274;
    private static final int DETAIL_THUMB_HEIGHT = 274;

    private static final int NUM_COLS = 8;

    private static final String PLANET = "Movie";

    private Planet selectedPlanet;

    private Drawable mDefaultBackground;
    private Target mBackgroundTarget;
    private DisplayMetrics mMetrics;

    public static int dpToPx(int dp, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate DetailsFragment");
        super.onCreate(savedInstanceState);

        BackgroundManager backgroundManager = BackgroundManager.getInstance(getActivity());
        backgroundManager.attach(getActivity().getWindow());
        mBackgroundTarget = new PicassoBackgroundManagerTarget(backgroundManager);

        mDefaultBackground = getResources().getDrawable(R.drawable.default_background);

        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

        selectedPlanet = (Planet) getActivity().getIntent().getSerializableExtra(PLANET);
        new DetailRowBuilderTask().execute(selectedPlanet);

        setOnItemClickedListener(getDefaultItemClickedListener());
        updateBackground(selectedPlanet.getBackgroundImageURI());

    }

    protected OnItemClickedListener getDefaultItemClickedListener() {
        return new OnItemClickedListener() {
            @Override
            public void onItemClicked(Object item, Row row) {
                if (item instanceof Planet) {
                    Planet planet = (Planet) item;
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra(PLANET, planet);
                    startActivity(intent);
                }
            }
        };
    }

    protected void updateBackground(URI uri) {
        Log.d(TAG, "uri" + uri);
        Log.d(TAG, "metrics" + mMetrics.toString());
        Picasso.with(getActivity())
                .load(uri.toString())
                .resize(mMetrics.widthPixels, mMetrics.heightPixels)
                .error(mDefaultBackground)
                .centerCrop()
                .into(mBackgroundTarget);
    }

    private class DetailRowBuilderTask extends AsyncTask<Planet, Integer, DetailsOverviewRow> {
        @Override
        protected DetailsOverviewRow doInBackground(Planet... movies) {
            selectedPlanet = movies[0];

            DetailsOverviewRow row = new DetailsOverviewRow(selectedPlanet);
            /*try {
                Bitmap poster = Picasso.with(getActivity())
                        .load(selectedPlanet.getCardImageUrl())
                        .resize(dpToPx(DETAIL_THUMB_WIDTH, getActivity().getApplicationContext()),
                                dpToPx(DETAIL_THUMB_HEIGHT, getActivity().getApplicationContext()))
                        .centerCrop()
                        .get();
                row.setImageBitmap(getActivity(), poster);
            } catch (IOException e) {
            }*/

            //row.addAction(new Action(ACTION_WATCH_TRAILER, "Read More", null));

            return row;
        }

        @Override
        protected void onPostExecute(DetailsOverviewRow detailRow) {
            ClassPresenterSelector ps = new ClassPresenterSelector();
            DetailsOverviewRowPresenter dorPresenter =
                    new DetailsOverviewRowPresenter(new DetailsDescriptionPresenter());

            SharedPreferences getPrefs2 = PreferenceManager
                    .getDefaultSharedPreferences(getActivity().getBaseContext());

            int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));

            if (theme_chooser == 1)
                dorPresenter.setBackgroundColor(getResources().getColor(R.color.detail_background_red));
            else if (theme_chooser == 2)
                dorPresenter.setBackgroundColor(getResources().getColor(R.color.detail_background_orange));
            else if (theme_chooser == 3)
                dorPresenter.setBackgroundColor(getResources().getColor(R.color.detail_background_blue));
            else if (theme_chooser == 4)
                dorPresenter.setBackgroundColor(getResources().getColor(R.color.detail_background_green));
            else if (theme_chooser == 5)
                dorPresenter.setBackgroundColor(getResources().getColor(R.color.detail_background_purple));
            else if (theme_chooser == 6)
                dorPresenter.setBackgroundColor(getResources().getColor(R.color.detail_background_dark));

            dorPresenter.setStyleLarge(true);
            dorPresenter.setOnActionClickedListener(new OnActionClickedListener() {
                @Override
                public void onActionClicked(Action action) {

                    if (action.getId() == ACTION_WATCH_TRAILER) {

                    } else {
                        Toast.makeText(getActivity(), action.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            ps.addClassPresenter(DetailsOverviewRow.class, dorPresenter);
            ps.addClassPresenter(ListRow.class,
                    new ListRowPresenter());

            ArrayObjectAdapter adapter = new ArrayObjectAdapter(ps);
            adapter.add(detailRow);

            String subcategories[] = {
                    getString(R.string.related_movies)
            };
            List<Planet> list = PlanetsList.planetList;
            Collections.shuffle(list);
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new CardPresenter());

            for (int j = 0; j < NUM_COLS; j++) {
                listRowAdapter.add(list.get(j));
            }

            HeaderItem header = new HeaderItem(0, subcategories[0], null);
            adapter.add(new ListRow(header, listRowAdapter));

            setAdapter(adapter);
        }

    }
}
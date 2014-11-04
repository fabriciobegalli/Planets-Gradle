package com.andrewq.planets.TvActivity;

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;

public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    @Override
    protected void onBindDescription(ViewHolder viewHolder, Object item) {
        Planet planet = (Planet) item;

        if (planet != null) {
            viewHolder.getTitle().setText(planet.getTitle());
            viewHolder.getSubtitle().setText(planet.getStudio());
            viewHolder.getBody().setText(planet.getDescription());
        }
    }
}

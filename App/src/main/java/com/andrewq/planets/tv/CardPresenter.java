package com.andrewq.planets.tv;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.andrewq.planets.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URI;

public class CardPresenter extends Presenter {
    private static final String TAG = "CardPresenter";

    private static Context mContext;
    private static int CARD_WIDTH = 313;
    private static int CARD_HEIGHT = 200;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");
        mContext = parent.getContext();

        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);

        SharedPreferences getPrefs2 = PreferenceManager
                .getDefaultSharedPreferences(mContext);

        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));

        if (theme_chooser == 1)
            cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background_red));
        else if (theme_chooser == 2)
            cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background_orange));
        else if (theme_chooser == 3)
            cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background_blue));
        else if (theme_chooser == 4)
            cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background_green));
        else if (theme_chooser == 5)
            cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background_purple));
        else if (theme_chooser == 6)
            cardView.setBackgroundColor(mContext.getResources().getColor(R.color.fastlane_background_dark));

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Planet planet = (Planet) item;
        ((ViewHolder) viewHolder).setMovie(planet);

        Log.d(TAG, "onBindViewHolder");
        if (planet.getCardImageUrl() != null) {
            ((ViewHolder) viewHolder).mCardView.setTitleText(planet.getTitle());
            ((ViewHolder) viewHolder).mCardView.setContentText(planet.getStudio());
            ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
            //((ViewHolder) viewHolder).mCardView.setBadgeImage(mContext.getResources().getDrawable(
            //        R.drawable.videos_by_google_icon));
            ((ViewHolder) viewHolder).updateCardViewImage(planet.getCardImageURI());
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
    }

    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onViewAttachedToWindow");
    }

    static class ViewHolder extends Presenter.ViewHolder {
        private Planet mPlanet;
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;
        private PicassoImageCardViewTarget mImageCardViewTarget;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
            mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.movie);
        }

        public Planet getMovie() {
            return mPlanet;
        }

        public void setMovie(Planet m) {
            mPlanet = m;
        }

        public ImageCardView getCardView() {
            return mCardView;
        }

        protected void updateCardViewImage(URI uri) {
            Picasso.with(mContext)
                    .load(uri.toString())
                    .resize(CARD_WIDTH, CARD_HEIGHT)
                    .centerCrop()
                    .error(mDefaultCardImage)
                    .into(mImageCardViewTarget);
        }
    }

    public static class PicassoImageCardViewTarget implements Target {
        private ImageCardView mImageCardView;

        public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
            this.mImageCardView = mImageCardView;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
            mImageCardView.setMainImage(bitmapDrawable);
        }

        @Override
        public void onBitmapFailed(Drawable drawable) {
            mImageCardView.setMainImage(drawable);
        }

        @Override
        public void onPrepareLoad(Drawable drawable) {
            // Do nothing, default_background manager has its own transitions
        }
    }

}

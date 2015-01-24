package com.andrewq.planets.planets;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.andrewq.planets.R;
import com.andrewq.planets.image_views.SunImageView;
import com.andrewq.planets.util.NotifyingScrollView;
import com.google.analytics.tracking.android.EasyTracker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("ConstantConditions")
public class SunActivity extends Activity {

    private ImageView imgV;
    private Drawable mActionBarBackgroundDrawable;

    private ImageView img;
    private ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sun_activity);

        mActionBar = getActionBar();
        assert mActionBar != null;
        mActionBar.setCustomView(R.layout.custom_actionbar_the_sun);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(false);

        img = (ImageView) findViewById(R.id.backButtonSettings);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences getPrefs2 = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (theme_chooser == 1) {
            //Red
            mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_red);
        } else if (theme_chooser == 2) {
            //Orange
            mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_orange);
        } else if (theme_chooser == 3) {
            //Blue
            mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_blue);
        } else if (theme_chooser == 4) {
            //Green
            mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_green);
        } else if (theme_chooser == 5) {
            //Purple
            mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_purple);
        } else if (theme_chooser == 6) {
            //Black
            mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_black);
        }

        mActionBarBackgroundDrawable.setAlpha(0);

        getActionBar().setBackgroundDrawable(mActionBarBackgroundDrawable);

        ((NotifyingScrollView) findViewById(R.id.scroll_view_1)).setOnScrollChangedListener(mOnScrollChangedListener);

        imgV = (ImageView) findViewById(R.id.image_header_sun);

        View.OnTouchListener upDownListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgV.setAlpha(0.8f);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imgV.setAlpha(1.0f);
                    Intent i = new Intent(getBaseContext(), SunImageView.class);
                    Bundle scaleBundle = ActivityOptions.makeScaleUpAnimation(
                            v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                    startActivity(i, scaleBundle);
                    return true;
                }

                return false;
            }

        };

        imgV.setOnTouchListener(upDownListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.source_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sendFeedback:
                sendFeedback(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void sendFeedback(Context context)
    //opens an email intent with a screenshot and preloaded email
    {
        context.getApplicationContext();
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(takeTempScreenshot(context));
            /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{context.getResources().getString(R.string.support_email)});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback: The Sun");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
        context.startActivity(Intent.createChooser(emailIntent, context.getResources().getString(R.string.send_feedback)));
        //activity.startActivityForResult(Intent.createChooser(emailIntent, getResources().getString(R.string.send_feedback)));

    } //end sendFeedback()

    public static File takeTempScreenshot(Context context)
    //takes a screenshot and stores it in the app's cache, returns the image
    {
        Activity activity = (Activity) context;
        try {
            File outputDir = context.getExternalCacheDir(); // context being the Activity pointer
            File imageFile = File.createTempFile("pfb_sun", ".jpeg", outputDir);
            // image naming and path  to include sd card  appending name you choose for file
            View v1 = activity.getWindow().getDecorView().getRootView();

            v1.setDrawingCacheEnabled(true);
            // create bitmap screen capture
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            FileOutputStream fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);
            fout.flush();
            fout.close();
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } //end takeScreenshot

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        imgV = (ImageView) findViewById(R.id.image_header_sun);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            imgV.setAlpha(0.8f);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            imgV.setAlpha(1.0f);
        }

        return super.onTouchEvent(event);
    }

    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            final int headerHeight = findViewById(R.id.image_header_sun).getHeight() - getActionBar().getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
}
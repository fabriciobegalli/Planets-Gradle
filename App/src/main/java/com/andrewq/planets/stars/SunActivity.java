package com.andrewq.planets.stars;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andrewq.planets.R;
import com.andrewq.planets.util.BaseActivity;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.google.analytics.tracking.android.EasyTracker;
import com.nineoldandroids.view.ViewHelper;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

@SuppressWarnings("ConstantConditions")
public class SunActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private ImageView imgV;

    private View mImageView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sun_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar_sun);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageView = findViewById(R.id.image);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

        SharedPreferences getPrefs2 = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));

        if (theme_chooser == 1) {
            //Red
            int actionBarColor = Color.parseColor("#D32F2F");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setTaskDescription(new ActivityManager.TaskDescription("The Sun",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));

                Window window = getWindow();
                window.setStatusBarColor(darker(actionBarColor, 0.8f));
            }
        } else if (theme_chooser == 2) {
            //Orange
            int actionBarColor = Color.parseColor("#E64A19");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setTaskDescription(new ActivityManager.TaskDescription("The Sun",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));

                Window window = getWindow();
                window.setStatusBarColor(darker(actionBarColor, 0.8f));
            }
        } else if (theme_chooser == 3) {
            //Blue
            int actionBarColor = Color.parseColor("#1976D2");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setTaskDescription(new ActivityManager.TaskDescription("The Sun",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));

                Window window = getWindow();
                window.setStatusBarColor(darker(actionBarColor, 0.8f));
            }
        } else if (theme_chooser == 4) {
            //Green
            int actionBarColor = Color.parseColor("#388E3C");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setTaskDescription(new ActivityManager.TaskDescription("The Sun",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));

                Window window = getWindow();
                window.setStatusBarColor(darker(actionBarColor, 0.8f));
            }
        } else if (theme_chooser == 5) {
            //Purple
            int actionBarColor = Color.parseColor("#512DA8");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setTaskDescription(new ActivityManager.TaskDescription("The Sun",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));

                Window window = getWindow();
                window.setStatusBarColor(darker(actionBarColor, 0.8f));
            }
        } else if (theme_chooser == 6) {
            //Black
            int actionBarColor = Color.parseColor("#212121");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                this.setTaskDescription(new ActivityManager.TaskDescription("The Sun",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));

                Window window = getWindow();
                window.setStatusBarColor(darker(actionBarColor, 0.8f));
            }
        }

        //imgV = (ImageView) findViewById(R.id.image_header_sun);

        /*View.OnTouchListener upDownListener = new View.OnTouchListener() {

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

        };*/

        //imgV.setOnTouchListener(upDownListener);

    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.primary);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs2 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));

        int color;

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (theme_chooser == 0) {
            //Default
            color = Color.parseColor("#5B6ABF");

            baseColor = color;
        } else if (theme_chooser == 1) {
            //Red
            color = Color.parseColor("#D32F2F");

            baseColor = color;
        } else if (theme_chooser == 2) {
            //Orange
            color = Color.parseColor("#E64A19");

            baseColor = color;
        } else if (theme_chooser == 3) {
            //Blue
            color = Color.parseColor("#1976D2");

            baseColor = color;
        } else if (theme_chooser == 4) {
            //Green
            color = Color.parseColor("#388E3C");

            baseColor = color;
        } else if (theme_chooser == 5) {
            //Purple
            color = Color.parseColor("#512DA8");

            baseColor = color;
        } else if (theme_chooser == 6) {
            //Black
            color = Color.parseColor("#212121");

            baseColor = color;
        } else {
            color = Color.parseColor("#5B6ABF");

            baseColor = color;
        }

        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        // We ask for the bounds if they have been set as they would be most
        // correct, then we check we are  > 0
        final int width = !drawable.getBounds().isEmpty() ?
                drawable.getBounds().width() : drawable.getIntrinsicWidth();

        final int height = !drawable.getBounds().isEmpty() ?
                drawable.getBounds().height() : drawable.getIntrinsicHeight();

        // Now we check we are > 0
        final Bitmap bitmap = Bitmap.createBitmap(width <= 0 ? 1 : width, height <= 0 ? 1 : height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * Returns darker version of specified <code>color</code>.
     */
    public static int darker(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
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

                FeedbackDialog fdb = new FeedbackDialog(this, "AF-FD2E2AEF7F0A-27", fbs);
                fdb.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
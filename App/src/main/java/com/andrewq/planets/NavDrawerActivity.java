package com.andrewq.planets;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.andrewq.planets.compare.activity.CompareObjectsMain;
import com.andrewq.planets.fragments.FragmentMoons;
import com.andrewq.planets.fragments.FragmentOtherBodies;
import com.andrewq.planets.fragments.FragmentPlanets;
import com.andrewq.planets.fragments.FragmentStars;
import com.andrewq.planets.iab.IabHelper;
import com.andrewq.planets.iab.IabResult;
import com.andrewq.planets.iab.Inventory;
import com.andrewq.planets.iab.Purchase;
import com.andrewq.planets.intro.DefaultIntro;
import com.andrewq.planets.misc.Settings;
import com.andrewq.planets.wallpapers.activity.WallpaperMain;
import com.google.analytics.tracking.android.EasyTracker;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

/**
 * Created by andrew on 2/27/15.
 */

@SuppressWarnings("deprecation")
public class NavDrawerActivity extends ActionBarActivity {

    Drawer drawer;
    Toolbar toolbar;

    // Debug tag, for logging
    static final String TAG = "Planets Donations";

    // SKUs for our products: the premium upgrade (non-consumable) and gas (consumable)
    static final String SKU_1_DOLLAR = "donate_1_dollar";
    static final String SKU_5_DOLLARS = "donate_5_dollars";
    static final String SKU_10_DOLLARS = "donate_10_dollars";
    static final String SKU_25_DOLLARS = "donate_25_dollars";
    static final String SKU_50_DOLLARS = "donate_50_dollars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjkBL90CQz/fVfOoKDFHTo4y/AWKOuKHK60Wjz+y8mPIUF2AU3uJ5c3ofu1SoGTY6+lpowpeHruMlVzwQ5fk31vAKGSRApcdWjmPh2w7dYmLldV1MTnxz0UiJydENj1O1Ci7MBJiGYigjS+wXG1kFL1LS1BIVoBiodeC+oh9u+eqKZASyA5b5ZUfK8kBQk4EtswnUSq6q5m+oj1SALK/2Nu3ZtRMPKX54dBhs1DHhOY3o9oI+7kl/pLN9d2tARmjJev06bbXlgfVDBum0ghaRI8JS6BZSgJch8inALx6677pOzCnZ49uW+CugyIGp3fe9cwbQDXIvQ8qSDoDjcmv/iwIDAQAB";

        mHelper = new IabHelper(getBaseContext(), base64EncodedPublicKey);

        mHelper.enableDebugLogging(false);

        //TODO: Fix this!

        /*mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Toast.makeText(getBaseContext(), "Issue with in-app billing!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                Log.d(TAG, "Setup successful. Querying inventory.");
                mHelper.queryInventoryAsync(mGotInventoryListener);
            }
        });*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.listview_header_image)
                .withTranslucentNavigationBar(false)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withIcon(R.drawable.ic_planets)
                                .withName("Planets")
                                .withIdentifier(1)
                                .withTextColor(Color.parseColor("#444444"))
                                .withSelectedTextColor(Color.parseColor("#1976D2"))
                                .withSelectedIcon(R.drawable.ic_planets_blue),

                        new PrimaryDrawerItem()
                                .withIcon(R.drawable.ic_stars)
                                .withName("Stars")
                                .withIdentifier(2)
                                .withTextColor(Color.parseColor("#444444"))
                                .withSelectedTextColor(Color.parseColor("#E64A19"))
                                .withSelectedIcon(R.drawable.ic_stars_orange),

                        new PrimaryDrawerItem()
                                .withIcon(R.drawable.ic_moon)
                                .withName("Moons")
                                .withIdentifier(3)
                                .withTextColor(Color.parseColor("#444444"))
                                .withSelectedTextColor(Color.parseColor("#527A7A"))
                                .withSelectedIcon(R.drawable.ic_moon_tinted),

                        new PrimaryDrawerItem()
                                .withIcon(R.drawable.ic_comet)
                                .withName("Other")
                                .withIdentifier(4)
                                .withTextColor(Color.parseColor("#444444"))
                                .withSelectedTextColor(Color.parseColor("#535353"))
                                .withSelectedIcon(R.drawable.ic_comet_tinted),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem()
                                .withIcon(FontAwesome.Icon.faw_map_marker)
                                .withName("Map")
                                .withIdentifier(5)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false),

                        new PrimaryDrawerItem()
                                .withIcon(R.drawable.ic_compare)
                                .withName("Compare Objects")
                                .withIdentifier(6)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false),

                        new PrimaryDrawerItem()
                                .withIcon(R.drawable.ic_gallery)
                                .withName("Wallpapers")
                                .withIdentifier(7)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false),

                        new DividerDrawerItem(),

                        new SecondaryDrawerItem()
                                .withIcon(R.drawable.ic_donate)
                                .withName("Donate")
                                .withIdentifier(8)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false),

                        new SecondaryDrawerItem()
                                .withIcon(FontAwesome.Icon.faw_github)
                                .withName("Open Source Licenses")
                                .withIdentifier(9)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false),

                        new SecondaryDrawerItem()
                                .withIcon(R.drawable.ic_settings)
                                .withName("Settings")
                                .withIdentifier(10)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false),

                        new SecondaryDrawerItem()
                                .withIcon(R.drawable.ic_feedback)
                                .withName("Send Feedback")
                                .withIdentifier(11)
                                .withTextColor(Color.parseColor("#444444"))
                                .withIconColor(Color.parseColor("#444444"))
                                .withCheckable(false)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        // update the main content by replacing fragments
                        Fragment fragment = new FragmentPlanets();
                        FragmentManager fragmentManager = getFragmentManager();

                        switch (iDrawerItem.getIdentifier()) {
                            case 1:
                                fragment = new FragmentPlanets();
                                break;
                            case 2:
                                fragment = new FragmentStars();
                                break;
                            case 3:
                                fragment = new FragmentMoons();
                                break;
                            case 4:
                                fragment = new FragmentOtherBodies();
                                break;
                            case 5:
                                Toast.makeText(getBaseContext(), "Coming Soon!", Toast.LENGTH_LONG).show();
                                break;
                            case 6:
                                Toast.makeText(getBaseContext(), "Still in beta!", Toast.LENGTH_LONG).show();
                                Intent compare = new Intent(NavDrawerActivity.this, CompareObjectsMain.class);
                                startActivity(compare);
                                break;
                            case 7:
                                Intent wallpaper = new Intent(NavDrawerActivity.this, WallpaperMain.class);
                                startActivity(wallpaper);
                                break;
                            case 8:
                                final Activity act = NavDrawerActivity.this;
                                new MaterialDialog.Builder(NavDrawerActivity.this)
                                        .title("Select Amount")
                                        .theme(Theme.LIGHT)
                                        .items(R.array.donations)
                                        .itemsCallback(new MaterialDialog.ListCallback() {
                                            @Override
                                            public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                                                String payload = "";
                                                if (i == 0) {
                                                    mHelper.launchPurchaseFlow(act, SKU_1_DOLLAR, RC_REQUEST, mPurchaseFinishedListener, payload);
                                                } else if (i == 1) {
                                                    mHelper.launchPurchaseFlow(act, SKU_5_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                                } else if (i == 2) {
                                                    mHelper.launchPurchaseFlow(act, SKU_10_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                                } else if (i == 3) {
                                                    mHelper.launchPurchaseFlow(act, SKU_25_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                                } else {
                                                    mHelper.launchPurchaseFlow(act, SKU_50_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                                }
                                            }
                                        })
                                        .neutralText("Cancel")
                                        .callback(new MaterialDialog.ButtonCallback() {
                                            @Override
                                            public void onNeutral(MaterialDialog dialog) {
                                                super.onNeutral(dialog);
                                                dialog.dismiss();
                                            }
                                        })
                                        .autoDismiss(false)
                                        .show();
                                return false;
                            case 9:
                                new LibsBuilder()
                                        .withFields(R.string.class.getFields())
                                        .withActivityTitle("Sources")
                                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                                        .start(NavDrawerActivity.this);
                                return false;
                            case 10:
                                Intent intent = new Intent(getApplicationContext(), Settings.class);
                                startActivity(intent);
                                return false;
                            case 11:
                                FeedbackSettings fbs = new FeedbackSettings();
                                fbs.setText("Use this to send feedback, suggestions, and bugs to the developer. " +
                                        "All feedback/suggestions are appreciated!");
                                fbs.setYourComments("Your message here...");
                                fbs.setTitle("Feedback Submitter");

                                fbs.setToast("Thanks!");
                                fbs.setToastDuration(Toast.LENGTH_LONG);

                                fbs.setRadioButtons(true);
                                fbs.setBugLabel("Bug");
                                fbs.setIdeaLabel("Suggestion");
                                fbs.setQuestionLabel("General Feedback");

                                fbs.setOrientation(LinearLayout.VERTICAL);
                                fbs.setGravity(Gravity.START);

                                fbs.setModal(true);

                                FeedbackDialog fdb = new FeedbackDialog(NavDrawerActivity.this, "AF-FD2E2AEF7F0A-27", fbs);
                                fdb.show();
                                return false;

                        }
                        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                        if (iDrawerItem instanceof Nameable) {
                            setTitle(((Nameable) iDrawerItem).getName());
                        }

                        return false;
                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();

        // set a custom shadow that overlays the main content when the drawer opens
        drawer.getDrawerLayout().setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("key", true);

                if (isFirstStart) {
                    drawer.openDrawer();
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("key", false);
                    e.apply();

                    Intent i = new Intent(NavDrawerActivity.this, DefaultIntro.class);
                    startActivity(i);
                }
            }
        });

        t.start();
    }

    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 10001;
    // The helper object
    IabHelper mHelper;
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            if (mHelper == null) return;

            if (result.isFailure()) {
                Toast.makeText(getBaseContext(), "Error purchasing: " + result, Toast.LENGTH_LONG).show();
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                Toast.makeText(getBaseContext(), "Error purchasing. Authenticity verification failed.", Toast.LENGTH_LONG).show();
                return;
            }

            Log.d(TAG, "Purchase successful.");

            switch (purchase.getSku()) {
                case SKU_1_DOLLAR:
                    Toast.makeText(getBaseContext(), "Thank you for your donation of $1!", Toast.LENGTH_LONG).show();
                    break;
                case SKU_5_DOLLARS:
                    Toast.makeText(getBaseContext(), "Thank you for your donation of $5!", Toast.LENGTH_LONG).show();
                    break;
                case SKU_10_DOLLARS:
                    Toast.makeText(getBaseContext(), "Thank you for your donation of $10!", Toast.LENGTH_LONG).show();
                    break;
                case SKU_25_DOLLARS:
                    Toast.makeText(getBaseContext(), "Thank you for your donation of $25!", Toast.LENGTH_LONG).show();
                    break;
                case SKU_50_DOLLARS:
                    Toast.makeText(getBaseContext(), "Thank you for your donation of $50!", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener;

    {
        mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

                if (mHelper == null) return;

                if (result.isFailure()) {
                    Toast.makeText(getBaseContext(), "Failed to query items", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener;

    {
        mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
            public void onConsumeFinished(Purchase purchase, IabResult result) {
                Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

                if (mHelper == null) return;

                Log.d(TAG, "End consumption flow.");
            }
        };
    }

    /**
     * Verifies the developer payload of a purchase.
     */
    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = drawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setTitle(CharSequence title) {
        //noinspection StatementWithEmptyBody
        if (title == "Map" || title == "Compare Objects" || title == "Wallpapers" || title == "Donate" || title == "Send Feedback" || title == "Settings") {
            //Do nothing...
        } else
            //noinspection ConstantConditions
            getSupportActionBar().setTitle(title);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs2 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetColor", "3"));
        //Get an instance of the ActionBar
        ActionBar mActionBar = getSupportActionBar();

        int color;

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (theme_chooser == 1) {
            //Red
            color = Color.parseColor("#D32F2F");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#D32F2F");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 2) {
            //Orange
            color = Color.parseColor("#E64A19");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#E64A19");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 3) {
            //Blue
            color = Color.parseColor("#1976D2");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#1976D2");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 4) {
            //Green
            color = Color.parseColor("#388E3C");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#388E3C");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 5) {
            //Purple
            color = Color.parseColor("#512DA8");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#512DA8");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 6) {
            //Black
            color = Color.parseColor("#212121");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#212121");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else {
            color = Color.parseColor("#E64A19");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Overview color
                int actionBarColor = Color.parseColor("#E64A19");
                NavDrawerActivity.this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        }

        if (mActionBar != null) {
            toolbar.setBackgroundColor(color);
            drawer.setStatusBarColor(darker(color, 0.8f));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.setElevation(8);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        boolean sendUsage = getPrefs.getBoolean("pref_send_usage", false);

        if (sendUsage)
            EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        boolean sendUsage = getPrefs.getBoolean("pref_send_usage", false);

        if (sendUsage)
            EasyTracker.getInstance(this).activityStop(this);  // Add this method.
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
}

package com.andrewq.planets;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrewq.planets.fragments.FragmentMoons;
import com.andrewq.planets.fragments.FragmentPlanets;
import com.andrewq.planets.fragments.FragmentStars;
import com.andrewq.planets.iab.IabHelper;
import com.andrewq.planets.iab.IabResult;
import com.andrewq.planets.iab.Inventory;
import com.andrewq.planets.iab.Purchase;
import com.andrewq.planets.misc.Settings;
import com.google.analytics.tracking.android.EasyTracker;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2/27/15.
 */
public class NavDrawerActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String[] navigationDrawerItems;

    // Debug tag, for logging
    static final String TAG = "Planets Donations";

    // SKUs for our products: the premium upgrade (non-consumable) and gas (consumable)
    static final String SKU_1_DOLLAR = "donate_1_dollar";
    static final String SKU_5_DOLLARS = "donate_5_dollars";
    static final String SKU_10_DOLLARS = "donate_10_dollars";
    static final String SKU_25_DOLLARS = "donate_25_dollars";
    static final String SKU_50_DOLLARS = "donate_50_dollars";

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

            if (purchase.getSku().equals(SKU_1_DOLLAR)) {
                Toast.makeText(getBaseContext(), "Thank you for your donation of $1!", Toast.LENGTH_LONG).show();
            } else if (purchase.getSku().equals(SKU_5_DOLLARS)) {
                Toast.makeText(getBaseContext(), "Thank you for your donation of $5!", Toast.LENGTH_LONG).show();
            } else if (purchase.getSku().equals(SKU_10_DOLLARS)) {
                Toast.makeText(getBaseContext(), "Thank you for your donation of $10!", Toast.LENGTH_LONG).show();
            } else if (purchase.getSku().equals(SKU_25_DOLLARS)) {
                Toast.makeText(getBaseContext(), "Thank you for your donation of $25!", Toast.LENGTH_LONG).show();
            } else if (purchase.getSku().equals(SKU_50_DOLLARS)) {
                Toast.makeText(getBaseContext(), "Thank you for your donation of $50!", Toast.LENGTH_LONG).show();
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
        String payload;
        payload = p.getDeveloperPayload();
        return true;
    }

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationDrawerItems = getResources().getStringArray(R.array.navigation_drawer_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        listView.setAdapter(new NavDrawerAdapter());
        listView.setOnItemClickListener(new DrawerItemClickListener());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("key", true);

                if (isFirstStart) {
                    drawerLayout.openDrawer(Gravity.START);
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("key", false);
                    e.apply();
                }
            }
        });

        t.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final Activity act = this;

        switch (item.getItemId()) {
            case R.id.donate:
                new AlertDialog.Builder(this)
                        .setTitle("Select Amount (Tax Not Listed)")
                        .setItems(R.array.donations, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String payload = "";
                                if (which == 0) {
                                    mHelper.launchPurchaseFlow(act, SKU_1_DOLLAR, RC_REQUEST, mPurchaseFinishedListener, payload);
                                } else if (which == 1) {
                                    mHelper.launchPurchaseFlow(act, SKU_5_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                } else if (which == 2) {
                                    mHelper.launchPurchaseFlow(act, SKU_10_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                } else if (which == 3) {
                                    mHelper.launchPurchaseFlow(act, SKU_25_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                } else {
                                    mHelper.launchPurchaseFlow(act, SKU_50_DOLLARS, RC_REQUEST, mPurchaseFinishedListener, payload);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Do nothing!
                            }
                        })
                        .show();
                break;
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
                fbs.setGravity(Gravity.START);

                fbs.setModal(true);

                FeedbackDialog fdb = new FeedbackDialog(this, "AF-FD2E2AEF7F0A-27", fbs);
                fdb.show();
                break;
            case R.id.settings:
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new FragmentStars();
        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {
            case 0:
                fragment = new FragmentStars();
                break;
            case 1:
                fragment = new FragmentPlanets();
                break;
            case 2:
                fragment = new FragmentMoons();
                break;
        }

        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        listView.setItemChecked(position, true);
        setTitle(navigationDrawerItems[position]);
        drawerLayout.closeDrawer(listView);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs2 = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));
        //Get an instance of the ActionBar
        ActionBar mActionBar = getSupportActionBar();

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (theme_chooser == 1) {
            //Red
            assert mActionBar != null;
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D32F2F")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                mActionBar.setElevation(15);

                //Status bar tinting
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_red));

                //Overview color
                int actionBarColor = Color.parseColor("#D32F2F");
                this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }

        } else if (theme_chooser == 2) {
            //Orange
            assert mActionBar != null;
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E64A19")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int actionBarColor = Color.parseColor("#E64A19");

                //Status bar tinting
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_orange));

                this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 3) {
            //Blue
            assert mActionBar != null;
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1976D2")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int actionBarColor = Color.parseColor("#1976D2");

                //Status bar tinting
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_blue));

                this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 4) {
            //Green
            assert mActionBar != null;
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#388E3C")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int actionBarColor = Color.parseColor("#388E3C");

                //Status bar tinting
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_green));

                this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 5) {
            //Purple
            assert mActionBar != null;
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#512DA8")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int actionBarColor = Color.parseColor("#512DA8");

                //Status bar tinting
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_purple));

                this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
            }
        } else if (theme_chooser == 6) {
            //Black
            assert mActionBar != null;
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#212121")));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int actionBarColor = Color.parseColor("#212121");

                //Status bar tinting
                Window window = this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(this.getResources().getColor(R.color.status_bar_dark));

                this.setTaskDescription(new ActivityManager.TaskDescription("Planets",
                        drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), actionBarColor));
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

    private class NavDrawerAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;


        public NavDrawerAdapter() {
            inflater = LayoutInflater.from(getApplicationContext());

            items.add(new Item(R.drawable.ic_stars, "Stars"));
            items.add(new Item(R.drawable.ic_planets, "Planets"));
            items.add(new Item(R.drawable.ic_moons, "Moons"));
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
                v = inflater.inflate(R.layout.drawer_list_item, viewGroup, false);
                v.setTag(R.id.navDrawerItemIcon, v.findViewById(R.id.navDrawerItemIcon));
                v.setTag(R.id.navDrawerItemName, v.findViewById(R.id.navDrawerItemName));
            }

            picture = (ImageView) v.getTag(R.id.navDrawerItemIcon);
            name = (TextView) v.getTag(R.id.navDrawerItemName);

            Item item = (Item) getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.itemName);

            return v;
        }

        private class Item {
            final int drawableId;
            final String itemName;

            Item(int drawableId, String name) {
                this.itemName = name;
                this.drawableId = drawableId;
            }
        }
    }
}

package com.andrewq.planets;

/**
 * Created by Andrew Quebe on 5/10/2014.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrewq.planets.util.IabHelper;
import com.andrewq.planets.util.IabResult;
import com.andrewq.planets.util.Inventory;
import com.andrewq.planets.util.Purchase;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;

import com.google.analytics.tracking.android.EasyTracker;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.suredigit.inappfeedback.FeedbackDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ActionBar mActionBar;

    public FeedbackDialog fbd;

    private Context con = getBaseContext();

    // Debug tag, for logging
    static final String TAG = "TrivialDrive";

    // Does the user have the premium upgrade?
    boolean mIsPremium = false;

    // Does the user have an active subscription to the infinite gas plan?
    boolean mSubscribedToInfiniteGas = false;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.gallery_main);

        String base64EncodedPublicKey = "<hidden>";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.enableDebugLogging(false);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
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
        });


        GridView gridView = (GridView) findViewById(R.id.gridview);

        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(new MyAdapter(con));

        scaleInAnimationAdapter.setAbsListView(gridView);

        scaleInAnimationAdapter.setInitialDelayMillis(500);
        scaleInAnimationAdapter.setAnimationDurationMillis(300);

        /*SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(new MyAdapter(con), 100, 300);

        swingBottomInAnimationAdapter.setAbsListView(gridView);*/

        gridView.setAdapter(scaleInAnimationAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                switch (position) {
                    case 0:
                        Intent i1 = new Intent(getApplicationContext(),
                                SunActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i1, scaleBundle);
                        break;
                    case 1:
                        Intent i2 = new Intent(getApplicationContext(),
                                MercuryActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle2 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i2, scaleBundle2);
                        break;
                    case 2:
                        Intent i3 = new Intent(getApplicationContext(),
                                VenusActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle3 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i3, scaleBundle3);
                        break;
                    case 3:
                        Intent i4 = new Intent(getApplicationContext(),
                                EarthActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle4 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i4, scaleBundle4);
                        break;
                    case 4:
                        Intent i5 = new Intent(getApplicationContext(),
                                MarsActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle5 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i5, scaleBundle5);
                        break;
                    case 5:
                        Intent i6 = new Intent(getApplicationContext(),
                                JupiterActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle6 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i6, scaleBundle6);
                        break;
                    case 6:
                        Intent i7 = new Intent(getApplicationContext(),
                                SaturnActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle7 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i7, scaleBundle7);
                        break;
                    case 7:
                        Intent i8 = new Intent(getApplicationContext(),
                                UranusActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle8 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i8, scaleBundle8);
                        break;
                    case 8:
                        Intent i9 = new Intent(getApplicationContext(),
                                NeptuneActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle9 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i9, scaleBundle9);
                        break;
                    case 9:
                        Intent i10 = new Intent(getApplicationContext(),
                                PlutoActivity.class);
                        //Scale animation is sent as a bundle to the next activity.
                        Bundle scaleBundle10 = ActivityOptions.makeScaleUpAnimation(
                                v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                        //then start the activity, and send the bundle
                        startActivity(i10, scaleBundle10);
                        break;
                }
            }
        });
    }

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            if (mHelper == null) return;

            if (result.isFailure()) {
                Toast.makeText(getBaseContext(), "Failed to query items", Toast.LENGTH_LONG).show();
                return;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "Destroying helper.");
        if (mHelper != null) {
            mHelper.dispose();
            mHelper = null;
        }
    }

    /**
     * Verifies the developer payload of a purchase.
     */
    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }

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

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            if (mHelper == null) return;

            Log.d(TAG, "End consumption flow.");
        }
    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Set getPrefs to a preference manager
        SharedPreferences getPrefs2 = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        //Give theme_chooser the preference key defined in XML
        int theme_chooser = Integer.parseInt(getPrefs2.getString("prefSetTheme", "3"));
        //Get an instance of the ActionBar
        mActionBar = getActionBar();

        //Set the action bar colors to whatever the user selects from the ListPreference
        if (theme_chooser == 1) {
            //Red
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cc0202")));
        } else if (theme_chooser == 2) {
            //Orange
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8801")));
        } else if (theme_chooser == 3) {
            //Blue
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0497c9")));
        } else if (theme_chooser == 4) {
            //Green
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#679a03")));
        } else if (theme_chooser == 5) {
            //Purple
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9832cb")));
        } else if (theme_chooser == 6) {
            //Black
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#292929")));
        }
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
                fbd = new FeedbackDialog(this, "AF-FD2E2AEF7F0A-27");
                fbd.show();
                break;
            case R.id.settings:
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }

    private class MyAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;


        public MyAdapter(Context context) {
            inflater = LayoutInflater.from(getApplicationContext());

            items.add(new Item("The Sun", R.drawable.sun));
            items.add(new Item("Mercury", R.drawable.mercury));
            items.add(new Item("Venus", R.drawable.venus));
            items.add(new Item("Earth", R.drawable.earth));
            items.add(new Item("Mars", R.drawable.mars));
            items.add(new Item("Jupiter", R.drawable.jupiter));
            items.add(new Item("Saturn", R.drawable.saturn));
            items.add(new Item("Uranus", R.drawable.uranus));
            items.add(new Item("Neptune", R.drawable.neptune));
            items.add(new Item("Pluto", R.drawable.pluto));
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

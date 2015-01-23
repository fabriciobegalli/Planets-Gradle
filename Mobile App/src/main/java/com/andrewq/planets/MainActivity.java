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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrewq.planets.misc.Settings;
import com.andrewq.planets.other_bodies.PlutoActivity;
import com.andrewq.planets.planets.EarthActivity;
import com.andrewq.planets.planets.JupiterActivity;
import com.andrewq.planets.planets.MarsActivity;
import com.andrewq.planets.planets.MercuryActivity;
import com.andrewq.planets.planets.NeptuneActivity;
import com.andrewq.planets.planets.SaturnActivity;
import com.andrewq.planets.planets.SunActivity;
import com.andrewq.planets.planets.UranusActivity;
import com.andrewq.planets.planets.VenusActivity;
import com.andrewq.planets.iab.IabHelper;
import com.andrewq.planets.iab.IabResult;
import com.andrewq.planets.iab.Inventory;
import com.andrewq.planets.iab.Purchase;
import com.google.analytics.tracking.android.EasyTracker;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.suredigit.inappfeedback.FeedbackDialog;
import com.suredigit.inappfeedback.FeedbackSettings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ActionBar mActionBar;

    public FeedbackDialog fbd;

    private Context con = getBaseContext();

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

    private ActionBar ab;

    public static GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        ab = getActionBar();

        ab.setCustomView(R.layout.custom_actionbar_main);
        ab.setDisplayShowCustomEnabled(true);

        setContentView(R.layout.gallery_main);

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjkBL90CQz/fVfOoKDFHTo4y/AWKOuKHK60Wjz+y8mPIUF2AU3uJ5c3ofu1SoGTY6+lpowpeHruMlVzwQ5fk31vAKGSRApcdWjmPh2w7dYmLldV1MTnxz0UiJydENj1O1Ci7MBJiGYigjS+wXG1kFL1LS1BIVoBiodeC+oh9u+eqKZASyA5b5ZUfK8kBQk4EtswnUSq6q5m+oj1SALK/2Nu3ZtRMPKX54dBhs1DHhOY3o9oI+7kl/pLN9d2tARmjJev06bbXlgfVDBum0ghaRI8JS6BZSgJch8inALx6677pOzCnZ49uW+CugyIGp3fe9cwbQDXIvQ8qSDoDjcmv/iwIDAQAB";

        //TODO: Re-enable donation code

        /*mHelper = new IabHelper(this, base64EncodedPublicKey);

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
        });*/


        gridView = (GridView) findViewById(R.id.gridview);

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

        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());

        boolean isChecked = getPrefs.getBoolean("pref_translucent", false);


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
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        boolean sendUsage = getPrefs.getBoolean("pref_send_usage", false);


        //TODO: fix landscape translucent status bar bug

        boolean isChecked = getPrefs.getBoolean("pref_translucent", false);

        if (isChecked) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            gridView = (GridView) findViewById(R.id.gridview);
            gridView.setClipToPadding(false);
            setInsets(this, gridView);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (isChecked) {
            tintManager.setStatusBarTintEnabled(true);
        } else {
            tintManager.setStatusBarTintEnabled(false);
        }

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

            int actionBarColor = Color.parseColor("#cc0202");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 2) {
            //Orange
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8801")));

            int actionBarColor = Color.parseColor("#ff8801");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 3) {
            //Blue
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0497c9")));

            int actionBarColor = Color.parseColor("#0497c9");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 4) {
            //Green
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#679a03")));

            int actionBarColor = Color.parseColor("#679a03");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 5) {
            //Purple
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9832cb")));

            int actionBarColor = Color.parseColor("#9832cb");
            tintManager.setStatusBarTintColor(actionBarColor);
        } else if (theme_chooser == 6) {
            //Black
            mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#292929")));

            int actionBarColor = Color.parseColor("#292929");
            tintManager.setStatusBarTintColor(actionBarColor);
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

                sendFeedback(this);

                /*FeedbackSettings fbs = new FeedbackSettings();
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

                fbd = new FeedbackDialog(this, "AF-FD2E2AEF7F0A-27", fbs);
                fbd.show();*/
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

    public static void sendFeedback(Context context)
    //opens an email intent with a screenshot and preloaded email
    {
        context.getApplicationContext();
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(takeTempScreenshot(context));
            /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{context.getResources().getString(R.string.support_email)});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback: Main Activity");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
        context.startActivity(Intent.createChooser(emailIntent, context.getResources().getString(R.string.send_feedback)));
        //activity.startActivityForResult(Intent.createChooser(emailIntent, getResources().getString(R.string.send_feedback)));

    } //end sendFeedback()

    public static File takeTempScreenshot(Context context)
    //takes a screenshot and stores it in the app's cache, returns the image
    {
        Activity activity = (Activity)context;
        try {
            File outputDir = context.getExternalCacheDir(); // context being the Activity pointer
            File imageFile = File.createTempFile("p_main", ".jpeg", outputDir);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } //end takeScreenshot

    public void setInsets(Activity context, View transView) {

        SystemBarTintManager tintManager = new SystemBarTintManager(context);

        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();

        transView.setPadding(0, config.getPixelInsetTop(true), config.getPixelInsetRight(), config.getPixelInsetBottom());
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

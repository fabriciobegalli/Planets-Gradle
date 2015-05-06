package com.andrewq.planets.misc;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;

import com.andrewq.planets.R;
import com.andrewq.planets.contributers.Contributers;
import com.andrewq.planets.licenses.Licenses;
import com.google.analytics.tracking.android.EasyTracker;

import uk.me.lewisdeane.ldialogs.BaseDialog;
import uk.me.lewisdeane.ldialogs.CustomDialog;

public class Settings extends PreferenceActivity {

    Toolbar toolbar;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setTaskDescription(new ActivityManager.TaskDescription("Settings",
                    drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), Color.parseColor("#414141")));
        }

        Preference prefContribs = findPreference("pref_contribs");

        prefContribs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Intent i = new Intent(getBaseContext(), Contributers.class);
                startActivity(i);
                return true;
            }
        });

        Preference prefLicenses = findPreference("pref_licenses");

        prefLicenses.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Intent i = new Intent(getBaseContext(), Licenses.class);
                startActivity(i);
                return true;
            }
        });

        Preference prefWhatsNew = findPreference("pref_whatsnew");

        prefWhatsNew.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                CustomDialog.Builder builder = new CustomDialog.Builder(Settings.this, "What's New in 2.6.3 BETA 3", "View on Github");

                builder.content("- Fixed \"Send Feedback\" bug");
                builder.negativeText("Close");
                builder.titleAlignment(BaseDialog.Alignment.LEFT);
                builder.positiveColor("#0497c9");

                CustomDialog customDialog = builder.build();
                customDialog.setClickListener(new CustomDialog.ClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String url = "https://github.com/AMQTech/Planets-Gradle";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }

                    @Override
                    public void onCancelClick() {
                        //Do nothing!
                    }
                });
                customDialog.show();
                return true;
            }
        });
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
}

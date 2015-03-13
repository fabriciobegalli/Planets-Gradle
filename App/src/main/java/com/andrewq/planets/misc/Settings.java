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
import android.view.View;
import android.widget.ImageButton;

import com.andrewq.planets.R;
import com.google.analytics.tracking.android.EasyTracker;

import uk.me.lewisdeane.ldialogs.BaseDialog;
import uk.me.lewisdeane.ldialogs.CustomDialog;

public class Settings extends PreferenceActivity {

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

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setContentView(R.layout.custom_preferences);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setTaskDescription(new ActivityManager.TaskDescription("Settings",
                    drawableToBitmap(getResources().getDrawable(R.drawable.ic_launcher)), Color.parseColor("#414141")));
        }

        Preference prefSystemBarTint = findPreference("pref_listlicense");

        prefSystemBarTint.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                CustomDialog.Builder builder = new CustomDialog.Builder(Settings.this, "License", "View on Github");

                builder.content("Copyright 2015 Niek Haarman\n" +
                        "\n" +
                        "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                        "you may not use this file except in compliance with the License.");
                builder.negativeText("Close");
                builder.titleAlignment(BaseDialog.Alignment.LEFT);
                builder.positiveColor("#0497c9");

                CustomDialog customDialog = builder.build();
                customDialog.setClickListener(new CustomDialog.ClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String url = "https://github.com/nhaarman/ListViewAnimations";
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

        Preference prefLDialogs = findPreference("pref_ldialogs");

        prefLDialogs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                CustomDialog.Builder builder = new CustomDialog.Builder(Settings.this, "License", "View on Github");

                builder.content("The author of this library has not released an official license file. " +
                        "However, you can still view the source code on Github.");
                builder.negativeText("Close");
                builder.titleAlignment(BaseDialog.Alignment.LEFT);
                builder.positiveColor("#0497c9");

                CustomDialog customDialog = builder.build();
                customDialog.setClickListener(new CustomDialog.ClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String url = "https://github.com/lewisjdeane/L-Dialogs";
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

        Preference prefAnalytics = findPreference("pref_analytics");

        prefAnalytics.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                CustomDialog.Builder builder = new CustomDialog.Builder(Settings.this, "License", "Visit Website");

                builder.content("Google Analytics Android SDK version 3.02" +
                        "\n" +
                        "Copyright 2009-2015 Google, Inc. All rights reserved." +
                        "\n" + "Licensed under the Apache License, Version 2.0 (the \"License\"); " +
                        "you may not use this file except in compliance with the License.");
                builder.negativeText("Close");
                builder.titleAlignment(BaseDialog.Alignment.LEFT);
                builder.positiveColor("#0497c9");

                CustomDialog customDialog = builder.build();
                customDialog.setClickListener(new CustomDialog.ClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String url = "https://developers.google.com/analytics/devguides/collection/android/resources";
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

        Preference prefWhatsNew = findPreference("pref_whatsnew");

        prefWhatsNew.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                CustomDialog.Builder builder = new CustomDialog.Builder(Settings.this, "What's New in 2.6.1", "View on Github");

                builder.content("- New \"Other\" Category\n " +
                        "- TV Updates");
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

        ImageButton share = (ImageButton) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Learn about the solar system around you!\nhttp://goo.gl/avvK0R";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share To:"));

            }
        });

        ImageButton rate = (ImageButton) findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.andrewq.planets")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.andrewq.planets")));
                }

            }
        });

        ImageButton apps = (ImageButton) findViewById(R.id.apps);
        apps.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=AMQTech")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=AMQTech")));
                }

            }
        });


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

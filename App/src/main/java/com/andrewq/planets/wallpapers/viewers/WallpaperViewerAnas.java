package com.andrewq.planets.wallpapers.viewers;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andrewq.planets.R;

import java.io.IOException;

/**
 * Created by andrew on 8/2/15.
 */
public class WallpaperViewerAnas extends ActionBarActivity {

    private ImageView img;

    public String receivedID;

    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        setContentView(R.layout.activity_view_wallpapers);

        tb = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(tb);

        final SharedPreferences getPrefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());
        boolean isFirstStart = getPrefs.getBoolean("wall_key", true);

        if (isFirstStart) {
            Toast.makeText(getBaseContext(), "Long press to set as wallpaper.", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor e = getPrefs.edit();
            e.putBoolean("wall_key", false);
            e.apply();
        }

        Intent intent = getIntent();
        receivedID = intent.getExtras().getString("id");

        img = (ImageView) findViewById(R.id.viewer_imgview);

        final Bitmap b;

        switch (checkSentID()) {
            case 1:
                Drawable image1 = getResources().getDrawable(R.drawable.anas_wall_1);
                img.setImageDrawable(image1);

                img.setDrawingCacheEnabled(true);

                // this is the important code :)
                // Without it the view will have a dimension of 0,0 and the bitmap will be null
                img.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                img.layout(0, 0, img.getMeasuredWidth(), img.getMeasuredHeight());

                img.buildDrawingCache(true);
                b = Bitmap.createBitmap(img.getDrawingCache());
                img.setDrawingCacheEnabled(false); // clear drawing cache

                img.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        try {
                            WallpaperManager.getInstance(getBaseContext()).setBitmap(b);
                            Toast.makeText(getBaseContext(), "Done!", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                        }

                        return true;
                    }
                });
                break;
            case 2:
                Drawable image2 = getResources().getDrawable(R.drawable.anas_wall_2);
                img.setImageDrawable(image2);

                img.setDrawingCacheEnabled(true);

                // this is the important code :)
                // Without it the view will have a dimension of 0,0 and the bitmap will be null
                img.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                img.layout(0, 0, img.getMeasuredWidth(), img.getMeasuredHeight());

                img.buildDrawingCache(true);
                b = Bitmap.createBitmap(img.getDrawingCache());
                img.setDrawingCacheEnabled(false); // clear drawing cache

                img.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        try {
                            WallpaperManager.getInstance(getBaseContext()).setBitmap(b);
                            Toast.makeText(getBaseContext(), "Done!", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                        }

                        return true;
                    }
                });
                break;
        }
    }

    private int checkSentID() {
        if (receivedID.equals("image_1"))
            return 1;
        else if (receivedID.equals("image_2"))
            return 2;
        else return 1;
    }
}

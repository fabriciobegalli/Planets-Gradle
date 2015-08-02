package com.andrewq.planets.wallpapers.previews.phone;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.andrewq.planets.R;

import java.io.IOException;

/**
 * Created by andrew on 8/2/15.
 */
public class PhoneMain extends Activity {

    private ImageView img;

    public String receivedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_phone_wallpapers);

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

        img = (ImageView) findViewById(R.id.phone_imgview);

        final Bitmap b;

        switch (checkSentID()) {
            case 1:
                Drawable image1 = getResources().getDrawable(R.drawable.david_wall_1);
                img.setBackground(image1);

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
                Drawable image2 = getResources().getDrawable(R.drawable.david_wall_2);
                img.setBackground(image2);

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
            case 3:
                Drawable image3 = getResources().getDrawable(R.drawable.david_wall_3);
                img.setBackground(image3);

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
            case 4:
                Drawable image4 = getResources().getDrawable(R.drawable.david_wall_4);
                img.setBackground(image4);

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
            case 5:
                Drawable image5 = getResources().getDrawable(R.drawable.david_wall_5);
                img.setBackground(image5);

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
            case 6:
                Drawable image6 = getResources().getDrawable(R.drawable.david_wall_6);
                img.setBackground(image6);

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
            case 7:
                Drawable image7 = getResources().getDrawable(R.drawable.david_wall_7);
                img.setBackground(image7);

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
        else if (receivedID.equals("image_3"))
            return 3;
        else if (receivedID.equals("image_4"))
            return 4;
        else if (receivedID.equals("image_5"))
            return 5;
        else if (receivedID.equals("image_6"))
            return 6;
        else if (receivedID.equals("image_7"))
            return 7;
        else return 1;
    }
}

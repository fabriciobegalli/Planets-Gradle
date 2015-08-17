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
public class WallpaperViewerAdrien extends ActionBarActivity {

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
            case 3:
                Drawable image3 = getResources().getDrawable(R.drawable.anas_wall_3);
                img.setImageDrawable(image3);

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
                Drawable image4 = getResources().getDrawable(R.drawable.anas_wall_4);
                img.setImageDrawable(image4);

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
                Drawable image5 = getResources().getDrawable(R.drawable.anas_wall_5);
                img.setImageDrawable(image5);

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
                Drawable image6 = getResources().getDrawable(R.drawable.anas_wall_6);
                img.setImageDrawable(image6);

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
                Drawable image7 = getResources().getDrawable(R.drawable.anas_wall_7);
                img.setImageDrawable(image7);

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
            case 8:
                Drawable image8 = getResources().getDrawable(R.drawable.anas_wall_8);
                img.setImageDrawable(image8);

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
            case 9:
                Drawable image9 = getResources().getDrawable(R.drawable.anas_wall_9);
                img.setImageDrawable(image9);

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
            case 10:
                Drawable image10 = getResources().getDrawable(R.drawable.anas_wall_10);
                img.setImageDrawable(image10);

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
            case 11:
                Drawable image11 = getResources().getDrawable(R.drawable.anas_wall_11);
                img.setImageDrawable(image11);

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
            case 12:
                Drawable image12 = getResources().getDrawable(R.drawable.anas_wall_12);
                img.setImageDrawable(image12);

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
            case 13:
                Drawable image13 = getResources().getDrawable(R.drawable.anas_wall_13);
                img.setImageDrawable(image13);

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
            case 14:
                Drawable image14 = getResources().getDrawable(R.drawable.anas_wall_14);
                img.setImageDrawable(image14);

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
        else if (receivedID.equals("image_8"))
            return 8;
        else if (receivedID.equals("image_9"))
            return 9;
        else if (receivedID.equals("image_10"))
            return 10;
        else if (receivedID.equals("image_11"))
            return 11;
        else if (receivedID.equals("image_12"))
            return 12;
        else if (receivedID.equals("image_13"))
            return 13;
        else if (receivedID.equals("image_14"))
            return 14;
        else return 1;
    }
}

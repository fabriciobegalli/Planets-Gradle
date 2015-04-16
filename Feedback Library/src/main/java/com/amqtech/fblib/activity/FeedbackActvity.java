package com.amqtech.fblib.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.amqtech.fblib.R;

/**
 * Created by andrew on 4/14/15.
 */
public class FeedbackActvity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);

        Toolbar tb = (Toolbar) findViewById(R.id.fb_toolbar);
        setSupportActionBar(tb);
    }
}

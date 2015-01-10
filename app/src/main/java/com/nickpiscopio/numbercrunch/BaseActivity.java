package com.nickpiscopio.numbercrunch;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

/**
 * The parent class for the activities with Action Bars.
 *
 * Created by Nick Piscopio on January 6, 2015.
 */
public class BaseActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:

                finish();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
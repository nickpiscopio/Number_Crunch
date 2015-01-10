package com.nickpiscopio.numbercrunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * The main activity for Number Crunch.
 *
 * Created by Nick Piscopio on January 6, 2015.
 */
public class MainActivity extends ActionBarActivity
{
    private final int ID_PLAY = R.id.button_play;
    private final int ID_HIGH_SCORE = R.id.button_high_scores;

    private Button btnPlay;
    private Button btnHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.button_play);
        btnHighScore = (Button) findViewById(R.id.button_high_scores);

        btnPlay.setOnClickListener(buttonListener);
        btnHighScore.setOnClickListener(buttonListener);
    }

    /**
     * The listener for the buttons.
     */
    private View.OnClickListener buttonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case ID_PLAY:

                    startActivity(NumberCrunchActivity.class);

                    break;

                case ID_HIGH_SCORE:

                    startActivity(HighScoreActivity.class);

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_about)
        {
            startActivity(AboutActivity.class);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts a designated activity.
     *
     * @param destination   The class to start.
     */
    private void startActivity(Class destination)
    {
        Intent intent = new Intent(MainActivity.this, destination);
        startActivity(intent);
    }
}
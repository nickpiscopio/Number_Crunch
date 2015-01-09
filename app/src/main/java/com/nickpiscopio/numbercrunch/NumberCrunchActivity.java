package com.nickpiscopio.numbercrunch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by nickpiscopio on 1/6/15.
 */
public class NumberCrunchActivity extends ActionBarActivity
{
    private final int ANIMATION_DURATION = 500;
    private final int HIDE_ANSWER_DURATION = 3000;

    private LinearLayout target;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_crunch);

        target = (LinearLayout)findViewById(R.id.target);
        target.setOnTouchListener(targetListener);
    }

    private View.OnTouchListener targetListener = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(final View v, MotionEvent event)
        {
            int action = event.getAction();

            switch (action)
            {
                case MotionEvent.ACTION_DOWN:

                    final int distanceToMove = target.getWidth() - (target.getWidth() / 4);

                    animateView(v, 0, distanceToMove);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            animateView(v, distanceToMove, 0);
                        }
                    }, HIDE_ANSWER_DURATION + ANIMATION_DURATION);

                    break;
                default:
                    break;
            }

            return true;
        }
    };

    /**
     * Animates a view to a specified target.
     *
     * @param view      The view to animate.
     * @param start     The start position of the view.
     * @param end       The end position of the view.
     */
    private void animateView(View view, int start, int end)
    {
        TranslateAnimation animation = new TranslateAnimation(start, end, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(ANIMATION_DURATION);

        view.startAnimation(animation);
    }
}
package com.nickpiscopio.numbercrunch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * The Number Crunch activity.
 *
 * Created by Nick Piscopio on January 6, 2015.
 */
public class NumberCrunchActivity extends ActionBarActivity
{
    private final int ANIMATION_DURATION = 500;
    private final int HIDE_ANSWER_DURATION = 3000;
    private final int NUMBERS_TO_GENERATE = 4;

    private AnswerSheet answerSheet;

    private Button number1;
    private Button number2;
    private Button number3;
    private Button number4;

    private LinearLayout target;

    private TextView textView_target;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_crunch);

        number1 = (Button) findViewById(R.id.number_1);
        number2 = (Button) findViewById(R.id.number_2);
        number3 = (Button) findViewById(R.id.number_3);
        number4 = (Button) findViewById(R.id.number_4);

        target = (LinearLayout)findViewById(R.id.target);
        target.setOnTouchListener(targetListener);

        textView_target = (TextView) findViewById(R.id.textView_target);

        generateNewGame();
    }

    /**
     * The touch listener for the target layout.
     */
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
     * Generates a new game for the user.
     */
    private void generateNewGame()
    {
        answerSheet = Arithmetic.generateAnswerSheet(NUMBERS_TO_GENERATE);

        number1.setText(String.valueOf(answerSheet.getNumbers()[0]));
        number2.setText(String.valueOf(answerSheet.getNumbers()[1]));
        number3.setText(String.valueOf(answerSheet.getNumbers()[2]));
        number4.setText(String.valueOf(answerSheet.getNumbers()[3]));

        textView_target.setText(String.valueOf(answerSheet.getAnswer()));
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_number_crunch, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_skip)
        {
            generateNewGame();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.nickpiscopio.numbercrunch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
    private final float SNAP_DISTANCE = 50;

    private AnswerSheet answerSheet;

    private TextView numberTarget1;
    private TextView numberTarget2;
    private TextView numberTarget3;
    private TextView numberTarget4;

    private Button number1;
    private Button number2;
    private Button number3;
    private Button number4;

    private LinearLayout target;

    private TextView textView_target;

    private ArrayList<Button> numbers;

    private float target1;
    private float target2;
    private float target3;
    private float target4;

    private float prevX;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_crunch);

        numberTarget1 = (TextView) findViewById(R.id.target_number_1);
        numberTarget2 = (TextView) findViewById(R.id.target_number_2);
        numberTarget3 = (TextView) findViewById(R.id.target_number_3);
        numberTarget4 = (TextView) findViewById(R.id.target_number_4);

        number1 = (Button) findViewById(R.id.number_1);
        number2 = (Button) findViewById(R.id.number_2);
        number3 = (Button) findViewById(R.id.number_3);
        number4 = (Button) findViewById(R.id.number_4);

        number1.setOnTouchListener(numberListener);
        number2.setOnTouchListener(numberListener);
        number3.setOnTouchListener(numberListener);
        number4.setOnTouchListener(numberListener);

        target = (LinearLayout)findViewById(R.id.target);
        target.setOnTouchListener(touchListener);

        textView_target = (TextView) findViewById(R.id.textView_target);

        numbers = new ArrayList<Button>();
        numbers.add(number1);
        numbers.add(number2);
        numbers.add(number3);
        numbers.add(number4);

        generateNewGame();
    }

    /**
     * The touch listener for the target layout.
     */
    private View.OnTouchListener touchListener = new View.OnTouchListener()
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

    private View.OnTouchListener numberListener = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(final View v,final MotionEvent event)
        {
            int width = v.getWidth();

            int index = numbers.indexOf(v);

            target1 = numberTarget1.getX();
            target2 = numberTarget2.getX();
            target3 = numberTarget3.getX();
            target4 = numberTarget4.getX();

            switch(event.getAction())
            {
                case MotionEvent.ACTION_MOVE:

                    float pointerX = event.getRawX() - ((width / 2) + (width / 4));

                    if ((pointerX - target1) < SNAP_DISTANCE)
                    {


                        int buttonIndex = 0;

//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);
                        number.setX(prevX);
                        v.setX(target1);


                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

//                        Log.i("prevX: ", String.valueOf(prevX));

//                        Log.i("numbers: ", numbers.toString());

//                        numbers.remove(index);
//                        numbers.add(0, (Button)v);
                    }
                    else if ((pointerX - target2) < SNAP_DISTANCE)
                    {


                        int buttonIndex = 1;

//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);
                        number.setX(prevX);
                        v.setX(target2);


                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

//                        numbers.remove(index);
//                        numbers.add(1, (Button)v);
                    }
                    else if ((pointerX - target3) < SNAP_DISTANCE)
                    {


                        int buttonIndex = 2;

//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);

                        number.setX(prevX);
                        v.setX(target3);

                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

//                        numbers.remove(index);
//                        numbers.add(2, (Button)v);
                    }
                    else if ((pointerX - target4) < SNAP_DISTANCE || pointerX > target4)
                    {


                        int buttonIndex = 3;

//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);
                        number.setX(prevX);
                        v.setX(target4);



                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

//                        numbers.remove(index);
//                        numbers.add(3, (Button)v);
                    }
                    else
                    {
                        v.setX(pointerX);
                    }

                    return true;

//                case MotionEvent.ACTION_UP:
//                    return true;
//
                case MotionEvent.ACTION_DOWN:

                    prevX = v.getX();

                    return true;
            }

            return false;
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
    private void animateView(View view, float start, float end)
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
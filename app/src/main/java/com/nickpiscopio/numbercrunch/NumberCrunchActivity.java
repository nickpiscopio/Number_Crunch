package com.nickpiscopio.numbercrunch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Number Crunch activity.
 *
 * Created by Nick Piscopio on January 6, 2015.
 */
public class NumberCrunchActivity extends ActionBarActivity implements OperatorFragment.DialogListener
{
    private final int ANIMATION_DURATION = 500;
    private final int HIDE_ANSWER_DURATION = 3000;
    private final int NUMBERS_TO_GENERATE = 4;
    private final float SNAP_DISTANCE = 75;
    private final int ONE_SECOND = 1000;
    private final int ITERATION_TO_NEXT = 3;

    private AnswerSheet answerSheet;

    private TextView numberTarget1;
    private TextView numberTarget2;
    private TextView numberTarget3;
    private TextView numberTarget4;

    private Button number1;
    private Button number2;
    private Button number3;
    private Button number4;

    private Button operator1;
    private Button operator2;
    private Button operator3;

    private LinearLayout target;
    private LinearLayout answer;

    private TextView textViewTarget;
    private TextView textViewAnswer;
    private TextView textViewAnswerTitle;
    private TextView textViewAnswerDescription;

    private ArrayList<Button> numbers;
    private ArrayList<Operator> operators;

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

        operator1 = (Button) findViewById(R.id.operator_1);
        operator2 = (Button) findViewById(R.id.operator_2);
        operator3 = (Button) findViewById(R.id.operator_3);

        operator1.setOnClickListener(operatorListener);
        operator2.setOnClickListener(operatorListener);
        operator3.setOnClickListener(operatorListener);

        target = (LinearLayout)findViewById(R.id.target);
        target.setOnTouchListener(touchListener);

        answer = (LinearLayout) findViewById(R.id.answer);

        textViewTarget = (TextView) findViewById(R.id.textView_target);
        textViewAnswer = (TextView) findViewById(R.id.textView_answer);
        textViewAnswerTitle = (TextView) findViewById(R.id.textView_answer_title);
        textViewAnswerDescription = (TextView) findViewById(R.id.textView_answer_description);

        numbers = new ArrayList<Button>();
        numbers.add(number1);
        numbers.add(number2);
        numbers.add(number3);
        numbers.add(number4);

        operators = new ArrayList<Operator>();

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

                    checkAnswer();

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

            float pointerX = 0;

            switch(event.getAction())
            {
                case MotionEvent.ACTION_MOVE:

                    pointerX = event.getRawX() - ((width / 2) + (width / 4));

                    v.setX(pointerX);

//                    Log.i("pointerX: ", String.valueOf(pointerX));
//
//                    Log.i("target1: ", String.valueOf(target1));
//                    Log.i("target2: ", String.valueOf(target2));
//                    Log.i("target3: ", String.valueOf(target3));
//                    Log.i("target4: ", String.valueOf(target4));

                    if ((pointerX - target1) < SNAP_DISTANCE)
                    {


                        int buttonIndex = 0;
////
////                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
////
                        Button number = numbers.get(buttonIndex);
////                        float numberX = number.getX();
                        animateView(number, number.getX(), prevX);
//                        number.setX(prevX);
                        v.setX(target1);

                        Log.i("number.getX(): ", String.valueOf(number.getX()));
                        Log.i("prevX: ", String.valueOf(prevX));
////
                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        prevX = target1;

                        //                        Log.i("prevX: ", String.valueOf(prevX));

                        //                        Log.i("numbers: ", numbers.toString());

                        //                        numbers.remove(index);
                        //                        numbers.add(0, (Button)v);
                    }
                    else if (Math.abs(pointerX - target2) < SNAP_DISTANCE)
                    {
                        int buttonIndex = 1;
//
//                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
//
                        Button number = numbers.get(buttonIndex);
//                        //                        animateView(number, number.getX(), prevX);
                        number.setX(prevX);
                        v.setX(target2);
//
//
                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        prevX = target2;

                        //                        numbers.remove(index);
                        //                        numbers.add(1, (Button)v);
                    }
                    else if (Math.abs(pointerX - target3) < SNAP_DISTANCE)
                    {
                        int buttonIndex = 2;
//
//                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
//
                        Button number = numbers.get(buttonIndex);
//                        //                        animateView(number, number.getX(), prevX);
//
                        number.setX(prevX);
                        v.setX(target3);
//
                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        prevX = target3;

                        //                        numbers.remove(index);
                        //                        numbers.add(2, (Button)v);
                    }
                    else if ((target4 - pointerX) < SNAP_DISTANCE || pointerX > target4)
                    {
                        int buttonIndex = 3;
//
//                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
//
                        Button number = numbers.get(buttonIndex);
//                        //                        animateView(number, number.getX(), prevX);
                        number.setX(prevX);
                        v.setX(target4);
//
                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));

                        prevX = target4;

                        //                        numbers.remove(index);
                        //                        numbers.add(3, (Button)v);
                    }

                    return true;

                case MotionEvent.ACTION_UP:

                    float currX = v.getX();

                    if (currX < target2)
                    {
//                        int buttonIndex = 0;
                        //
                        //                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //
//                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), target2);
//                        number.setX(prevX);
                        v.setX(target1);
                        //
                        //
//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
//                        v.setX(target1);
                    }
                    else if (currX > target2 && currX < target3)
                    {
//                        int buttonIndex = 1;
                        //
                        //                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //
//                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);
                        //                        number.setX(prevX);
                        v.setX(target2);
                        //
                        //
//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //                        v.setX(target2);
                    }
                    else if (currX > target3 && currX < target4)
                    {
//                        int buttonIndex = 2;
                        //
                        //                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //
//                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);
                        //                        number.setX(prevX);
                        v.setX(target3);
                        //
                        //
//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //                        v.setX(target3);
                    }
                    else if (currX > target4)
                    {
//                        int buttonIndex = 3;
                        //
                        //                        //                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //
//                        Button number = numbers.get(buttonIndex);
//                        animateView(number, number.getX(), prevX);
                        //                        number.setX(prevX);
                        v.setX(target4);
                        //
                        //
//                        Collections.swap(numbers, buttonIndex, numbers.indexOf(v));
                        //                        v.setX(target4);
                    }

                    return true;

                case MotionEvent.ACTION_DOWN:

                    prevX = v.getX();
//
//                    Log.i("prevX", String.valueOf(prevX));

                    return true;
            }

            return false;
        }
    };

    private View.OnClickListener operatorListener = new View.OnClickListener()
    {
        @Override public void onClick(View v)
        {
            Bundle bundle = new Bundle();
            bundle.putInt(OperatorFragment.OPERATOR_INDEX, Integer.valueOf(v.getTag().toString()));
            bundle.putInt(OperatorFragment.OPERATOR_ID, v.getId());

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            OperatorFragment newFragment = new OperatorFragment();
            newFragment.setArguments(bundle);
            newFragment.show(ft, "Operator Dialog");
        }
    };

    private void checkAnswer()
    {
        int targetAnswer = 0;

        for (int i = 0; i < numbers.size(); i++)
        {
             if (i == 1)
            {
                targetAnswer = Arithmetic.calculate(operators.get(0), Integer.valueOf(
                        numbers.get(0).getText().toString()), Integer.valueOf(
                        numbers.get(1).getText().toString()));
            }
            else if (i > 1)
            {
                targetAnswer = Arithmetic.calculate(operators.get(i - 1), targetAnswer, Integer.valueOf(numbers.get(i).getText().toString()));
            }
        }

        textViewAnswer.setText(String.valueOf(targetAnswer));

        if (targetAnswer == answerSheet.getAnswer())
        {
            answer.setBackground(getResources().getDrawable(R.drawable.target_correct));

            textViewAnswerTitle.setText(getResources().getString(R.string.title_answer_correct));

            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run()
                {
                    for (int i = ITERATION_TO_NEXT; i >= 0 ; i--)
                    {
                        final int index = i;

                        runOnUiThread(new Runnable()
                        {
                            @Override public void run()
                            {
                                textViewAnswerDescription.setText(String.format(getResources().getString(R.string.description_answer_correct), index));

                                if (index == 0)
                                {
                                    timer.cancel();

                                    generateNewGame();
                                }
                            }
                        });
                    }
                }
            }, ONE_SECOND * ITERATION_TO_NEXT + ONE_SECOND, ONE_SECOND);

            textViewAnswerDescription.setVisibility(View.VISIBLE);
        }
        else
        {
            answer.setBackground(getResources().getDrawable(R.drawable.target_incorrect));

            textViewAnswerTitle.setText(getResources().getString(R.string.title_answer_incorrect));

            textViewAnswerDescription.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Generates a new game for the user.
     */
    private void generateNewGame()
    {
        answerSheet = Arithmetic.generateAnswerSheet(NUMBERS_TO_GENERATE);

        // 0 1 2 3
        //generate random number 2
        //apply to number1
        //if total - 1 - random number > 1, generate new random number


        number1.setText(String.valueOf(answerSheet.getNumbers()[0]));
        number2.setText(String.valueOf(answerSheet.getNumbers()[1]));
        number3.setText(String.valueOf(answerSheet.getNumbers()[2]));
        number4.setText(String.valueOf(answerSheet.getNumbers()[3]));

        textViewTarget.setText(String.valueOf(answerSheet.getAnswer()));
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

        Log.i("view x", String.valueOf(view.getX()));
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

    @Override
    public void onOperatorSelected(int operatorIndex, int operatorId, Operator operator)
    {
        Button button;

        switch (operator)
        {
            case MULTIPLY:

                operators.add(operatorIndex, operator);

                button  = (Button) findViewById(operatorId);
                button.setText("X");

                break;

            case DIVIDE:

                operators.add(operatorIndex, operator);

                button  = (Button) findViewById(operatorId);
                button.setText("/");

                break;

            case ADD:

                operators.add(operatorIndex, operator);

                button  = (Button) findViewById(operatorId);
                button.setText("+");

                break;

            case SUBTRACT:

                operators.add(operatorIndex, operator);

                button  = (Button) findViewById(operatorId);
                button.setText("-");

                break;

            default:
                break;
        }
    }
}
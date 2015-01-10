package com.nickpiscopio.numbercrunch;

/**
 * The model class for Number Crunch answers.
 *
 * Created by Nick Piscopio on January 10, 2015.
 */
public class AnswerSheet
{
    private int[] numbers;
    private int answer;

    /**
     * Setters and getters.
     */
    public int[] getNumbers()
    {
        return numbers;
    }

    public void setNumbers(int[] numbers)
    {
        this.numbers = numbers;
    }

    public int getAnswer()
    {
        return answer;
    }

    public void setAnswer(int answer)
    {
        this.answer = answer;
    }
}
package com.nickpiscopio.numbercrunch;

import java.util.Random;

/**
 * This class generates and checks the Arithmetic for Number Crunch.
 *
 * Created by Nick Piscopio on January 9, 2015.
 */
public class Arithmetic
{
    private static final int OPERATOR_MIN = 0;
    private static final int OPERATOR_MAX = Operator.values().length - 1;
    private static final int NUMBER_MIN = 1;
    private static final int NUMBER_MAX = 9;

    /**
     * Generates and manipulates numbers to get an answer.
     *
     * @param amountToGenerate  The amount of numbers to generate.
     *
     * @return  The answer sheet with numbers and the answer.
     */
    public static AnswerSheet generateAnswerSheet(int amountToGenerate)
    {
        int[] numbers = new int[amountToGenerate];
        int targetAnswer = 0;

        Random random = new Random();

        for (int i = 0; i < amountToGenerate; i++)
        {
            numbers[i] = generateRandomNumber(random, NUMBER_MIN, NUMBER_MAX);

            if (i == 1)
            {
                targetAnswer = doRandomCalculation(random, numbers[0], numbers[1]);
            }
            else if (i > 1)
            {
                targetAnswer = doRandomCalculation(random, targetAnswer, numbers[i]);
            }
        }

        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setNumbers(numbers);
        answerSheet.setAnswer(targetAnswer);

        return answerSheet;
    }

    /**
     * Performs a random calculation between two values.
     *
     * @param random    An instance of the Random class to perform a random number generation.
     * @param value1    The first value to perform a calculation.
     * @param value2    The second value to perform a calculation.
     *
     * @return  The temporary answer to use in other calculations.
     */
    private static int doRandomCalculation(Random random, int value1, int value2)
    {
        int tempAnswer = 0;

        boolean foundOperator = false;

        while (!foundOperator)
        {
            int operatorValue = generateRandomNumber(random, OPERATOR_MIN, OPERATOR_MAX);

            Operator operator = Operator.values()[operatorValue];

            switch (operator)
            {
                case ADD:
                    tempAnswer = value1 + value2;

                    foundOperator = true;

                    break;

                case DIVIDE:

                    if (value1 % value2 == 0)
                    {
                        tempAnswer = value1 / value2;

                        foundOperator = true;
                    }

                    break;

                case MULTIPLY:

                    tempAnswer = value1 * value2;

                    foundOperator = true;

                    break;

                case SUBTRACT:

                    int subtractedValue = value1 - value2;

                    if (subtractedValue > 0)
                    {
                        tempAnswer = subtractedValue;

                        foundOperator = true;
                    }

                    break;

                default:
                    break;
            }
        }

        return tempAnswer;
    }

    /**
     * Generates a random number between two values.
     *
     * @param random    The instance of Random to perform a random number generation.
     * @param min       The minimum number to generate.
     * @param max       The maximum number to generate.
     *
     * @return  The random number generated.
     */
    private static int generateRandomNumber(Random random, int min, int max)
    {
        return random.nextInt((max - min) + 1) + min;
    }
}
package com.nickpiscopio.numbercrunch;

/**
 * This is an enumeration of operators that can be performed in Number Crunch.
 *
 * Created by Nick Piscopio on January 10, 2015.
 */
public enum Operator
{
    MULTIPLY,
    DIVIDE,
    ADD,
    SUBTRACT;

    private int value;

    private Operator()
    {
        this.value = ordinal();
    }

    /**
     * Gets the operator's value.
     */
    public int getValue()
    {
        return value;
    }
}
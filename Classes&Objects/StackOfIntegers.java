package com.company;

/*
    Class "StackOfIntegers"
    This class will be utilized to display prime
    numbers that are less than 120 in descending order.
    This class is derived from the textbook (per assignment).

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

public class StackOfIntegers
{
    private int[] elements;
    private int size;
    //default capacity of the stack
    public static final int DEFAULT_CAPACITY = 16;

    //construct a stack with the default capacity of 16
    public StackOfIntegers()
    {
        this(DEFAULT_CAPACITY);
    }

    //construct a stack with the specified maximum capacity
    public StackOfIntegers(int capacity)
    {
        elements = new int[DEFAULT_CAPACITY];
    }

    //push new integer to top of stack
    public void push(int value)
    {
        if (size >= elements.length)
        {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = value;
    }

    //return and remove the topmost element from the stack
    public int pop()
    {
        return elements[--size];
    }

    //return the top element from the stack
    public int peek()
    {
        return elements[size-1];
    }

    //test whether the stack is empty or not
    public boolean empty()
    {
        return size == 0;
    }

    //return the number of elements in the stack
    public int getSize()
    {
        return size;
    }

}

package com.company;

/*
    Class "QuadraticEquations" -
    This class is used to create quadratic equation objects.
    The user will enter in values for a, b, and c in a
    quadratic equation and the class will use its attributes and
    methods to calculate the respective roots. Corresponding roots will
    be outputted based on if the

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */


public class QuadraticEquation
{
    private double a;
    private double b;
    private double c;

    //quad eqn object (no-arg constructor)
    QuadraticEquation()
    {
    }

    //get the arguments for the equation
    QuadraticEquation(double a1, double b1, double c1)
    {
        a = a1;
        b = b1;
        c = c1;
    }

    //method to get the discriminant value
    public double getDiscriminant()
    {
        //variable to hold the discriminant value
        double discriminant = Math.pow(getB(), 2) - (4 * getA() * getC());
        return discriminant;
    }

    //method to get the first root (if you were to add in the formula)
    public double getRoot1()
    {
        double root1 = (-getB() + Math.sqrt(getDiscriminant())) / (2 * getA());
        return root1;
    }

    //method to get the second root (if you were to subtract in the formula)
    public double getRoot2()
    {
        double root2 = (-getB() - Math.sqrt(getDiscriminant())) / (2 * getA());
        return root2;
    }

    //accessor for a
    public double getA()
    {
        return a;
    }
    //accessor for b
    public double getB()
    {
        return b;
    }
    //accessor for c
    public double getC()
    {
        return c;
    }
}

/*Notes: (Ignore)

if (getDiscriminant() == 0)
        {
            return root1;
        }
        else if (getDiscriminant() < 0)
        {
            return 0;
        }
        else
            return root1;


 */
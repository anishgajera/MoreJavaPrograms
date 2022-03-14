package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW 4 - Classes (Main file)
    Note: This is the file used to call the methods and actually
    implement certain classes created per assignment instructions.
    This file implements those certain classes created.

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

public class Main
{
    public static void main(String[] args)
    {
        //this block is for the Rectangle class implementation
        {
            //this is the rectangle with a width, height, area, of 1 (the default case, per assignment instructions)
            Rectangle rectangle1 = new Rectangle(1,1);
            System.out.println("Rectangle Class");
            //1st case (width 4, height 40)
            Rectangle rectangle2 = new Rectangle(4, 40);
            System.out.println("Rectangle 1\nWidth: " + rectangle2.width + "\nHeight: " + rectangle2.height + "\nArea: " + rectangle2.getArea() + "\nPerimeter: " + rectangle2.getPerimeter());
            System.out.print("\n");
            //2nd case (width 3.5, height 35.9)
            Rectangle rectangle3 = new Rectangle(3.5, 39.5);
            System.out.println("Rectangle 2\nWidth: " + rectangle3.width + "\nHeight: " + rectangle3.height + "\nArea: " + rectangle3.getArea() + "\nPerimeter: " + rectangle3.getPerimeter());
            System.out.print("\n\n");
        }

        //this block is for the Stock class implementation
        {
            System.out.println("Stock Class");
            //create stock object with ticker "ORCL" and name "Oracle Corporation"
            Stock newStock = new Stock("ORCL", "Oracle Corporation");
            //set the previous closing price of the stock to 34.5 and the current closing price to 34.35 and calculate the percent difference
            newStock.getChangePercent(34.5, 34.35);
            System.out.println("Ticker: " + newStock.symbol + "\nName: " + newStock.name + "\nPrevious closing price: " + newStock.previousClosingPrice + "\nCurrent closing price: " + newStock.currentPrice + "\nPrice-change percentage: " + newStock.getChangePercent(34.5, 34.35));
            System.out.print("\n\n");
        }

        //this block is for the Account class implementation
        {
            System.out.println("Account Class");
            //create a new account with id 1122, balance of 20000, and annual interest rate of 4.5%
            Account account1 = new Account(1122, 20000, 4.5);
            //withdraw 2500 from the account
            account1.withdraw(2500);
            //deposit 3000 into the account
            account1.deposit(3000);
            System.out.println("Balance: " + account1.getBalance() + "\nMonthly interest: " + account1.monthlyInterest(account1.getMonthlyInterestRate()) + "\nDate created: " + account1.getDateCreated());
            System.out.print("\n\n");
        }

        //this block is for the QuadraticEquation class implementation
        {
            System.out.println("Quadratic Equation Class");
            //variables that will hold the 3 values the user enters
            double a, b, c;
            //scanner object
            Scanner scan = new Scanner(System.in);
            //prompt user for 3 values for a, b, c
            System.out.println("Enter 3 values for a, b, c: ");
            a = scan.nextDouble();
            b = scan.nextDouble();
            c = scan.nextDouble();
            //quadratic equation object (with values user entered)
            QuadraticEquation eqn = new QuadraticEquation(a, b, c);
            //if the discriminant is 0, then display one of the roots (since they'll both be the same)
            if(eqn.getDiscriminant() == 0)
            {
                System.out.println(eqn.getRoot1());
            }
            //else if the discriminant is negative, then the equation has no roots
            else if(eqn.getDiscriminant() < 0)
            {
                System.out.println("The equation has no roots.");
            }
            //else, display the 2 roots
            else
            {
                System.out.println("Root 1: " + eqn.getRoot1() + "\nRoot 2: " + eqn.getRoot2());
            }
            System.out.print("\n\n");
        }

        //this block is for the stackOfIntegers class implementation (to print prime numbers, per assignment instructions)
        {
            System.out.println("Printing Prime Numbers");
            //StackOfIntegers (stack) object (a.k.a. create a stack of integers)
            StackOfIntegers stack = new StackOfIntegers();
            for (int i = 2; i < 120; i++)
            {
                //if the value is a prime number
                if(isPrime(i))
                {
                    //then push it onto the stack
                    stack.push(i);
                }
            }
            //output the prime numbers
            System.out.println("Prime numbers less than 120 in decreasing order: ");
            //while the stack is still full (until it is empty)
            while (!stack.empty())
            {
                //pop the elements from the stack and print them (with proper spacing)
                System.out.print(stack.pop() + " ");
            }
            System.out.print("\n\n");
        }
    }

    //function used by StackOfInteger class to check if the number we will push onto the stack is prime or not
    public static boolean isPrime(int value)
    {
        for (int i = 2; i <= value / 2; i++)
        {
            if (value % i == 0)
            {
                return false;
            }
        }
        return true;
    }

}

